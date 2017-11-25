import algorithms.BST;

public class Main {

    public static void main(String[] args) {
        System.out.println("algorithms.BST<INTEGER>");
        BST<Integer> bst = new BST<>();
        bst.insert(8);
        bst.insert(3);
        bst.insert(1);
        bst.insert(4);
        bst.insert(7);
        bst.insert(5);
        bst.insert(6);
        bst.insert(10);
        bst.insert(9);
        bst.insert(14);
        bst.insert(13);
        bst.insert(12);
        System.out.println("max = " + bst.max());
        bst.insert(null);
        System.out.println(bst.toString());
        bst.remove(1);
        bst.remove(4);
        System.out.println("min = " + bst.min());
        System.out.println(bst.toString());
        System.out.println(bst.search(1));
        bst.clear();
        System.out.println("size = " + bst.size());

        System.out.println("\nalgorithms.BST<STRING>");
        BST<String> bstS = new BST<>();
        bstS.insert("ala");
        bstS.insert("ma");
        bstS.insert("kota");
        bstS.insert("ale");
        bstS.insert("nie");
        bstS.insert(null);
        bstS.insert("ma");
        System.out.println("min = " + bstS.min());
        bstS.insert("psa");
        System.out.println("max = " + bstS.max());
        System.out.println(bstS.toString());
        bstS.remove("nie");
        System.out.println(bstS.toString());
        System.out.println(bstS.search("nie"));
        bstS.clear();
        System.out.println("size = " + bstS.size());
    }
}