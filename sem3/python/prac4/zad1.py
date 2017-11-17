import timeit


def doskonale_skladana(n):
    return [k for k in range(1, n + 1) if sum([i for i in range(1, k) if k % i == 0]) == k]


def doskonale_funkcyjna(n):
    return [*filter(
        lambda k:  # suma wlasciwych dzielnikow k
        sum([*filter(lambda i: k % i == 0, range(1, k))]) == k,  # doskonala
        range(2, n + 1))]


class DoskonaleIterowana():
    def __init__(self, n):
        self.x = 1
        self.found = 1
        self.n = n

    def __iter__(self):
        return self

    def __next__(self):
        k = self.x + 1
        while True:
            if k > self.n:
                raise StopIteration
            if sum([i for i in range(1, k) if k % i == 0]) == k:
                self.found = k
                break
            k += 1
        self.x = self.found + 1

        return self.found


if __name__ == '__main__':
    imp = 'from __main__ import '
    num = 1
    N = [10, 100, 1000]
    times = [[0, 0, 0] for i in range(len(N))]
    funkcje = {0: 'doskonale_funkcyjna', 1: 'doskonale_skladana', 2: 'DoskonaleIterowana'}

    for i, n in enumerate(N):
        for k, fun in funkcje.items():
            times[i][k] = timeit.timeit(fun + '(' + str(n) + ')', imp + fun, number=num)

    fun = ['funkcyjna', 'skladana', 'iterator']
    print('    ', end='')
    for i in fun:
        print(" | {:>16s}".format(i), end='')
    print('\n{:-<61}'.format(''))

    for i, t in enumerate(times):
        print("{:>4}".format(N[i]), end='')
        for j in range(len(t)):
            print(" | {:.10e}".format(times[i][j]), end='')
        print()
