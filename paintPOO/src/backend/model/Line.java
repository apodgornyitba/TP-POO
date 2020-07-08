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
    public String toString() {
        return String.format("Linea [ %s, %s ]", startPoint, endPoint);
    }
}
