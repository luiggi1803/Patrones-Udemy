package construccion.factory;

class Point {
    double x;
    double y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static class Factory {
        public static Point newCartesianPoint(double x, double y) {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theta) {
            return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }

}

public class FactoryMethod {

    public static void main(String[] args) {
        Point cartesianPoint = Point.Factory.newCartesianPoint(5, 2);
        Point polarPoint = Point.Factory.newPolarPoint(5, 2);

    }
}
