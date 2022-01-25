import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * A class that handles user input by using various helper methods to break down the code into smaller fragments
 * in order to be structured and be more readable
 */
public class RosterUI {
    /** A scanner to read user input */
    private Scanner scan;
    /** A Student Binary tree structure object to store the students */
    private StudentBinaryTree tree;
    /** A student array that will hold the final roster */
    private Student[] arr;
    /** A Queue structure that will be used to store extra students need be */
    private StudentQueue que;
    /** A hashtable to quickly store students for searching by id */
    private StudentHashTable table;

    /**
     * This constructor creates the UI in command line
     * @param scan a scanner to read inputs
     * @param list a list structure, in this case a tree
     * @param que a queue to store students if there is no more space
     * @param table a hashtable to store the students to create a O(1) search time
     */
    public RosterUI(Scanner scan, StudentBinaryTree list,StudentQueue que,StudentHashTable table) {
        this.scan = scan;
        this.tree = list;
        this.que = que;
        this.table = table;
    }

    /**
     * This takes care of the menu that is associated with the user to use for commands
     * breaks out to separate methods that take care of each associated command
     * it always work with the main structure before it works with the sorted array
     */
    public void start() {
        while (true) {
            if(arr==null){
                System.out.println("Working with Tree");
                System.out.println("current size of tree" + tree.size());
            }else {
                System.out.println("Working with Array");
                System.out.println("current size of array" + arr.length);
            }
            if(arr==null) System.out.println("Load roster from a file");
            System.out.println("Add student");
            System.out.println("Remove Student");
            System.out.println("Search Student by Name or ID");
            System.out.println("Save");
            System.out.println("Exit");
            String input = scan.nextLine().toLowerCase();
            if (input.contains("load")) {
                loadFile();
            } else if (input.contains("add")) {
                addStudent();
            } else if (input.contains("remove")) {
                removeStudent();
            } else if (input.contains("search")) {
                search();
            } else if(input.contains("save")){
                save();
            }else if(input.contains("exit"))
                break;
            }

            if(que.isEmpty()){
                System.out.println("Currently no one in waiting list");
            }else{
                System.out.println("Currently " + que.size() + " in waiting list");
            }

    }

    /**
     * A helper method invoked by input command that user wants to do
     * method to load a file that is already created, stored the students into the
     * appropriate structure, in this case both the tree and table
     */
    private void loadFile() {
        System.out.print("name of file? ");
        String file = scan.nextLine();
        try (Scanner reader = new Scanner(Paths.get(file))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(" ");
                Student toPut = new Student(parts[0], parts[1], parts[2]);
                tree.insert(toPut);
                table.put(toPut);

            }
            System.out.println("file loaded successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }

    /**
     * A helper method invoked by input command that user wants to do
     * Method to add student, takes into account whether it is working with the sorted array or the main data structure
     * it is able to add to the main data structure, if there is no more space then it is added to the queue
     */
    private void addStudent() {
        System.out.print("Enter Student First Name: ");
        String firstName = scan.nextLine();
        System.out.print("Enter Student Last Name: ");
        String lastName = scan.nextLine();
        System.out.print("Enter ID: ");
        String ID = scan.nextLine();
        Student student =new Student(firstName, lastName, ID);
        if(arr==null) {
            if (tree.size() != 20) {
                tree.insert(student);
                table.put(student);
            } else {
                System.out.println("List is full, student is added to waiting list");
                que.enQue(student);
            }
        }else{
            insertSorted(arr,student);
        }
    }

    /**
     * A helper method invoked by input command that user wants to do
     * This method inserts a student to the main array while maintaining sorted order
     * if it is full, in this case 20 students are present then the student is added to the queue
     * @param arr - the main array that should maintain sorted order
     * @param student - the student that is to be added to the main array
     */
    private void insertSorted(Student[] arr, Student student){
        boolean check = false;
        if(arr.length==20){
            System.out.println("Array is full");
            que.enQue(student);
        }else{
            table.put(student);
            this.arr= new Student[arr.length + 1];
            int i = 0, j = 0;
                while(i<arr.length){
                    if (student.compareTo(arr[i])<0) {
                        this.arr[i] = student;
                        for (int k = i + 1; k < this.arr.length; k++)
                            this.arr[k] = arr[j++];
                        check = false;
                        break;
                    }
                    else
                        this.arr[i++] = arr[j++];
                        check = true;
                }
                if (check){
                    this.arr[this.arr.length-1]=student;
                }
        }
    }

    /**
     * A helper method invoked by input command that user wants to do
     * Method to remove the student, like the other methods it works with the main data structure at the beginning and
     * then with the sorted array
     * checks whether the student is present in said data structure before removing
     *
     */
    private void removeStudent() {
        System.out.print("Enter Student Name: ");
        String name = scan.nextLine();
        System.out.print("Enter ID: ");
        String ID = scan.nextLine();
        Student stu = new Student(name, ID);

        if (arr==null) {
            if (tree.search(stu)!=null) {
                tree.delete(stu);
                table.remove(stu.getID());
            }
            if(!que.isEmpty()){
                Student firstInLine = que.deQue();
                tree.insert(firstInLine);
                table.put(firstInLine);
            }

        } else {
            removeFromArray(arr,stu);
            table.remove(stu.getID());
            if(!que.isEmpty()){
                Student thisOne = que.deQue();
                insertSorted(this.arr,thisOne);
                table.put(thisOne);
            }
        }
    }

    /**
     * A helper method invoked by input command that user wants to do
     * method to remove from the array, a binary search is performed to find the location of the student in question
     * for removal
     * the approach of deleting is to replace at the position with everything else and then creating a new array with 1 less
     * after that the rest of the values are copied to the new array
     * @param arr - main sorted array that is to be searched for the student
     * @param stu - student to remove from the main array
     */
    private void removeFromArray(Student[] arr, Student stu) {
        int n = arr.length;
        int pos = binarySearchStudent(arr,stu);
        if(pos==-1){
            System.out.println("Not found");
        }else {

            for (int i = pos; i < n-1; i++) {  //n-1
                arr[i] = arr[i + 1];
            }
            this.arr = new Student[n-1];
            for(int i = 0 ; i< n-1; i++){
                this.arr[i] = arr[i];
            }

        }


    }

    /**
     * A helper method invoked by input command that user wants to do
     * Helper method for searching, if its working with main data structure then it is directed to use the appropriate
     * method.
     */
    private void search() {
        System.out.print("By name or id?");
        String input = scan.nextLine().toLowerCase();
        if (arr == null ) {
            searchInList(input);
        } else {
            searchInArray(input);
        }
    }

    /**
     * A helper method invoked by input command that user wants to do
     * This method searches in the array based on the input
     * @param input - input is the search option either id or name
     */
    private void searchInArray(String input) {

        if (input.contains("id")) {
            System.out.print("ID number: ");
            String ID = scan.nextLine();
            table.search(ID);
//            if(binarySearchByID(arr,ID)!=-1){
//                Student stu = arr[binarySearchByID(arr,ID)];
//                options(stu);
//            }else{
//                System.out.println("Not Found");
//            }
        }
        if (input.contains("name")) {
            System.out.print("First Name: ");
            String name = scan.nextLine();
            System.out.print("Last Name: ");
            String lName = scan.nextLine();
            if(binarySearchByName(arr, name,lName)!=-1){
                Student stut = arr[binarySearchByName(arr, name,lName)];
                options(stut);
            }else{
                System.out.println("Not Found");
            }
        }
    }

    /**
     *
     * a method that is not implemented but could be implemented to deal with the returns of the searches and removes
     * currently just prints out a menu with options for the student
     * @param stu - student that has options
     */
    private void options(Student stu) {
        System.out.println("What would you like to do with "+stu.getName() +"?");
        System.out.println("remove?");
        System.out.println("add to waiting list?");

    }

    /**
     * A helper method invoked by input command that user wants to do
     * Although some say it could not be done, this is binary search by id.
     * Students internal id is in the form of X000000 therefore if the numbers are unique
     * then it is basically able to be sorted by IDs since it is in the form of letter and numbers
     * @param arr- main arr to be searched
     * @param id- the id to be searched
     * @return - returns the position or -1 if not found
     * Runtime - O(logn)
     */
    private int binarySearchByID(Student[] arr, String id) {
        int low = 0;
        int high = arr.length - 1;
        while(low<=high){
            int mid = (low+high)/2;
            int compare = arr[mid].getID().compareTo(id);
            if (compare==0){
                return mid;
            }else if(compare<0){
                low = mid+1;
            }else{
                high = mid -1;
            }
        }
        return -1;
    }

    /**
     * bianry search by name mainly last name because that is the sorted order
     * @param arr - main array
     * @param name - first name of student
     * @param lName - last name of student
     * @return - returns position or -1 if not found
     * Runtime - O(logn)
     */
    private int binarySearchByName(Student[] arr, String name, String lName) {
        int low = 0;
        int high = arr.length -1;
        while(low<=high){
            int mid = (low+high)/2;
            int compare = arr[mid].getLastName().compareTo(lName);
            if (compare==0){
                if(arr[mid].getFirstName().compareTo(name)==0){
                    return mid;
                }
            }else if(compare<0){
                low = mid+1;
            }else{
                high = mid -1;
            }
        }
        return -1;
    }

    /**
     * general case of searching the array by a student.compareto(other)
     * @param arr - main array to search
     * @param student - student to search for
     * @return - returns the position of the student or -1 if not found
     * Runtime - O(logn)
     */
    private int binarySearchStudent(Student[] arr, Student student) {
        int low = 0;
        int high = arr.length -1;
        while(low<=high){
            int mid = (low+high)/2;
            int compare = arr[mid].compareTo(student);
            if (compare==0){
                return mid;
            }else if(compare<0){
                low = mid+1;
            }else{
                high = mid -1;
            }
        }
        return -1;
    }

    /**
     *
     * searches the main data structure in this case it is the tree
     * implemented the search to be strictly in the hash table because it will be faster O(1)
     * searched by id or name
     * @param input- chooses what to search by
     */
    private void searchInList(String input) {
        if (input.contains("id")) {
            System.out.print("ID number: ");
            String ID = scan.nextLine();
            table.search(ID);
//            Student searched = tree.searchByID(ID);
//            if (searched == null) {
//                System.out.println("Student not found");
//            } else {
//                options(searched);
//            }
        } else if (input.contains("name")) {
            System.out.print("Name: ");
            String name = scan.nextLine();
            Student searched = tree.searchByName(name);
            if (searched == null) {
                System.out.println("Student not found");
            } else {
                options(searched);
            }
        }
    }

    /**
     * save method that saves the main data structure to the sorted array
     * second save saves the current data in the array to a sorted roster list(output.txt)
     * a hashtable toString to see the table(hashtable.txt)
     * and a waiting list(waitinglist.txt) in case there is someone waiting
     */
    private void save(){
        if(arr==null){
            arr = tree.toArray();
        }else {
            try {
                PrintWriter writer = new PrintWriter("output.txt");
                PrintWriter waitwriter = new PrintWriter("waitinglist.txt");
                PrintWriter hashWriter= new PrintWriter("hashTable.txt");
                writer.println("Final Roster:");
                for (int i = 0; i < arr.length; i++) {
                    writer.println(arr[i]);

                }
                waitwriter.println("Waiting List:");
                while(!que.isEmpty()){
                    waitwriter.println(que.deQue());
                }
                hashWriter.println(table.toString());

                hashWriter.close();
                writer.close();
                waitwriter.close();
            }catch (Exception e){
                System.out.println("Try again");
            }
        }
    }
}



