package console_gui;

public class Test {
    public static void main(String[] args) {
        String toSplit = "how ; about";
        
        printArray(toSplit.split(" *;+ *"));
        
    }
    
    public static void printArray(String[] arr) {
        
        System.out.println("{");
        
        if (arr.length != 0) {
            for (int i = 0 ; i < arr.length; i++) {
                System.out.print(i);
                System.out.print(": ");
                System.out.print(arr[0]);
                System.out.print('\n');    
            }
        }

        System.out.println("}");
    }
}
