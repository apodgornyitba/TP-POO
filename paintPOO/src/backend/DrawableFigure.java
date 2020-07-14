package TPE_POO.paintPOO.src.backend;

import javafx.scene.canvas.GraphicsContext;

@FunctionalInterface
public interface DrawableFigure {
    void reDraw(GraphicsContext gc);
}
