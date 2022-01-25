/**A student binary tree data structure to store the students*/

public class StudentBinaryTree {
    /**
     * A different type of node that holds the student data and pointers to left or right
     */
    private TNode root;
    /**
     * A count to keep track of how many students are in the tree
     */
    private int count = 0;
    /**
     * A student array that will be passed to the main program to be the final roster
     */
    Student[] arr;

    /**
     * Method returning the size of the tree
     */
    public int size() {
        return count;
    }

    /**
     * Method to check whether the tree is empty or not
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Method to sort in order and print
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * Helper method to print in order
     * Runtime-O(n)
     * @param tree the root of the tree
     */
    private void inOrder(TNode tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.println(tree.data);
            inOrder(tree.right);
        }
    }

    /**
     * Method to sort in pre order and print
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * Helper method to print pre order
     * Runtime-O(n)
     * @param tree the root of the tree
     */
    private void preOrder(TNode tree) {
        if (tree != null) {
            System.out.println(tree.data);
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /**
     * Method to sort in pre order and print
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * Helper method to print post order
     * Runtime-O(n)
     * @param tree the root of the tree
     */
    private void postOrder(TNode tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.println(tree.data);
        }
    }

    /**
     * Method to insert a new student to the tree
     * @param student student to be inserted into the tree
     */
    public void insert(Student student) {

        root = insert(student, root);
        count++;

    }

    /**
     * Helper method to insert into the tree
     * Runtime-O(logn)
     * @param student student to insert
     * @param tree the root of the tree
     * @return the new complete tree
     */
    private TNode insert(Student student, TNode tree) {
        if (tree != null) {
            if (student.compareTo(tree.data) < 0) {
                //System.out.println("going left");
                tree.left = insert(student, tree.left);
            } else if (student.compareTo(tree.data) > 0) {
                //System.out.println("going right");
                tree.right = insert(student, tree.right);
            }
        } else {
            //System.out.println("creating node");
            return new TNode(student);
        }
        //System.out.println("returning the tree");
        return tree;
    }

    /**
     * Method to find the max of the tree
     * Runtime-O(logn)
     * @return the max or null
     */
    public Student findMax() {
        if (root == null) {
            System.out.println("Empty Tree");  //maybe handle in main
            return null;
        }
        TNode tree = root;
        while (tree.right != null) {
            tree = tree.right;
        }
        return tree.data;
    }

    /**
     * Method to find the max of the tree
     * Runtime-O(logn)
     * @param sideOfTree which side from the root either right or left side and checks max on the appropriate side
     * @return the max of the particular side
     */
    public Student findMax(TNode sideOfTree) {
        TNode tree = sideOfTree;
        while (tree.right != null) {
            tree = tree.right;
        }
        return tree.data;
    }

    /**
     * Method to find the min of the tree
     * Runtime-O(logn)
     * @return returns the min
     */
    public Student findMin() {
        if (root == null) {
            System.out.println("Empty Tree");
        }
        TNode tree = root;
        while (tree.left != null) {
            tree = tree.left;
        }
        return tree.data;
    }

    /**
     * Method to find the min of the tree
     * Runtime-O(logn)
     * @param side is the side of the tree, maybe you want to check the left side of the root for the min or the right
     *             side from the root
     * @return the student with min lexicographic value
     */
    public Student findMin(TNode side) {
        TNode tree = side;
        while (tree.left != null) {
            tree = tree.left;
        }
        return tree.data;
    }

    /**
     * Method to delete a student
     * @param student the student to be deleted
     * Runtime- O(logn)
     */
    public void delete(Student student){
        if (isEmpty()){
            System.out.println("Empty tree");
        }else{
            delete(student,root);
            count--;
        }

    }

    /**
     * Helper method to the delete
     * @param student Student to delete
     * @param tree the root node of the tree
     *Runtime-O(logn)
     */
    private TNode delete(Student student, TNode tree) {

            if (tree == null) {
                return null;
            }
            if (student.compareTo(tree.data) < 0) {
                tree.left = delete(student, tree.left);
            } else if (student.compareTo(tree.data) > 0) {
                tree.right = delete(student, tree.right);
            } else {
                if (tree.right == null) {
                    return tree.left;
                }
                if (tree.left == null) {
                    return tree.right;
                }


                System.out.println(tree.data);
                System.out.println(findMax(tree.left));
                tree.data = findMax(tree.left);
                tree.left = delete(tree.data, tree.left);

            }


            return tree;

    }

    /**
     * Method to search for a student in the tree
     * @param student- student to search for
     * @returns the student or null if not found
     */
    public Student search(Student student){
        if (isEmpty()){
            System.out.println("Empty");
            return null;
        }
        return search(student,root);
    }

    /**
     * Helper method where the action takes place
     * @param student - student to search
     * @param tree - the root node of the tree
     * @return the student being searched for else null
     *Runtime-O(logn)
     */
    private Student search(Student student, TNode tree){
        if(tree==null){
            System.out.println("not found");
            return null;
        }
        if (student.compareTo(tree.data)<0){
            return search(student,tree.left);
        }
        if (student.compareTo(tree.data)>0){
            return search(student,tree.right);
        }
        return tree.data;
    }

    int i = 0;
  //need the toArray method

    /**Method that returns the array representation of the tree
     * is uses helper methods to store in the array and then return
     */
    public Student[] toArray(){
        arr = new Student[size()];
        storeInOrder();
        return arr;
    }
    /**method to store into the array*/
    public void storeInOrder(){
        storeInOrder(root);
    }

    /**stores in similar fashion as in order printing
     * Runtime- O(n)
     * @param tree - the root node of the tree
     */
    private void storeInOrder(TNode tree){
        if(tree!=null){
            storeInOrder(tree.left);
            arr[i++] =tree.data;
            storeInOrder(tree.right);
        }
    }

    /**
     * search the student by name in the tree
     * @param id - String representation of id to be searched
     * @return - student being searched for else null
     */
    public Student searchByID(String id) {
        if (isEmpty()){
            System.out.println("Empty");
            return null;
        }
        return searchByID(id,root);
    }

    /**
     * searches the tree with a given String Id
     * @param id the string representation of the id to be searched
     * @param tree the root of the tree
     * @return if found the student that has the id or null
     * Runtim- O(logn)
     */
    private Student searchByID(String id, TNode tree){
        if(tree==null){
            System.out.println("not found");
            return null;
        }
        if (id.compareTo(tree.data.getID())<0){
            return searchByID(id,tree.left);
        }
        if (id.compareTo(tree.data.getID())>0){
            return searchByID(id,tree.right);
        }
        return tree.data;
    }

    /**
     * search the tree with a given student name
     * @param name the name of the student
     * @return the student being searched for or null
     */
    public Student searchByName(String name) {
        if (isEmpty()){
            System.out.println("Empty");
            return null;
        }
        return searchByName(name,root);
    }

    /**
     * helper method to searching by the name
     * @param name name of the student
     * @param tree the root of the tree so we are able to search
     * @return the student or null
     * Runtime- O(logn)
     */
    private Student searchByName(String name, TNode tree){
        if(tree==null){
            System.out.println("not found");
            return null;
        }
        if (name.compareTo(tree.data.getName())<0){
            return searchByName(name,tree.left);
        }
        if (name.compareTo(tree.data.getName())>0){
            return searchByName(name,tree.right);
        }
        return tree.data;
    }
}