package TPE_POO.paintPOO.src.backend.model;

public class Ellipse extends Rectangle {
    public Ellipse(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public String toString() {
        return String.format("Elipse [ %s, %s ]", super.getTopLeft(), super.getBottomRight());
    }
}
