import java.util.*;
/**
 * method to calculate the average speed
 */

public class SpeedCalculate {
    public static double getDist(WayPoint p1, WayPoint p2){
        double x_dist = p2.getX_coordinate()-p1.getX_coordinate();
        double y_dist = p2.getY_coordinate()-p1.getY_coordinate();
        return Math.sqrt(Math.pow(x_dist,2)+Math.pow(y_dist,2));
    }

    public static void getSpeed(ArrayList<WayPoint> route){
        double totalDist = 0;
        int totalTime=0;

        for (int i = 0; i< route.size()-1; i++){
            WayPoint currentPoint = (WayPoint) route.get(i);
            WayPoint nextPoint = (WayPoint) route.get(i+1);
            totalDist += getDist(currentPoint,nextPoint);
            if (currentPoint.getTime()>totalTime){
                totalTime = currentPoint.getTime();
            }
            if (nextPoint.getTime()>totalTime){
                totalTime = nextPoint.getTime();
            }
        }
        totalDist *=0.1;
        double speed = totalDist/(totalTime/3600);
        System.out.printf("The average speed of this hiker is %.3f miles per hour. ",speed);

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<WayPoint> route = new ArrayList<>();

        String flag = "y";
        while (flag.equalsIgnoreCase("y")){
            //get inputs from user
            System.out.print("Please input the X-coordinate: ");
            double x = sc.nextDouble();
            System.out.print("Please input the Y-coordinate: ");
            double y = sc.nextDouble();
            System.out.print("Please input the timestamp: ");
            int timestamp = sc.nextInt();
            //create an object based on input and add to the route list
            WayPoint wayPoint = new WayPoint(x,y,timestamp);
            route.add(wayPoint);

            System.out.print("Do you still want to add another way point? (y/n) ");
            flag = sc.next();

        }

        //get the average speed
        getSpeed(route);
    }
}
