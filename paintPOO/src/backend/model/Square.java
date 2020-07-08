package TPE_POO.paintPOO.src.backend.model;

public class Square extends Rectangle{
    public Square(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }

    public double Side(){
        return super.getBottomRight().getX() - super.getTopLeft().getX();
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s, %s ]", super.getTopLeft(), super.getBottomRight());
    }
}
