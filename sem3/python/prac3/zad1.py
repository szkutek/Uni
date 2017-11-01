from timeit import default_timer as timer


def pierwsze_skladana(n):
    prime = lambda i: [j for j in range(1, i) if i % j == 0] == [1]
    return [i for i in range(2, n + 1) if prime(i)]


def pierwsze_funkcyjna(n):
    return [*filter(lambda i: [*filter(lambda j: i % j == 0, range(1, i))] == [1], range(n + 1))]


if __name__ == '__main__':
    # # ZAD 1
    N = 10000
    start = timer()
    pierwsze_skladana(N)
    print(timer() - start)

    start = timer()
    pierwsze_funkcyjna(N)
    print(timer() - start)
