class FormatujAkapity():
    def __init__(self, strumien):
        self.stream = strumien
        self.line = strumien.readline()

    def __iter__(self):
        return self

    def __next__(self):
        pass


def length(l):
    res = 0
    for el in l:
        res += len(el) + 1
    return res


if __name__ == '__main__':
    file = open('tekst.txt', 'r')
    szerokosc = 20
    res = []
    l = file.readline().split()
    print(l)
    # while l:
    #     if len(l) <= szerokosc:
    #         if l.strip():
    #             res += (l.split(' '))
    #         l = file.readline()
    #     else:
    #         l1 = l[:szerokosc]
    #         l2 = l[szerokosc:]
    #
    #         res.append(l1)
    #         l = l2
    while l:
        lgth = length(l)
        if lgth <= szerokosc:
            if l:
                res += l
            l = file.readline().split()
        else:
            l2 = []
            while length(l) > szerokosc:
                l2 = [l[-1]] + l2
                print(l2)
                l = l[:-1]

            res += l
            l = l2

    print(res)
