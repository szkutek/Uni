package algorithms;

/**
 * Sparametryzowana implementacja Drzewa Binarnych Poszukiwań (BST)
 *
 * @param <T> Typ danych, które chcemy przechowywać
 */
public class BST<T extends Comparable<T>> implements Dict<T> {

    /**
     * Wewnętrzna klasa węzła przechowująca wartość i wskaźniki do dzieci oraz rozdzica
     *
     * @param <T> Typ danych, które chcemy przechowywać. Muszą być porównywalne(dziedziczyć po Comparable)
     */
    private class Node<T extends Comparable<T>> {

        /**
         * Wskaźniki do dzieci oraz rodzica
         */
        Node<T> left, right, parent;
        /**
         * Dane przechowywane w węźle
         */
        T data;

        /**
         * Tworzy pusty węzeł
         */
        public Node() {
            left = null;
            right = null;
            parent = null;
            data = null;
        }

        /**
         * Tworzy węzeł z danymi, bez odniesień do potomków i rodzica
         *
         * @param t dane, które ma przechowywać
         */
        public Node(T t) {
            left = null;
            right = null;
            parent = null;
            data = t;
        }

        /**
         * Tworzy węzeł ze wszystkimi danymi
         *
         * @param parent wskaźnik do rodzica
         * @param left   wskaźnik do lewego potomka
         * @param right  wskaźnik do prawego potomka
         * @param t      dane do przechowania
         */
        public Node(Node<T> parent, Node<T> left, Node<T> right, T t) {
            this.left = left;
            this.right = right;
            this.parent = parent;
            data = t;
        }

        /**
         * Wstawia dane do drzewa
         *
         * @param t dane do wstawienia
         */
        public void insert(T t) throws NullPointerException {
            if (t == null) {
                throw new NullPointerException("Próbowałeś/aś dodać pusty element.");
            }
            if (root == null) {
                root = new Node(t);
            } else {
                Node<T> tmp = (Node<T>) root;
                Node<T> par = null;

                while (tmp != null) {
                    par = tmp;
                    if (t.compareTo(tmp.data) == -1) {
                        tmp = tmp.left;
                    } else {
                        tmp = tmp.right;
                    }
                }
                if (t.compareTo(par.data) == -1) {
                    par.left = new Node(par, null, null, t);
                } else {
                    par.right = new Node(par, null, null, t);
                }
            }
        }

        /**
         * Usuwa dane z drzewa
         *
         * @param t dane do usunięcia
         */
        public void remove(T t) throws NullPointerException {
            if (root.left == null && root.right == null) {
                root = null;
            }
            Node<T> tmp = (Node<T>) root;
            Node<T> par = null;
            while (tmp != null && tmp.data.compareTo(t) != 0) {
                par = tmp;
                if (t.compareTo(tmp.data) < 0) {
                    tmp = tmp.left;
                } else {
                    tmp = tmp.right;
                }
            }
            if (tmp.left == null && tmp.right == null) {
                if (t.compareTo(par.left.data) == 0) {
                    par.left = null;
                } else {
                    par.right = null;
                }
            } else if (tmp.left == null) {
                par.right = tmp.right;
            } else if (tmp.right == null) {
                par.left = tmp.left;
            } else if (tmp.left != null && tmp.right != null) {
                par = tmp;
                tmp = tmp.right;
                while (tmp.left != null) {
                    tmp = tmp.left;
                }
                par.data = tmp.data;
                tmp.parent.left = null;
                tmp.parent.right = null;
            } else {
                throw new NullPointerException("Nie znaleziono wartości");
            }
        }

        /**
         * Wyszukuje dane w drzewie
         *
         * @param t dane do wyszukania
         * @return zwraca wyszukane dane
         * @throws NullPointerException gdy dane nie zostaną znalezione
         */
        public T search(T t) throws NullPointerException {
            Node<T> tmp = (Node<T>) root;
            while (tmp != null && tmp.data.compareTo(t) != 0) {
                if (t.compareTo(tmp.data) < 0) {
                    tmp = tmp.left;
                } else {
                    tmp = tmp.right;
                }
            }
            if (tmp.data == null) {
                throw new NullPointerException("Nie znaleziono wartości.");
            }
            return tmp.data;
        }

        /**
         * Zwraca element maksymalny w drzewie
         *
         * @return wartość elem. max
         */
        public T max() {
            Node<T> tmp = (Node<T>) root;
            while (tmp.right != null) {
                tmp = tmp.right;
            }
            return tmp.data;
        }

        /**
         * Zwraca element minimalny w drzewie
         *
         * @return wartość elem. min
         */
        public T min() {
            Node<T> tmp = (Node<T>) root;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            return tmp.data;
        }

        /**
         * Przechodzi po drzewie in order i zwraca napis
         *
         * @param node aktualny węzeł
         * @return napis
         */
        public String valueString(Node<T> node) {
            String wynik = "";
            if (node != null) {
                wynik += valueString(node.left) + " " + node.data + " " + valueString(node.right);
            }
            return wynik;
        }

        @Override
        public String toString() {
            return valueString((Node<T>) root);
        }
    }

    private Node<T> root;
    private int size;

    /**
     * Tworzy puste drzewo
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Tworzy drzewo z jednym węzłem
     *
     * @param t
     */
    public BST(T t) {
        root = new Node(t);
        size = 1;
    }

    /**
     * Szuka wartości w drzewie
     *
     * @param t wartość do wyszukania
     * @return true jeśli znaleziono, w przeciwnym wypadku false
     */
    @Override
    public boolean search(T t) {
        T tmp = null;
        try {
            tmp = root.search(t);
        } catch (NullPointerException ex) {
            //System.err.println(ex.getMessage());
            System.out.println("Nie ma takiego elementu.");
        }
        return tmp != null;
    }

    /**
     * Wstawia element do drzewa
     *
     * @param t element do wstawienai
     */
    @Override
    public void insert(T t) {
        if (root == null) {
            root = new Node(t);
        } else {
            try {
                root.insert(t);
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
            }
            size++;
        }
    }

    /**
     * Usuwa element z drzewa
     *
     * @param t element do usunięcia
     */
    @Override
    public void remove(T t) {
        root.remove(t);
        size--;
    }

    /**
     * Zwraca element maksymalny drzewa
     *
     * @return wartość elem. max
     */
    @Override
    public T max() {
        return root.max();
    }

    /**
     * Zwraca element minimalny drzewa
     *
     * @return wartość elem. min
     */
    @Override
    public T min() {
        return root.min();
    }

    /**
     * Zwraca rozmiar drzewa
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Czyści drzewo
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public String toString() {
        if (root != null) {
            return root.toString();
        } else {
            System.out.println("Drzewo jest puste.");
            return "";
        }
    }
}