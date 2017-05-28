package fr.univ_amu.iut.exercice8;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {
    private DoubleProperty paddleY = new SimpleDoubleProperty();

    private double initPaddleTranslateY;
    private double paddleDragAnchorY;

    public Paddle(double x) {
        super(x, 0, 20, 50);
        setFill(Color.LIGHTBLUE);
        setCursor(Cursor.CLOSED_HAND);

        setOnMousePressed(me -> {
            initPaddleTranslateY = getTranslateY();
            paddleDragAnchorY = me.getSceneY();
        });

        setOnMouseDragged(me -> {
            double dragY = me.getSceneY() - paddleDragAnchorY;
            paddleY.setValue(initPaddleTranslateY + dragY);
        });

        translateYProperty().bind(paddleY);
        paddleY.setValue(235);
    }
}
