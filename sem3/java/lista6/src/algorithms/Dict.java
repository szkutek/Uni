package algorithms;

/**
 * @param <T>
 */
public interface Dict<T extends Comparable<T>> {
//    metody słownikowe

    /**
     * Metoda sprawdza czy element o zadanej wartości istnieje
     *
     * @param t element o zadanej wartości
     * @return true jeśli element jest w zbiorze, false jeśli nie
     */
    boolean search(T t);

    /**
     * Metoda dodaje nowy element do zbioru
     *
     * @param t element dodawany do zbioru
     */
    void insert(T t);

    /**
     * Metoda usuwa podany element ze zbioru
     *
     * @param t element do usunięcia
     */
    void remove(T t);

    /**
     * Metoda wyznacza element najmniejszy zbioru
     *
     * @return najmniejszy element zbioru
     */
    T min();

    /**
     * Metoda wyznacza element największy zbioru
     *
     * @return największy element zbioru
     */
    T max();

    /**
     * Metoda usuwa całe drzewo
     */
    void clear();
}
