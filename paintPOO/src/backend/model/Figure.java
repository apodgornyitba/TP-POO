package TPE_POO.paintPOO.src.backend.model;

import javafx.scene.paint.Color;

public abstract class Figure {
    private Color borderColor, insideColor;
    private double lineWidth;

    public abstract void move(double newX, double newY);

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
