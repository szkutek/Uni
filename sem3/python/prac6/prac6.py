import re

import concurrent.futures
import requests
from bs4 import BeautifulSoup
from pprint import pprint


def processed_href(a, pattern):
    href = a['href']
    if href.strip('#') and href != '\\' and href != '/':
        href = href.split('#')[0]
        if not pattern.match(href):
            href = url + href
        try:
            r = requests.head(href)
            if r.status_code < 400:
                return href
        except:
            print('Invalid url: ' + href)


def znajdz_linki(url):
    page = requests.get(url).text
    soup = BeautifulSoup(page, 'html.parser')

    all_a = soup.find_all('a', href=True)
    links = []
    p = re.compile('http|www')

    for a in all_a:
        processed = processed_href(a, p)
        if processed:
            links.append(processed)
    return links


def zwroc_linki_odlegle_od_url(pocz_url, ilosc_krokow=0):
    executor = concurrent.futures.ThreadPoolExecutor(max_workers=64)
    urls = [pocz_url]
    linki = [pocz_url]

    futures = []
    for k in range(ilosc_krokow + 1):
        nowe_url = []
        for u in urls:
            futures.append(executor.submit(znajdz_linki, u))
            # nowe_url += znajdz_linki(u)
        completed_futures = concurrent.futures.as_completed(futures)  # poczekaj aż wszystkie się zakończą
        for future in completed_futures:
            res = future.result()
            nowe_url += res

        linki += nowe_url
        urls = nowe_url

    return set(linki)


def wypisz_wyniki_wyszukiwania(wyniki):
    for slowo, w in wyniki.items():
        print('Wyniki wyszukiwania dla ' + slowo + ':')
        for url, ilosc in w:
            print('- ' + url + ' (' + str(ilosc) + ')')


def znajdz_na_stronie(url, p):
    page = requests.get(url).text
    text = BeautifulSoup(page, 'html.parser').text
    return url, p.findall(text)


def wyszukiwarka(fraza, baza_url):
    slowa = fraza.split(' ')
    p = re.compile('|'.join(slowa), re.IGNORECASE)
    wyniki = {s.lower(): {} for s in slowa}

    executor = concurrent.futures.ThreadPoolExecutor(max_workers=64)
    futures = []  # futures dla znalezionych na stronie
    for url in baza_url:
        futures.append(executor.submit(znajdz_na_stronie, url, p))

    completed_futures = concurrent.futures.as_completed(futures)
    for future in completed_futures:
        znalezione_na_stronie = future.result()
        url = znalezione_na_stronie[0]
        for znalezione in znalezione_na_stronie[1]:  # dodaj wyniki do słownika
            wyniki[znalezione.lower()][url] = wyniki[znalezione.lower()].get(url, 0) + 1
    # pprint(wyniki)
    # print()

    for s, d in wyniki.items():
        wyniki[s] = sorted(d.items(), key=lambda x: x[1], reverse=True)
    return wyniki


if __name__ == '__main__':
    url = 'https://www.ii.uni.wroc.pl/~marcinm/'
    # url = 'https://docs.python.org/3/'
    # links = znajdz_linki(url)
    # links = zwroc_linki_odlegle_od_url(url, 0)

    wyniki = wyszukiwarka('python Ruby kurczak', zwroc_linki_odlegle_od_url(url, 0))
    wypisz_wyniki_wyszukiwania(wyniki)

    # print('\n--------------------------------------------------------------------------------\n')
    # wyniki = wyszukiwarka('python Ruby kurczak', zwroc_linki_odlegle_od_url(url, 1))
    # wypisz_wyniki_wyszukiwania(wyniki)
