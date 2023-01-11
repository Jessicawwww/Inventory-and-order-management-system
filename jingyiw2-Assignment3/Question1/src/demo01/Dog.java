package demo01;

public class Dog {
    int[] moveDirection;
    int[] location;
    //initialize the dog's initial move direction in constructor
    public Dog(){
        //1 for positive, and 1 for x-axis, {1,1} means moving forward along the x-axis
        //-1 for negative, and -1 for y-axis, {-1,-1} means moving negatively along the y-axis
        moveDirection=new int[]{1,1};
        //initialize dog's location, indexes of floor array
        location = new int[]{0,0};
    }
    //store dog's move direction
    public static int[] takeTurn(int command, int[] direction){
        //at every turn, change x and y; and when turning right
        if (command==3){
            if (direction[1] == 1){
                direction[0] = direction[0]*(-1);
            }

            direction[1] = direction[1]*(-1);
        }
        //at every turn, change x and y, and when turning left
        else {
            if (direction[1] == -1){
                direction[0] = direction[0]*(-1);
            }

            direction[1] = direction[1]*(-1);
        }
        return direction;
    }
    //move forward on the floor based on the step numbers and move direction
    //return the location after making a move
    public static int[] makeMove(int spaces, int[][] floor, int[] direction, int[] locate, int stats){
        int[] newlocation = new int[2];
        //when direction is moving along x-axis
        if (direction[1]==1 ){
            if (stats==2) {
                for (int i = 1; i <= spaces; i++) {
                    floor[locate[0]][locate[1]+(direction[0])*i]=1;
                }
            }
            newlocation[0] = locate[0];
            newlocation[1] = locate[1]+(direction[0])*spaces;
            return newlocation;
        }
        //else when direction is moving along y-axis
        else {
            if (stats==2) {
                for (int j = 1; j <= spaces; j++) {
                    floor[locate[0]-(direction[0])*j][locate[1]]=1;
                }
            }
            newlocation[0] = locate[0]-(direction[0])*spaces;
            newlocation[1] = locate[1];
            return newlocation;
        }
    }
}
