package fr.univ_amu.iut.exercice4;

import fr.univ_amu.iut.exercice3.TriangleArea;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class TriangleAreaCalculator extends Application {
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

    private GridPane root = new GridPane();

    private static void configSlider(Slider slider) {
        throw new RuntimeException("Not yet implemented !");
    }

    @Override
    public void start(Stage stage) throws Exception {
        configGridPane();
        configSliders();

        addSliders();
        addArea();

        addPointLabels();
        createBinding();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Triangle Area Calculator");
        stage.show();
    }

    private void configSliders() {
        root.add(x1Slider,1,1);
        configSlider(x1Slider);

        root.add(x2Slider,1,2);
        configSlider(x2Slider);

        root.add(x3Slider,1,3);
        configSlider(x3Slider);

        root.add(y1Slider,1,4);
        configSlider(y1Slider);

        root.add(y2Slider,1,5);
        configSlider(y2Slider);

        root.add(y3Slider,1,6);
        configSlider(y3Slider);

      }

    private void configGridPane() {
        root.setPadding(new Insets(10,10,10,10));
        root.setHgap(10);
        root.setVgap(10);
        root.getColumnConstraints().addAll()
                = new

    }

    private void addArea() {
        throw new RuntimeException("Not yet implemented !");
    }

    private void addSliders() {
        throw new RuntimeException("Not yet implemented !");
    }

    private void addPointLabels() {
        throw new RuntimeException("Not yet implemented !");
    }

    private void createBinding() {
        throw new RuntimeException("Not yet implemented !");
    }
}
