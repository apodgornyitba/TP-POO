package TPE_POO.paintPOO.src.backend.model;


public class Line extends Figure {

    private final Point startPoint, endPoint;

    public Line(Point startpoint, Point endPoint) {
        this.startPoint = startpoint;
        this.endPoint = endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    @Override
    public void move(double newX, double newY) {
        startPoint.x += newX;
        startPoint.y += newY;
        endPoint.x += newX;
        endPoint.y += newY;
    }

    @Override
    public String toString() {
        return String.format("Linea [ %s, %s ]", startPoint, endPoint);
    }
}
