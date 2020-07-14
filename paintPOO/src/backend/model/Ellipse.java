package TPE_POO.paintPOO.src.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Rectangle {
    public Ellipse(Point startPoint, Point endPoint, Color borderColor, Color insideColor, double lineWidth) {
        super(startPoint, endPoint, borderColor, insideColor, lineWidth);
    }

    @Override
    public String toString() {
        return String.format("Elipse [ %s, %s ]", super.getTopLeft(), super.getBottomRight());
    }

    @Override
    public void reDraw(GraphicsContext gc) {
        gc.strokeOval(super.getTopLeft().getX(), super.getTopLeft().getY(), super.base(), super.height());
        gc.fillOval(super.getTopLeft().getX(), super.getTopLeft().getY(), super.base(), super.height());
    }
}
