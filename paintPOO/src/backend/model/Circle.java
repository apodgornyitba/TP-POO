package TPE_POO.paintPOO.src.backend.model;

import javafx.scene.paint.Color;

public class Circle extends Figure {

    private final Point centerPoint;
    private final double radius;

    public Circle(Point centerPoint, double radius, Color borderColor, Color insideColor, double lineWidth) {
        this.centerPoint = centerPoint;
        this.radius = radius;
        setBorderColor(borderColor);
        setInsideColor(insideColor);
        setLineWidth(lineWidth);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean figureBelongs(Point eventPoint) {
        return centerPoint.distanceTo(eventPoint) <= radius;
    }

    @Override
    public boolean figureBelongs(Point startPoint, Point endPoint) {
        return startPoint.getY() < centerPoint.getY() - getRadius() && endPoint.getY() > centerPoint.getY() + getRadius()
                && startPoint.getX() < centerPoint.getX() - getRadius() && endPoint.getX() > centerPoint.getX() + getRadius();
    }

    @Override
    public void move(double newX, double newY) {
        centerPoint.move(newX, newY);
    }
}
