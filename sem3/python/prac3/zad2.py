from functools import reduce
from timeit import default_timer as timer


def doskonale_skladana(n):
    return [k for k in range(1, n + 1) if sum([i for i in range(1, k) if k % i == 0]) == k]


def doskonale_funkcyjna(n):
    return [*filter(
        lambda k:  # suma wlasciwych dzielnikow k
        sum([*filter(lambda i: k % i == 0, range(1, k))]) == k,  # doskonala
        range(2, n + 1))]


if __name__ == '__main__':
    # ZAD 2
    N = 1000
    start = timer()
    doskonale_skladana(N)
    print(timer() - start)

    start = timer()
    doskonale_funkcyjna(N)
    print(timer() - start)
    print(doskonale_funkcyjna(N))
