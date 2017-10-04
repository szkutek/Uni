def tabliczka(x1, x2, y1, y2):
    print('  '.join(str(x) for x in range(x1, x2 + 1)))
    for y in range(y1, y2 + 1):
        print('')


tabliczka(3, 5, 2, 4)
