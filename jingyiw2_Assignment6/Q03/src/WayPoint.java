/**
 * class for storing way points
 */

public class WayPoint {
    double x_coordinate,y_coordinate;
    int time; //unit: seconds

    public WayPoint(double x_coordinate,double y_coordinate,int time){
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.time = time;
    }

    public double getX_coordinate() {
        return x_coordinate;
    }

    public void setX_coordinate(double x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public double getY_coordinate() {
        return y_coordinate;
    }

    public void setY_coordinate(double y_coordinate) {
        this.y_coordinate = y_coordinate;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
