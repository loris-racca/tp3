package fr.univ_amu.iut.exercice7;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import static javafx.beans.binding.Bindings.when;

public class Ball {

    private final DoubleProperty positionX;
    private final DoubleProperty positionY;
    private final DoubleProperty velocityX; //en pixel par nanosecond
    private final DoubleProperty velocityY; //en pixel par nanosecond
    private final DoubleProperty radius;
    private final Pane parent;
    private Circle ball = new Circle();

    private BooleanExpression isBouncingOffVerticalWall;
    private BooleanExpression isBouncingOffHorizontalWall;

    private NumberBinding bounceOffVerticalWall;
    private NumberBinding bounceOffHorizontalWall;

    public Ball(Pane parent) {
        velocityX = new SimpleDoubleProperty(150E-9);
        velocityY = new SimpleDoubleProperty(100E-9);
        positionX = new SimpleDoubleProperty(10);
        positionY = new SimpleDoubleProperty(10);
        radius = new SimpleDoubleProperty(5);
        this.parent = parent;
        this.parent.getChildren().add(ball);
        this.createBindings();
    }

    private void createBindings() {
        ball.centerXProperty().bind(positionX);
        ball.centerYProperty().bind(positionY);
        ball.radiusProperty().bind(radius);

        isBouncingOffVerticalWall = positionY.greaterThan(parent.heightProperty().subtract(radius)).or(positionY.lessThan(radius));
        isBouncingOffHorizontalWall = positionX.greaterThan(parent.widthProperty().subtract(radius)).or(positionX.lessThan(radius));

        bounceOffVerticalWall = Bindings.when(isBouncingOffVerticalWall).then(velocityY.negate()).otherwise(velocityY);
        bounceOffHorizontalWall = Bindings.when(isBouncingOffHorizontalWall).then(velocityX.negate()).otherwise(velocityX);

    }

    public void move(long elapsedTimeInNanoseconds) {
        velocityY.setValue(bounceOffVerticalWall.getValue());
        velocityX.setValue(bounceOffHorizontalWall.getValue());
        positionX.setValue(positionX.getValue() + velocityX.getValue()*elapsedTimeInNanoseconds);
        positionY.setValue(positionY.getValue() + velocityY.getValue()*elapsedTimeInNanoseconds);
    }
}