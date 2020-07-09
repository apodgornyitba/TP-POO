package TPE_POO.paintPOO.src.backend;

import TPE_POO.paintPOO.src.backend.model.Figure;
import TPE_POO.paintPOO.src.backend.model.Point;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CanvasState {
    //Lista de figuras dibujadas
    private final LinkedList<Figure> list = new LinkedList<>();

    //Lista de figuras seleccionadas
    private final List<Figure> multipleSelection = new ArrayList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    //Iteradores sobre las listas
    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }
    public Iterable<Figure> selectedFigures(){
        return new ArrayList<>(multipleSelection);
    }

    //Define y agrega las figuras seleccionadas
    public void selection(Point startPoint, Point endPoint){
        if(startPoint.equals(endPoint)){
            Figure selected = null;
            for (Figure figure : figures()) {
                if (figure.figureBelongs(startPoint)){
                    selected = figure;
                }
            }
            if(selected != null){
                multipleSelection.add(selected);
            }
        } else {
            for (Figure figure : figures()) {
                if(figure.figureBelongs(startPoint, endPoint)){
                    multipleSelection.add(figure);
                }
            }
        }
    }

    //Retorna true si figure esta en la lista de seleccionados
    public boolean selectionContains(Figure figure){
        return multipleSelection.contains(figure);
    }

    //Retorna true si la lista esta vacia, false en caso contrario
    public boolean selectionIsEmpty(){
        return multipleSelection.isEmpty();
    }

    //Vacia la lista de seleccionados
    public void selectionClear(){
        multipleSelection.clear();
    }

    //Elimina las figuras del canvas
    public void deleteSelection(){
        if(!selectionIsEmpty()){
            for (Figure figure : selectedFigures()) {
                list.remove(figure);
            }
        }
    }

    //Mueve todas las figuras de la selección
    public void moveSelection(double newX, double newY){
        for (Figure figure : multipleSelection) {
            figure.move(newX, newY);
        }
    }

    public void changeBorderColor(Color color){
        for (Figure figure : multipleSelection) {
            figure.setBorderColor(color);
        }
    }

    public void changeInsideColor(Color color){
        for (Figure figure : multipleSelection) {
            figure.setInsideColor(color);
        }
    }

    public void changeLineWidth(double lineWidth){
        for (Figure figure : multipleSelection) {
            figure.setLineWidth(lineWidth);
        }
    }

    public void toFront(Figure figure){
        list.remove(figure);
        list.addLast(figure);
    }

    public void toBack(Figure figure){
        list.remove(figure);
        list.addFirst(figure);
    }
}
