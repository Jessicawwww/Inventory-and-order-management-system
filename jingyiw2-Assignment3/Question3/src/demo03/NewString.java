package demo03;

import java.util.Locale;

public class NewString {

    private final char[] charList; //string is immutable
    private final int count;//store the length of the char array, charList.length, can be smaller when being substring
    private int hashCode; //cache the result of hashCode() in object class
    final int start; //store starting position for char array

    //constructor for initialize a char array with length = 0
    public NewString(){
        charList=new char[0];
        count=0;
        start=0;
    }

    //use char array to initialize
    public NewString(char[] list){
        charList=list;
        count=list.length;
        start=0;
    }

    //copy the contents of a string to a new one;
    //since it is immutable, only shallow copy is done
    public NewString(NewString s){
        //the parameter is an array of chars
        this.charList=s.charList; //assign the value of s to our charlist
        this.count=s.count;
        this.hashCode=s.hashCode;
        this.start=s.start;

    }

    //get the length of the array
    public int length() {
        return this.count;
    }

    //used to compare two string objects
    //return type is an int
    //first compare the corresponding character, if not equal, then return the difference between ASCII codes, and so on, for each char
    //if corresponding chars are all same, return the difference between their lengths
    public int compare(NewString s){
        int len1 = count;
        int len2 = s.count;
        int minLen = Math.min(len1,len2);

        char[] charList1 = charList;
        char[] charList2 = s.charList;

        int i = start;
        int j = s.start;

        //compare corresponding characters
        while (--minLen>=0){
            int result = charList1[i++] -charList2[j++];
            if (result!=0){
                return result;
            }
        }
        /*
        alternative expression
        if (i==j) {
            int pointer = i;
            int limit = minLen+i;
            while (pointer<limit){
                char char1 = charList1[pointer];
                char char2 = charList2[pointer];
                if (char1!=char2){
                    return char1-char2; //return the difference of unicode value
                }
                pointer++;
            }
        }
        else {
            while (minLen--!=0){
                char char1 = charList1[i++];
                char char2 = charList2[j++];
                if (char1!=char2){
                    return char1-char2;
                }
            }

        }
    */
        //else return the length difference
        return len1-len2;
    }

    //define a new way to initialize a string
    public NewString(char[] list, int start, int count){
        this.count = count;
        this.charList = new char[count];
        System.arraycopy(list,start,this.charList,0,count);
        this.start=0;

    }
/*
    public NewString(char value[], int offset, int count) {
        if (offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (count <= 0) {
            if (count < 0) {
                throw new StringIndexOutOfBoundsException(count);
            }
            if (offset <= value.length) {
                this.value = new char[0];
                return;
            }
        }
        if (offset > value.length - count) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        this.value = Arrays.copyOfRange(value, offset, count);
    }
  */

    //assume that begin is between 0 and count
    public NewString substring(int begin){
        if (begin==0){
            return this;
        }
        int len = count-begin;
        //copy the substring, rather than change the original one
        return new NewString(this.charList,begin+this.start,len);
    }

    public char charAt(int index){
        //assume the user only types in right index
        return charList[start+index];
    }

    public void ToString(){
        for (int i = 0; i < charList.length; i++) {
            System.out.print(charList[i]);
        }
        System.out.print("\n");
    }

    public NewString toUpperCase() {
        return this.toUpperCase(Locale.getDefault()); //set the locale as default
    }

    public NewString toUpperCase(Locale locale) {
        if (locale == null) {
            throw new NullPointerException();
        } //exception handling
        //new char array should be as long as the original one
        char[] newString = new char[count];
        //walk through each element in the original array and turn it into upper case
        for (int i = 0; i < count; i++) {
            char c = this.charAt(i); //extract each element
            newString[i] = Character.toUpperCase(c); //use toUpperCase method in character class to change each element
            /*
            alternative expression

            if (charList[i] >= 'a' && charList[i] <= 'z') {
                newString[i] = (char) (charList[i] - 32);
            } else {
                newString[i] = charList[i];
            }
            */
        }
        return new NewString(newString);
    }

    /**
     * to change the string to a char array list
     * @return use copy method to avoid user changes the string
     */

    public char[] toChars(){
        char[] list = new char[count];
        System.arraycopy(charList,0,list,0,count);
        return list;
    }


    public static NewString valueOf(boolean b) {
        char[] trueString = {'t','r','u','e'};
        char[] falseString = {'f','a','l','s','e'};
        return b? new NewString(trueString):new NewString(falseString);
    }
}




