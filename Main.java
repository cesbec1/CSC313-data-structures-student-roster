import java.util.Scanner;
/**
 * main class that creates instances of the classes for the data to be processed
 */
public class Main {
    public static void main(String[] args) {

        StudentHashTable table = new StudentHashTable();
        Scanner scan = new Scanner(System.in);
        StudentBinaryTree tree = new StudentBinaryTree();
        StudentQueue q = new StudentQueue();
        RosterUI ui = new RosterUI(scan,tree,q,table);
        ui.start();




    }
}
