package HW3;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private Node root;

    // внутренний класс, представляющий узел дерева
    private class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // конструктор для создания пустого дерева
    public BinaryTree() {
        this.root = null;
    }

    // метод для добавления элемента в дерево
    public void add(int value) {
        root = addRecursive(root, value);
    }

    // вспомогательный рекурсивный метод для добавления элемента в дерево
    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }
        return current;
    }

    // метод для удаления элемента из дерева
    public void remove(int value) {
        root = removeRecursive(root, value);
    }

    // вспомогательный рекурсивный метод для удаления элемента из дерева
    private Node removeRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            // Удаление узла
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = removeRecursive(current.right, smallestValue);
            return current;
        }
        if (value < current.value) {
            current.left = removeRecursive(current.left, value);
            return current;
        }
        current.right = removeRecursive(current.right, value);
        return current;
    }

    // вспомогательный метод для поиска наименьшего значения в дереве
    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    // метод для удаления корня дерева
    public void removeRoot() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.left != null) {
                if (current.left.left != null) {
                    queue.add(current.left);
                } else {
                    current.left = null;
                }
            }

            if (current.right != null) {
                if (current.right.right != null) {
                    queue.add(current.right);
                } else {
                    current.right = null;
                }
            }
        }
        root = null;
    }

    // метод для удаления дочернего элемента
    public void removeChild(int value) {
        removeChildRecursive(root, value);
    }

    // вспомогательный рекурсивный метод для удаления дочернего элемента
    private void removeChildRecursive(Node current, int value) {
        if (current == null) {
            return;
        }

        if (current.left != null && current.left.value == value) {
            current.left = null;
            return;
        } else if (current.right != null && current.right.value == value) {
            current.right = null;
            return;
        }

        removeChildRecursive(current.left, value);
        removeChildRecursive(current.right, value);
    }

    // метод для вывода элементов дерева в порядке обхода "in-order" (левый - корень - правый)
    public void printInOrder() {
        printInOrderRecursive(root);
        System.out.println();
    }

    // вспомогательный рекурсивный метод для вывода элементов дерева в порядке обхода "in-order"
    private void printInOrderRecursive(Node current) {
        if (current != null) {
            printInOrderRecursive(current.left);
            System.out.print(current.value + " ");
            printInOrderRecursive(current.right);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree
                = new BinaryTree();

        // добавление элементов
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        System.out.println("Вывод всех элементов дерева:");
        tree.printInOrder();

        // удаление узла
        tree.remove(70);
        System.out.println("Вывод элементов дерева после удаления узла с значением 70:");
        tree.printInOrder();

        // удаление корня дерева
        tree.removeRoot();
        System.out.println("Вывод элементов дерева после удаления корня:");
        tree.printInOrder();

        // удаление дочернего элемента
        tree.removeChild(30);
        System.out.println("Вывод элементов дерева после удаления дочернего элемента с значением 30:");
        tree.printInOrder();
    }
}
