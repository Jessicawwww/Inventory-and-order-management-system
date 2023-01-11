import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        //users create loan objects
        Loan loan1 = new Loan();//a default one
        Loan loan2 = new Loan(6.4,5,10000);
        Loan loan3 = new Loan(7.0,8,5000);

        try{
            //create an output stream for Loan.dat
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Loan.dat"));
            //write the object to the file
            oos.writeObject(loan1);
            oos.writeObject(loan2);
            oos.writeObject(loan3);
            //release resource
            oos.close();

            //create an input stream
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Loan.dat"));
            //use its readObject method to store the objects in the file
            while (true){
                Loan l = (Loan)ois.readObject();
                System.out.println(l);
                System.out.printf("Total Loan Amount: %4.2f",l.getTotalPayment());
                System.out.println();
            }
        }catch (EOFException e){
            e.getStackTrace();
        }
    }
}
