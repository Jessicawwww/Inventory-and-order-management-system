package demo03;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class TestMain {
    public static void main(String[] args) {
        char[] testString = {'t','e','s','t',':','H','e','l','l','o',',','W','o','r','l','d','!'};
        //use char array to initialize the newString object here
        NewString string1 = new NewString(testString);

        //get the string's length
        int length1 = string1.length();
        System.out.print("The length of the string1 is "+length1+"\n");

        //use string1 to initialize string2
        NewString string2 = new NewString(string1);
        //compare string1 and string2
        System.out.print("the result of comparing string1 and string2 is "+string1.compare(string2)+"\n");

        //initialize string3
        NewString string3 = string1.substring(5);
        //compare string1 and string3
        System.out.print("the result of comparing string1 and string3 is "+string1.compare(string3)+"\n");

        //upper case transform
        NewString string4 = string3.toUpperCase();
        System.out.print("the upper case version for string3 is ");
        string4.ToString();
        //change it to char array
        char[] list3 = string3.toChars();
        System.out.print("the char array for string3 is ");
        for (int i = 0; i < list3.length; i++) {
            System.out.print(list3[i]);
        }
        System.out.print("\n");

        //value of a boolean
        boolean bool1 = true;
        boolean bool2 = false;

        System.out.print("the value of bool1 is ");
        NewString.valueOf(bool1).ToString();
        System.out.print("the value of bool2 is ");
        NewString.valueOf(bool2).ToString();

    }
}
