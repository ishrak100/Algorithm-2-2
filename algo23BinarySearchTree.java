import java.util.Scanner;

class Node {
    int v;
    Node left, right;

    public Node(int val) {
        this.v = val;
        this.left = this.right = null;
    }
}

public class algo23BinarySearchTree {
    Node root;

    public algo23BinarySearchTree() {
        root = null;
    }

    // Method to insert a node in the BST
    public Node insert(Node root, int v) {
        if (root == null) {
            root = new Node(v);
            return root;
        }

        if (v < root.v) {
            root.left = insert(root.left, v);
        } else if (v > root.v) {
            root.right = insert(root.right, v);
        }

        return root;
    }

    // Method to perform inorder traversal
    public void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.v + " ");
        inorder(root.right);
    }

    // Method to search for a specific value in the BST
    public boolean find(Node root, int v) {
        if (root == null) {
            return false;
        }

        if (root.v == v) {
            return true;
        }

        if (v < root.v) {
            return find(root.left, v);
        } else {
            return find(root.right, v);
        }
    }

    // Main method to take user input and execute the operations
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        algo23BinarySearchTree bst = new algo23BinarySearchTree();

        // Input for the number of nodes
        System.out.println("Enter the number of nodes:");
        int N = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Input for nodes to be inserted into the BST
        System.out.println("Enter the nodes (comma-separated):");
        String[] nodes = scanner.nextLine().split(",");
        
        // Explicit for loop with condition and increment parts
        for (int i = 0; i < nodes.length; i++) {
            bst.root = bst.insert(bst.root, Integer.parseInt(nodes[i].trim()));
        }

        // In-order traversal
        System.out.print("In-Order Traversal: ");
        bst.inorder(bst.root);
        System.out.println();

        // Input for the value to search
        System.out.println("Enter the value to search:");
        int searchValue = scanner.nextInt();
        if (bst.find(bst.root, searchValue)) {
            System.out.println(searchValue + " found in the BST.");
        } else {
            System.out.println(searchValue + " not found in the BST.");
        }

        scanner.close();
    }
}
