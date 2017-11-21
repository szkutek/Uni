import re
import requests
from bs4 import BeautifulSoup
from pprint import pprint


def znajdz_linki(url, wszystkie_linki=[]):
    page = requests.get(url).text
    soup = BeautifulSoup(page, 'html.parser')

    all_a = soup.find_all('a', href=True)
    links = []
    p = re.compile('http|www')

    for a in all_a:
        href = a['href']
        if href.strip('#') and href != '\\' and href != '/':
            if not p.match(href):
                href = url + href
            try:
                r = requests.head(href)
                if r.status_code < 400 and href not in wszystkie_linki:
                    links.append(href)
            except:
                print('Invalid url: ' + href)
    return links


def zwroc_linki_odlegle_od_url(pocz_url, ilosc_krokow=0):
    urls = [pocz_url]
    linki = []
    for k in range(ilosc_krokow + 1):
        nowe_url = []
        for u in urls:
            nowe_url += znajdz_linki(u, linki)
        linki += nowe_url
        urls = nowe_url
    return linki


def wypisz_wyniki_wyszukiwania(wyniki):
    for slowo, w in wyniki.items():
        print('Wyniki wyszukiwania dla ' + slowo + ':')
        for url, ilosc in w:
            print('- ' + url + ' (' + str(ilosc) + ')')


def wyszukiwarka(fraza, baza_url):
    slowa = fraza.split(' ')
    p = re.compile('|'.join(slowa), re.IGNORECASE)
    wyniki = {s.lower(): {} for s in slowa}

    for url in baza_url:
        page = requests.get(url).text
        soup = BeautifulSoup(page, 'html.parser')
        text = soup.text
        found_on_this_page = p.findall(text)
        for found in found_on_this_page:
            if url not in wyniki[found.lower()].keys():
                wyniki[found.lower()][url] = 1
            else:
                wyniki[found.lower()][url] += 1
    pprint(wyniki)
    print()

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
