package TPE_POO.paintPOO.src.backend.model;


import javafx.scene.paint.Color;

public class Line extends Figure {

    private final Point startPoint, endPoint;

    public Line(Point startpoint, Point endPoint, Color borderColor, double lineWidth) {
        this.startPoint = startpoint;
        this.endPoint = endPoint;
        setBorderColor(borderColor);
        setLineWidth(lineWidth);
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    @Override
    public void move(double newX, double newY) {
        startPoint.setX(startPoint.getX() + newX);
        startPoint.setY(startPoint.getY() + newY);
        endPoint.setX(endPoint.getX() + newX);
        endPoint.setY(endPoint.getY() + newY);
    }

    @Override
    public boolean figureBelongs(Point eventPoint) {
        return false;
    }

    @Override
    public boolean figureBelongs(Point startPoint, Point endPoint) {
        return this.startPoint.getX() > startPoint.getX() && this.startPoint.getY() > startPoint.getY() && this.startPoint.getX() < endPoint.getX() && this.startPoint.getY() < endPoint.getY()
                && this.endPoint.getX() > startPoint.getX() && this.endPoint.getY() > startPoint.getY() && this.endPoint.getX() < endPoint.getX() && this.endPoint.getY() < endPoint.getY();
    }

    @Override
    public String toString() {
        return String.format("Linea [ %s, %s ]", startPoint, endPoint);
    }
}
