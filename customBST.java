
//  custom binary search tree class
public class customBST{
    
    // main to run test scenarios
    public static void main(String[] args){
        customBST tree = new customBST(); // create tree
        // test insert cases
        Test.randomNum(tree, 10000);
        Test.randomNum(tree, 1000);
        Test.randomNum(tree, 100);
        Test.orderedNum(tree, 100);
        Test.orderedNum(tree, 1000);
        Test.orderedNum(tree, 10000);
        Test.testDepth(tree);
        Test.testSize(tree);
    }

    // test case class
    static class Test{
        static void testSize(customBST tree){
            long start = System.nanoTime();
            tree.getSize();
            long end = System.nanoTime();
            long time = end - start;
            System.out.println("getSize() runtime: "+time+" ns.");
        }
        static void testDepth(customBST tree){
            long start = System.nanoTime();
            tree.getDepth();
            long end = System.nanoTime();
            long time = end - start;
            System.out.println("getDepth() runtime: "+time+" ns.");
        }
        // test with random numbers, pass a tree and n nodes
        static void randomNum(customBST tree, int n){
            tree = new customBST();
            int pick = n/2;
            double sample = 0.0;
            long start = System.nanoTime();
            for (int i = 0; i < n; ++i){
                double num = Math.random();
                tree.insert(num);
                if (i == pick){
                    sample = num;
                }
            }
            long end = System.nanoTime();
            long time = end - start;
            System.out.println(n+" random nodes, runtime: "+time+" ns.");
            start = System.nanoTime();
            tree.search(sample);
            end = System.nanoTime();
            time = end - start;
            System.out.println(n+" node search() runtime: "+time+" ns.");
        }

        // test with ordered number, pass a tree and n nodes
        static void orderedNum(customBST tree, int n){
            tree = new customBST();
            long start = System.nanoTime();
            for (int i = 0; i < n; ++i){
                tree.insert(i);
            }
            long end = System.nanoTime();
            long time = end - start;
            System.out.println(n+" sorted nodes, runtime: "+time+" ns.");
            start = System.nanoTime();
            tree.search(n/2);
            end = System.nanoTime();
            time = end - start;
            System.out.println(n+" sorted node search() runtime: "+time+" ns.");
        }
    }

    // size = total nodes
    // depth = longest depth of nodes from root to null
    int size, depth;
    
    // root node
    Node root;

    // tree constructor
    customBST(){
        root = null;
        depth = 0;
        size = 0;
    }

    // insert new node
    boolean insert(double val){
        // if empty tree, add root
        if (root == null){
            root = new Node(val);
            depth = 1;
            ++size;
            return true;
        }
        // current node
        Node cur = root;
        // will be used to track depth
        int i = 2;
        // if cur is null then insert failed
        while (cur != null){
            // look left
            if (cur.val > val){
                // if left is null then insert there
                if (cur.left == null){
                    cur.left = new Node(val);
                    // increment size
                    ++size;
                    // determine if depth changed
                    if (i > depth){
                        depth = i;
                    }
                    // insert success
                    return true;
                }
                // move cur left
                cur = cur.left;
            }
            // look right
            else{
                if (cur.right == null){
                    cur.right = new Node(val);
                    ++size;
                    if (i > depth){
                        depth = i;
                    }
                    return true;
                }
                cur = cur.right;
            }
            // increment depth
            ++i;
        }
        // insert failed
        return false;
    }

    // returns size
    int getSize(){
        return size;
    }

    // returns depth
    int getDepth(){
        return depth;
    }

    // returns node with target val
    Node search(double val){
        Node cur = root;
        while (cur != null){
            if (cur.val == val){
                return cur;
            }
            if (cur.val > val){
                cur = cur.left;
            }
            else{
                cur = cur.right;
            }
        }
        return null;
    }

    public class Node{

        // node val
        double val;

        // node to left and right
        Node left, right;

        // constructor passing val
        Node(double val){
            this.val = val;
            left = null;
            right = null;
        }
    }
}