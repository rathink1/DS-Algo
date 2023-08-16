public class BSTClass {
    Node rootNode;
    class Node {
        int key;
        Node left, right;
        public Node(int data) {
            key = data;
            left = right = null;
        }
    }

    BSTClass() {
        rootNode = null;
    }

    void insert(int data){
        rootNode = insert(data, rootNode);
    }

    Node insert(int data, Node rootNode){
        if (rootNode == null) {
            return new Node(data);
        } else if (data >= rootNode.key){
            rootNode.right = insert(data, rootNode.right);
        } else {
            rootNode.left = insert(data, rootNode.left);
        }
        return rootNode;
    }

    Boolean search(int data){
        return search(data, rootNode);
    }

    Boolean search(int data, Node rootNode){
        if (rootNode == null) return false;
        else if (data == rootNode.key) return true;
        else if (data > rootNode.key) return search(data, rootNode.right);
        else return search(data, rootNode.left);
    }

    boolean delete(int data) {
        return delete(data, rootNode) != null;
    }

    Node delete(int data, Node rootNode){
        if (rootNode == null) return null;
        else if (data < rootNode.key) rootNode.left = delete(data, rootNode.left);
        else if (data > rootNode.key) rootNode.right = delete(data, rootNode.right);
        else {
            if (rootNode.left == null && rootNode.right == null) return null;
            else if (rootNode.left == null) return rootNode.right;
            else if (rootNode.right == null) return rootNode.left;
            else {
                rootNode.key = findMinSuccessor(rootNode.right).key;
                rootNode.right = delete(rootNode.key, rootNode.right);
            }
        }
        return rootNode;
    }

    private Node findMinSuccessor(Node rootNode) {
        if(rootNode.left == null) return rootNode;
        else return findMinSuccessor(rootNode.left);
    }

    void inorder() {
        inorder_Recursive(rootNode);
    }

    void inorder_Recursive(Node rootNode) {
        if (rootNode != null) {
            inorder_Recursive(rootNode.left);
            System.out.print(rootNode.key + " ");
            inorder_Recursive(rootNode.right);
        }
    }

    void printLevelOrder() {
        int h = height(rootNode);
        int i;
        for (i = 1; i <= h; i++)
            printCurrentLevel(rootNode, i);
    }

    int height(Node root) {
        if (root == null)
            return 0;
        else {

            // Compute  height of each subtree
            int lheight = height(root.left);
            int rheight = height(root.right);

            // use the larger one
            if (lheight > rheight)
                return (lheight + 1);
            else
                return (rheight + 1);
        }
    }

    void printCurrentLevel(Node root, int level) {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.key + " ");
        else if (level > 1) {
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.right, level - 1);
        }
    }


    public static void main(String[] args) {
        BSTClass bst = new BSTClass();
        bst.insert(50);
        bst.insert(40);
        bst.insert(60);
        bst.insert(30);
        bst.insert(45);
        bst.insert(20);
        bst.insert(43);
        bst.insert(47);
        bst.insert(10);
        bst.insert(41);
        bst.insert(44);
        bst.insert(42);

        bst.printLevelOrder();
        System.out.println(bst.search(12));

        bst.delete(40);
        bst.printLevelOrder();
    }
}