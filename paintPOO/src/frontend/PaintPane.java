package TPE_POO.paintPOO.src.frontend;

import TPE_POO.paintPOO.src.backend.CanvasState;
import TPE_POO.paintPOO.src.backend.model.Circle;
import TPE_POO.paintPOO.src.backend.model.Ellipse;
import TPE_POO.paintPOO.src.backend.model.Figure;
import TPE_POO.paintPOO.src.backend.model.Point;
import TPE_POO.paintPOO.src.backend.model.Rectangle;
import TPE_POO.paintPOO.src.backend.model.Square;
import TPE_POO.paintPOO.src.backend.model.Line;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PaintPane extends BorderPane {

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();

	// Botones Barra Izquierda
	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
	private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
	private final ToggleButton squareButton = new ToggleButton("Cuadrado");
	private final ToggleButton circleButton = new ToggleButton("Círculo");
	private final ToggleButton ellipseButton = new ToggleButton("Elipse");
	private final ToggleButton lineButton = new ToggleButton("Linea");

	//Borde (Border)
	private final Slider border = new Slider(1, 50, 1);
	private final ColorPicker borderColorPicker = new ColorPicker(Color.BLACK);

	//Relleno (Inside)
	private final ColorPicker insideColorPicker = new ColorPicker(Color.YELLOW);

	// Dibujar una figura
	private Point startPoint;

	// StatusBar
	StatusPane statusPane;

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;

		ToggleButton[] toolsArr = {selectionButton, rectangleButton, squareButton, circleButton, ellipseButton, lineButton};
		Label borderMessage = new Label("Borde: ");
		border.setShowTickLabels(true);
		border.setShowTickMarks(true);
		border.setSnapToTicks(true);
		borderMessage.setTextFill(Color.BLACK);
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		//Relleno (Inside)
		Label insideMessage = new Label("Relleno: ");
		Button deleteButton = new Button("Borrar");
		Button toFrontButton = new Button("Al Frente");
		Button toBackButton = new Button("Al fondo");
		buttonsBox.getChildren().addAll(deleteButton, toBackButton, toFrontButton, borderMessage, border, borderColorPicker, insideMessage, insideColorPicker);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);

		//Funcionalidades al seleccionar una opcion
		canvas.setOnMousePressed(event -> startPoint = new Point(event.getX(), event.getY()));

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if (startPoint == null) {
				return;
			}
			if (!(lineButton.isSelected()) && (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY())) {
				return;
			}
			Figure newFigure = null;
			if (rectangleButton.isSelected()) {
				newFigure = new Rectangle(startPoint, endPoint, borderColorPicker.getValue(), insideColorPicker.getValue(), border.getValue());
			} else if (circleButton.isSelected()) {
				double circleRadius = startPoint.distanceTo(endPoint);
				newFigure = new Circle(startPoint, circleRadius, borderColorPicker.getValue(), insideColorPicker.getValue(), border.getValue());
			} else if (squareButton.isSelected()) {
				newFigure = new Square(startPoint, endPoint, borderColorPicker.getValue(), insideColorPicker.getValue(), border.getValue());
			} else if (ellipseButton.isSelected()) {
				newFigure = new Ellipse(startPoint, endPoint, borderColorPicker.getValue(), insideColorPicker.getValue(), border.getValue());
			} else if (lineButton.isSelected()) {
				newFigure = new Line(startPoint, endPoint, borderColorPicker.getValue(), border.getValue());
			} else if (selectionButton.isSelected()) {
				canvasState.selectionClear();
				canvasState.selection(startPoint, endPoint);
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				if (!canvasState.selectionIsEmpty()) {
					found = true;
					for (Figure figure : canvasState.selectedFigures()) {
						label.append(figure.toString());
					}
				}
				if (found) {
					borderColorPicker.setOnAction(event1 -> {
						canvasState.changeBorderColor(borderColorPicker.getValue());
						redrawCanvas();
					});
					insideColorPicker.setOnAction(event12 -> {
						canvasState.changeInsideColor(insideColorPicker.getValue());
						redrawCanvas();
					});
					border.valueProperty().addListener((observable, oldValue, newValue) -> {
						canvasState.changeLineWidth(newValue.doubleValue());
						redrawCanvas();
					});
					statusPane.updateStatus(label.toString());
				} else {
					statusPane.updateStatus("Ninguna figura encontrada");
				}
			} else {
				return;
			}
			if (newFigure != null) {
				canvasState.addFigure(newFigure);
			}
			startPoint = null;
			redrawCanvas();
		});

		//Funcionalidad de ancho de borde sin seleccionar

		border.valueProperty().addListener((observable, oldValue, newValue) -> {
			gc.setLineWidth(newValue.doubleValue());
			redrawCanvas();
		});

		//Funcionalidad de borrado
		deleteButton.setOnAction(event -> {
			canvasState.deleteSelection();
			canvasState.selectionClear();
			redrawCanvas();
		});

		//Funcionalidades de Profundidad
		toBackButton.setOnAction(event -> {
			for (Figure figure : canvasState.selectedFigures()) {
				canvasState.toBack(figure);
			}
			redrawCanvas();
		});

		toFrontButton.setOnAction(event ->{
			for (Figure figure : canvasState.selectedFigures()) {
				canvasState.toFront(figure);
			}
			redrawCanvas();
		});

		//Informacion de las figuras
		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(Figure figure : canvasState.figures()) {
				if(figure.figureBelongs(eventPoint)) {
					found = true;
					label.append(figure.toString());
				}
			}
			if(found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		//Funcionalidad de movimiento
		canvas.setOnMouseDragged(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
				canvasState.moveSelection(diffX, diffY);
				redrawCanvas();
			}
		});
		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Figure figure : canvasState.figures()) {
			if(canvasState.selectionContains(figure)) {
				gc.setStroke(Color.RED);
			} else {
				gc.setStroke(figure.getBorderColor());
			}
			gc.setFill(figure.getInsideColor());
			gc.setLineWidth(figure.getLineWidth());
			if(figure instanceof Rectangle) {
				if(figure instanceof Ellipse){
					Ellipse ellipse = (Ellipse) figure;
					gc.strokeOval(ellipse.getTopLeft().getX(), ellipse.getTopLeft().getY(), ellipse.base(), ellipse.height());
					gc.fillOval(ellipse.getTopLeft().getX(), ellipse.getTopLeft().getY(), ellipse.base(), ellipse.height());
				} else if(figure instanceof Square){
					Square square=(Square) figure;
					gc.strokeRect(square.getTopLeft().getX(),square.getTopLeft().getY(),square.Side(),square.Side());
					gc.fillRect(square.getTopLeft().getX(),square.getTopLeft().getY(),square.Side(),square.Side());
				} else {
					Rectangle rectangle = (Rectangle) figure;
					gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), rectangle.base(), rectangle.height());
					gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), rectangle.base(), rectangle.height());
				}
			} else if(figure instanceof Circle) {
				Circle circle = (Circle) figure;
				double diameter = circle.getRadius() * 2;
				gc.fillOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
				gc.strokeOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
			} else if(figure instanceof Line){
				Line line = (Line) figure;
				gc.strokeLine(line.getStartPoint().getX(), line.getStartPoint().getY(), line.getEndPoint().getX(), line.getEndPoint().getY());
			}
		}
	}

}