import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * class for calculating the letter frequency in a text file
 */

public class LetterFreqCalc {
    public static void getFrequency(String text){
        //to store all chars that appeared
        ArrayList<Character>  chars = new ArrayList<>();
        //store frequency mapping
        Map<Character,Integer> frequencyPair = new HashMap<>();

        int length=0;
        Character c;
        //go through every char in the string
        for (int i = 0;i<text.length();i++) {
            c = text.charAt(i);
            if (c>='a' && c<='z'){
                //if new char
                if (!chars.contains(c)){
                    chars.add(c);
                    frequencyPair.put(c,1);
                }else { //add one to the current number
                    frequencyPair.put(c,frequencyPair.get(c)+1);
                }
                length+=1;
            }
        }
        //print out the results
        Collections.sort(chars);
        System.out.println("In this file, of all "+length+" letters,");
        for (int i=0;i<chars.size();i++){
            System.out.printf(chars.get(i)+": "+frequencyPair.get(chars.get(i))+"; Frequency: %.2f",(double)frequencyPair.get(chars.get(i))/length);
            System.out.println();
        }

    }


    public static void main(String[] args) {
        //take the file name
        Scanner sc = new Scanner(System.in);
        System.out.print("Please input the file name: ");
        String fileName = sc.nextLine();

        File file = new File("src/"+fileName+".txt");
        String encoding = "utf-8";
        if (file.isFile() && file.exists()) {
            try {
                Scanner scanner = new Scanner(file, encoding);
                StringBuffer sb = new StringBuffer();
                while (scanner.hasNextLine()) {
                    sb.append(scanner.nextLine()).toString();
                }
                //get file content as a string
                String fileText = sb.toString().toLowerCase();
                //fileText = fileText.replaceAll("\\p{Punct}","");
                //use the method to get frequency pairs
                getFrequency(fileText);
                //System.out.println(fileText);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("The file name is invalid");
            }
        }
    }
}
