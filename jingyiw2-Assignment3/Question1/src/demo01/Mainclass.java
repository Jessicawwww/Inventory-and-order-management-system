package demo01;

public class Mainclass {
    public static void main(String[] args) {
        //initialize three objects
        Room myRoom = new Room();
        Dog myDog = new Dog();
        Controller myController = new Controller();

        //start the controller to draw figures
        myController.getStarted(myRoom,myDog);
    }
}