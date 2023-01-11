import java.io.*;
import java.util.*;
import java.lang.AutoCloseable;

public class CheckGroupingSymbol {
    public static void main(String[] args) {

        //check command line argument
        if (args.length !=1){
            System.out.println("There is no java code file in command line argument!");
            System.exit(0);
        }
        //call the method
        CheckGroupings(args[0]);
    }

    public static void CheckGroupings(String file) {
        try {
            //use scanner to read the java file
            File f = new File(file);
            Scanner sc = new Scanner(f);
            //use stack to store (,[,{
            Deque<Character> stack= new ArrayDeque<Character>();
            while(sc.hasNext()){
                String line = sc.nextLine();
                for (int i = 0; i< line.length(); i++){
                    char ch = line.charAt(i);
                    //push (,[,{ to the stack
                    if (ch == '(' || ch == '[' || ch == '{'){
                        stack.push(ch);
                    } else if (ch == ')' || ch == ']' || ch == '}' )
                    {
                        //find if there is a matching one
                        matchGroups(stack,ch);
                    }
                }
            }
            //if all (,[,{ are matched, then they are popped. In this way, the stack is empty, so the code is correct.
            System.out.println("The Java source file has "+(stack.isEmpty() ? "correct": "incorrect")+" pairs of grouping symbols.");

        } catch (FileNotFoundException e) {
            //in case that the user did not enter command line argument
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void matchGroups(Deque<Character> stack, Character ch){
        //System.out.println(stack.peek());
        //to check whether the character has its matching on the top of the stack
        if ((stack.peek() == '(' && ch == ')')|| (stack.peek() == '[' && ch == ']')|| (stack.peek() == '{' && ch == '}')){
            stack.pop(); //if matched, pop that item out
        }else if ((stack.peek() != '(' && ch == ')') ||
                (stack.peek() != '[' && ch == ']') ||
                (stack.peek() != '{' && ch == '}')) {
            System.out.println("The Java source file has incorrect pairs of grouping symbols."); //if there is no matching for one item, then incorrect pairs!
            System.exit(1);
        }
    }
}
