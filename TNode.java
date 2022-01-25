/**
 * Node container class for the BST
 * Contains the student data with pointers to the right and left of it
 */
public class TNode {
    Student data;
    TNode left, right;
    public TNode(Student student){
        data = student;
        left = right = null;
    }
}
