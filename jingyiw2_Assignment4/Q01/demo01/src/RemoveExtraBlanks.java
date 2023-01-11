
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.*;
import java.util.*;


public class RemoveExtraBlanks {
    public static void main(String[] args) {
        try{
            //take the file input from user
            Scanner sc = new Scanner(System.in);
            System.out.print("Provide the file name： ");
            String fileName = sc.next();
            //generate new file
            String newFileName = fileName.split("\\.")[0]+"Temp."+fileName.split("\\.")[1];
            while(new File(newFileName).exists()){
                int num = 1;
                newFileName = newFileName+num;
                num++;
            }
            //remove extra blanks
            RemoveBlanks(fileName,newFileName);
            //copy the content to the orginal file
            DeleteTempFile(newFileName,fileName);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public static void RemoveBlanks(String file, String newFile) throws IOException,FileNotFoundException {
        try {
            //read files
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(newFile);
            BufferedWriter bw = new BufferedWriter(fw);
            String line;
            //make sure that we ignore any empty lines
            while ((line = br.readLine())!=null){
                if (line.trim().length()>0){
                    //remove extra blanks
                    line = line.trim().replaceAll("\\s+"," ")+" ";
                }
                bw.write(line);
            }
            br.close();
            bw.close();

        }catch(FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        catch (IOException e){
            e.printStackTrace();
            throw e;
        }
    }

    public static void DeleteTempFile(String newFile, String file) throws IOException,FileNotFoundException {
        try{
            //open files
            FileReader fin = new FileReader(newFile);
            FileWriter fout = new FileWriter(file, false);
            //write content to the original file
            int c;
            while ((c = fin.read()) != -1) {
                fout.write(c);
            }
            fin.close();
            fout.close();
            //delete the temp file
            File tempFile = new File(newFile);
            tempFile.delete();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
