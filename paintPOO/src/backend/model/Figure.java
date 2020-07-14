package TPE_POO.paintPOO.src.backend.model;

import TPE_POO.paintPOO.src.backend.DrawableFigure;
import javafx.scene.paint.Color;

public abstract class Figure implements DrawableFigure {
    private Color borderColor, insideColor;
    private double lineWidth;

    public abstract void move(double newX, double newY);
    public abstract boolean figureBelongs(Point eventPoint);
    public abstract boolean figureBelongs(Point startPoint, Point endPoint);

    public Color getBorderColor() {
        return borderColor;
    }
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getInsideColor() {
        return insideColor;
    }
    public void setInsideColor(Color insideColor) {
        this.insideColor = insideColor;
    }

    public double getLineWidth() {
        return lineWidth;
    }
    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }
}
