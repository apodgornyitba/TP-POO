package TPE_POO.paintPOO.src.backend.model;

import javafx.scene.paint.Color;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight, Color borderColor, Color insideColor, double lineWidth) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        setBorderColor(borderColor);
        setInsideColor(insideColor);
        setLineWidth(lineWidth);
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public double base(){
        return Math.abs(topLeft.getX() - bottomRight.getX());
    }

    public double height(){
        return Math.abs(topLeft.getY() - bottomRight.getY());
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public boolean figureBelongs(Point eventPoint) {
        return eventPoint.getX() > topLeft.getX() && eventPoint.getX() < bottomRight.getX() &&
                eventPoint.getY() > topLeft.getY() && eventPoint.getY() < bottomRight.getY();
    }

    @Override
    public boolean figureBelongs(Point startPoint, Point endPoint) {
        return startPoint.getX() <= topLeft.getX() && bottomRight.getX() <= endPoint.getX()
                && startPoint.getY() <= topLeft.getY() && endPoint.getY() >= bottomRight.getY();
    }

    @Override
    public void move(double newX, double newY) {
        topLeft.setX(topLeft.getX() + newX);
        topLeft.setY(topLeft.getY() + newY);
        bottomRight.setX(bottomRight.getX() + newX);
        bottomRight.setY(bottomRight.getY() + newY);
    }
}
