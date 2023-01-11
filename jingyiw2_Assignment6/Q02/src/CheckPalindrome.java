import java.util.*;

/**
 * uses a stack to determine whether a string is a palindrome (i.e., the string is spelled identically backward and forward).
 * The program should ignore spaces and punctuation
 */
public class CheckPalindrome {

    public static boolean isPalindrome(String string){
        Stack stack = new Stack();
        //use stack to reverse the string
        string = string.replaceAll("\\p{Punct}","");
        string = string.replaceAll("\\s","");
        for (int i = 0;i<string.length();i++){
            stack.push(string.charAt(i));
        }
        String reversedString = "";
        while(!stack.isEmpty()){
            reversedString += stack.pop();
        }
        System.out.println(string+"---"+reversedString);
        if (string.equals(reversedString))
            return true;
        else return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please input the test string: ");
        String testString = sc.nextLine();
        if(isPalindrome(testString)){
            System.out.println("The string is a palindrome");
        }else {
            System.out.println("The string is not a palindrome");
        }
    }
}
