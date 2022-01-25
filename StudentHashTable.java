/**
 * A wrapper class for the array that represents the array also known as HashTable
 * Used this to make my main more clean instead of adding these methods to it
 */

public class StudentHashTable {
    /**
     * An Array of nodes that can store the Students based on index
     */
    private LNode[] arr;

    /**
     * init method, setting an array from 0 - 99
     */
    public StudentHashTable(){
        arr = new LNode[100];

    }

    /**
     * method to add to the array based on the id%100 to get the index to know where to store the student
     * @param student the student to be stored
     * Runtime- O(1) at the index and thats it, if there is a collision the .next property of the node adds it at the same location
     *                but at the .next of the node
     */
    public void put(Student student){
        int id = Integer.parseInt(student.getID().substring(1,7));
        int index = id%100;
        if(arr[index]==null){                   //case where there is nothing at the array index
            arr[index] = new LNode(student);    //just add a new node there
        }else if(arr[index]!=null){
                LNode curr = arr[index];
            while(curr.next !=null){            //traverse across the table to find the next null
                curr = curr.next;
            }
            curr.next = new LNode(student);     //instead of null now itll be a new node
        }
    }

    /**
     * removes a student from the table
     * @param id - used to find the index of where the student is located
     * Runtime- O(1) checks the index for it
     */
    public void remove(String id) {
        int sid = Integer.parseInt(id.substring(1, 7));
        int index = sid % 100;
        if (arr[index] != null) {
            LNode current = arr[index];
            LNode prev = null;
            if (current != null && current.data.getID().equals(id)) {   //if the first node is the node to remove then just replace with the next one
                arr[index] = current.next;
                return;
            }

            while (current != null && !current.data.getID().equals(id)) {  //traversing the nodes and if found it gets switched
                prev = current;
                current = current.next;
            }
            if (current == null) {                  //if the at the end of the nodes is null then it was not found
                System.out.println("not found");
                return;
            }
            prev.next = current.next;           //doing the switching
        }
    }

    /**
     * searches for the id
     * @param id id to search for the index
     * Runtime- O(1)
     */
    public void search(String id){
        int sid = Integer.parseInt(id.substring(1,7));
        int index = sid%100;
        if (arr[index] != null) {
            LNode current = arr[index];
            while (current != null) {               //traversing the nodes and if it is found then we return
                if (current.data.getID().equals(id)) {
                    System.out.println(current.data + " was found ");
                    return;
                }
                current = current.next;
            }
        } else{
            System.out.println(id + " not found, no object at index " + index); // case where it was not found
        }
    }

    /**
     * String representation of the table
     * @return the string representation of the table
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("");
        for(int i = 0; i < arr.length; i++){
            if(arr[i]!=null){
                LNode curr = arr[i];
                builder.append("at index-->"+ i+" ");
                while(curr !=null){
                    builder.append(" "+curr.data);
                    curr = curr.next;
                }
                builder.append("\n");
            }
        }
        return builder.toString();
    }




}
