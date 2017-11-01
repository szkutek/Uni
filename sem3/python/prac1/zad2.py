def zaszyfruj(tekst, klucz):
    szyfr = ['a'] * len(tekst)
    for i in range(len(tekst)):
        szyfr[i] = chr(ord(tekst[i]) ^ (klucz))
    szyfr = ''.join(szyfr)
    print(szyfr)
    return szyfr

def encrypt(text,key):
	res=""
	for c in text:
		res+= c^key
	return res


def odszyfruj(tekst, klucz):
    zaszyfruj(tekst, klucz)


if __name__ == '__main__':
    # klucz in [0,255]
    tekst = 'Python'
    klucz = 7

    szyfr = zaszyfruj(tekst, klucz)
    odszyfruj(szyfr, klucz)
