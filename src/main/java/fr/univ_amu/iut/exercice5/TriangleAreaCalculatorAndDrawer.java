package fr.univ_amu.iut.exercice5;

import fr.univ_amu.iut.exercice3.TriangleArea;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class TriangleAreaCalculatorAndDrawer extends Application {
    private TriangleArea triangleArea = new TriangleArea();

    private Slider x1Slider = new Slider(0, 10, 0);
    private Slider x2Slider = new Slider(0, 10, 0);
    private Slider x3Slider = new Slider(0, 10, 0);

    private Slider y1Slider = new Slider();
    private Slider y2Slider = new Slider();
    private Slider y3Slider = new Slider();

    private Label x1Label = new Label("X1 :");
    private Label x2Label = new Label("X2 :");
    private Label x3Label = new Label("X3 :");

    private Label y1Label = new Label("Y1 :");
    private Label y2Label = new Label("Y2 :");
    private Label y3Label = new Label("Y3 :");

    private Label areaLabel = new Label("Area :");
    private TextField areaTextField = new TextField();

    private Line p1p2 = new Line();
    private Line p2p3 = new Line();
    private Line p3p1 = new Line();

    private Pane drawPane = new Pane();

    private GridPane root = new GridPane();

    @Override
    public void start(Stage stage) throws Exception {
        configGridPane();
        configSliders();
        addSliders();
        addArea();
        addPointLabels();
        addDrawPane();

        createBinding();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Triangle Area Calculator");
        stage.show();
    }

    private void configSliders() {
        configSlider(x1Slider);
        configSlider(y1Slider);
        configSlider(x2Slider);
        configSlider(y2Slider);
        configSlider(x3Slider);
        configSlider(y3Slider);
    }

    private void addDrawPane() {
        drawPane.setPrefHeight(500);
        drawPane.setPrefWidth(500);
        drawPane.setStyle("-fx-background-color: #e7e7e7");

        drawPane.getChildren().addAll(p1p2, p2p3, p3p1);

        root.add(drawPane, 0, 11, 2, 1);
        GridPane.setVgrow(drawPane, Priority.ALWAYS);
    }

    private void configSlider(Slider slider) {
        slider.setMin(0);
        slider.setMax(10);
        slider.setValue(0);

        slider.setSnapToTicks(true);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);

        slider.setMajorTickUnit(5);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(1);
    }

    private void configGridPane() {
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(10);
        root.setVgap(20);

        ColumnConstraints column1 = new ColumnConstraints(50, 50, Double.MAX_VALUE);
        ColumnConstraints column2 = new ColumnConstraints(450, 450, Double.MAX_VALUE);

        column2.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().addAll(column1, column2);

    }

    private void addArea() {
        root.add(areaLabel, 0, 10);
        root.add(areaTextField, 1, 10);
    }

    private void addSliders() {
        root.add(x1Label, 0, 1);
        root.add(x1Slider, 1, 1);

        root.add(y1Label, 0, 2);
        root.add(y1Slider, 1, 2);

        root.add(x2Label, 0, 4);
        root.add(x2Slider, 1, 4);

        root.add(y2Label, 0, 5);
        root.add(y2Slider, 1, 5);

        root.add(x3Label, 0, 7);
        root.add(x3Slider, 1, 7);

        root.add(y3Label, 0, 8);
        root.add(y3Slider, 1, 8);
    }

    private void addPointLabels() {
        Label p1 = new Label("P1");
        GridPane.setConstraints(p1, 0, 0, 2, 1, HPos.CENTER, VPos.BASELINE);

        Label p2 = new Label("P2");
        GridPane.setConstraints(p2, 0, 3, 2, 1, HPos.CENTER, VPos.BASELINE);

        Label p3 = new Label("P3");
        GridPane.setConstraints(p3, 0, 6, 2, 1, HPos.CENTER, VPos.BASELINE);
        root.getChildren().addAll(p1, p2, p3);
    }

    private void createBinding() {
        triangleArea.x1Property().bind(x1Slider.valueProperty());
        triangleArea.x2Property().bind(x2Slider.valueProperty());
        triangleArea.x3Property().bind(x3Slider.valueProperty());

        triangleArea.y1Property().bind(y1Slider.valueProperty());
        triangleArea.y2Property().bind(y2Slider.valueProperty());
        triangleArea.y3Property().bind(y3Slider.valueProperty());

        areaTextField.textProperty().bind(triangleArea.areaProperty().asString());

        p1p2.startXProperty().bind(triangleArea.x1Property().multiply(50));
        p1p2.startYProperty().bind(triangleArea.y1Property().multiply(50));

        p1p2.endXProperty().bind(triangleArea.x2Property().multiply(50));
        p1p2.endYProperty().bind(triangleArea.y2Property().multiply(50));

        p2p3.startXProperty().bind(triangleArea.x2Property().multiply(50));
        p2p3.startYProperty().bind(triangleArea.y2Property().multiply(50));

        p2p3.endXProperty().bind(triangleArea.x3Property().multiply(50));
        p2p3.endYProperty().bind(triangleArea.y3Property().multiply(50));

        p3p1.startXProperty().bind(triangleArea.x3Property().multiply(50));
        p3p1.startYProperty().bind(triangleArea.y3Property().multiply(50));

        p3p1.endXProperty().bind(triangleArea.x1Property().multiply(50));
        p3p1.endYProperty().bind(triangleArea.y1Property().multiply(50));
    }
}
