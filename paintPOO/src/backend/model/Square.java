package TPE_POO.paintPOO.src.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Rectangle{
    public Square(Point topLeft, Point bottomRight, Color borderColor, Color insideColor, double lineWidth) {
        super(topLeft, bottomRight, borderColor, insideColor, lineWidth);
    }

    public double Side(){
        return super.getBottomRight().getX() - super.getTopLeft().getX();
    }

    @Override
    public String toString() {
        Point bottom = new Point(super.getBottomRight().getX(), super.getTopLeft().getY() + (super.getBottomRight().getY() - super.getTopLeft().getY()));
        return String.format("Cuadrado [ %s, %s ]", super.getTopLeft(), bottom);
    }

    @Override
    public void reDraw(GraphicsContext gc) {
        double aux = Side();
        gc.strokeRect(super.getTopLeft().getX(),super.getTopLeft().getY(),aux,aux);
        gc.fillRect(super.getTopLeft().getX(),super.getTopLeft().getY(),aux,aux);
    }
}
