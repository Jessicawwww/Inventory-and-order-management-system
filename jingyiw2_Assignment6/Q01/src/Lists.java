import java.util.*;

/**
 * Write a test program that stores 10 million integers in ascending order (i.e., 1,2, . . ., 10m) in an ArrayList and a LinkedList,
 * displays the execution time taken to traverse the list,
 * and searches for the 10 millionth element using the get(index) vs using the iterator method.
 */
public class Lists {
    public static void main(String[] args) {
        //stores 10 million ints in an ArrayList
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0;i<250000 ;i++){ //10000000
            arrayList.add(i);
        }
        //store 10 million ints in a linked list
        LinkedList<Integer> linkedList = new LinkedList<>(arrayList);

        //display time to traverse the linked list
        int last_element1 = arrayList.get(arrayList.size()-1);

        long timeStart1 = System.currentTimeMillis();
        Iterator it = arrayList.iterator();
        while(it.hasNext()){
            // next() return the next element in the iteration
            it.next();
            if((int) it.next() == last_element1) {
                System.out.println(last_element1);
            }
        }
        long timeEnd1 = System.currentTimeMillis();
        System.out.println("Time for an array list to search for the last element using an iterator: "+ (timeEnd1 - timeStart1) + " millis");

        long timeStart3 = System.currentTimeMillis();
        for (int i = 0;i<250000;i++){
            arrayList.get(i);
            if (arrayList.get(i)==last_element1){
                System.out.println(arrayList.get(i));
            }
        }
        long timeEnd3 = System.currentTimeMillis();
        System.out.println("Time for an array list to search for the last element using get index: "+ (timeEnd3 - timeStart3) + " millis");

        long timeStart5 = System.currentTimeMillis();
        while(it.hasNext()){
            // next() return the next element in the iteration
            it.next();
        }
        long timeEnd5 = System.currentTimeMillis();
        System.out.println("Time for an array list to traverse a array list using an iterator: "+ (timeEnd5 - timeStart5) + " millis");

        //display time to traverse the linked list
        int last_element2 = linkedList.get(linkedList.size()-1);

        long timeStart2 = System.currentTimeMillis();
        Iterator it2 = linkedList.iterator();
        while(it2.hasNext()){
            it2.next();
            // next() return the next element in the iteration
            if(last_element2 == (int) it2.next()){
                System.out.println(last_element2);
            }
        }
        long timeEnd2 = System.currentTimeMillis();
        System.out.println("Time for a linked list to and search for the last element using an iterator: "+ (timeEnd2 - timeStart2) + " millis");

        long timeStart4 = System.currentTimeMillis();
        for (int i = 0;i<250000;i++){
            linkedList.get(i);
            if (linkedList.get(i)==last_element2){
                System.out.println(linkedList.get(i));
            }
        }
        long timeEnd4 = System.currentTimeMillis();
        System.out.println("Time for a linked list to search for the last element using get index: "+ (timeEnd4 - timeStart4) + " millis");

        long timeStart6 = System.currentTimeMillis();
        while(it2.hasNext()){
            it2.next();
        }
        long timeEnd6 = System.currentTimeMillis();
        System.out.println("Time for a linked list to traverse a linked list and searching for the last element using an iterator: "+ (timeEnd6 - timeStart6) + " millis");

    }
}
