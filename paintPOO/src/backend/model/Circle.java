package TPE_POO.paintPOO.src.backend.model;

import javafx.scene.paint.Color;

public class Circle extends Figure {

    protected final Point centerPoint;
    protected final double radius;

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
    public void move(double newX, double newY) {
        centerPoint.setX(centerPoint.getY() + newX);
        centerPoint.setY(centerPoint.getY() + newY);
    }
}
