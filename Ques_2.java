import java.util.*;

public class Ques_2 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        try {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int value = it.next();
                if (value==20) {
                    list.remove(Integer.valueOf(value));
                   list.add(40);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println(" ");
            System.out.println("Caught ConcurrentModificationException: " + e.getMessage());
        }

        // Corrected method-
        Iterator<Integer> safeit = list.iterator(); 
        while (safeit.hasNext()) { 
            int value = safeit.next(); 
            if (value==20) { 
                safeit.remove();   
            } 
        } 
        System.out.println("List after safe iteration and removal: " + list);
    }
}
