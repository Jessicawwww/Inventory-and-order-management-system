package demo01;

public class Room {
    int[][] roomFloor;
    public Room() {
        roomFloor = new int[20][20]; //initialize a 20-20 array and set the value = 0
    }

    public static void displayRoomFloor(int[][]floor){ //design a method to display the array
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                // walk through all element to print out the array
                if (floor[i][j]==1){
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            //wrap text around
            System.out.println("");
        }
        //System.out.println(" ");
    }
}