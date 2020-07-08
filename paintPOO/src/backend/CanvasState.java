package TPE_POO.paintPOO.src.backend;

import TPE_POO.paintPOO.src.backend.model.Figure;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class CanvasState {

    private final List<Figure> list = new ArrayList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }
}
