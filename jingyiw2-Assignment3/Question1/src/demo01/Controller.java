package demo01;

import java.util.ArrayList;
import java.util.Scanner;
public class Controller {
    // scanner to take input from users
    public static Scanner input = new Scanner(System.in);

    //dynamic array to store user's inputs
    public static ArrayList<Integer> commands = new ArrayList<Integer>();
    // call this method to start the controller and take inputs from user
    public void getStarted(Room myRoom, Dog myDog) {
        int status = 1;
        // initial status 1: turn the pen up
        commands.add(status);
        // UI
        System.out.println("Hi, the command list is as follows:\n" +
                "---------------------------------------------------\n"+
                "1 - Pen up\n"+
                "2 - Pen down\n"+
                "3 - Turn right\n"+
                "4 - Turn left\n"+
                "5, 10 - Move forward 10 spaces (10 can be replaced)\n"+
                "6 - Display the 20-20 array\n"+
                "9 - End\n"+
                "----------------------------------------------------------"
        );
        // read inputs until the user calls 9 to stop it
        while (commands.get(commands.size() - 1) != 9){
            int newCommand = 0;
            int currentCommand = commands.get(commands.size() - 1);
            // if user inputs 5, the program takes further information about forward numbers
            // in case that the forward spaces is also 5, check if the previous number is 5
            if ((commands.size()>=2)  && (currentCommand == 5) && (commands.get(commands.size()-2) != 5) ) {

                System.out.print("Enter forward spaces: ");
                newCommand = input.nextInt();
                commands.add(newCommand);

                myDog.location = Dog.makeMove(newCommand, myRoom.roomFloor, myDog.moveDirection,myDog.location,status);

            } else if (currentCommand == 6){
                // if the user types in 6, then call the display method
                Room.displayRoomFloor(myRoom.roomFloor);

            }
            else
            {
                //if 3/4, call the turn method
                if ((currentCommand ==3) || (currentCommand ==4)){
                    myDog.moveDirection = Dog.takeTurn(currentCommand,myDog.moveDirection);
                }
            }
            // still take input from user
            System.out.print("Enter command (9 to end input): ");
            newCommand = input.nextInt();
            commands.add(newCommand);

            if (newCommand == 2){
                status=2;
            }
            else if (newCommand==1){
                status=1;
            }
        }
        //input 9 and exit the program
        System.out.println("Commands received, thank you :)");
        System.exit(0); // user inputs 9 and exits the whole program

    }
}
