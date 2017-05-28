package fr.univ_amu.iut.exercice7;

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
    private final DoubleProperty velocityX;
    private final DoubleProperty velocityY;
    private final DoubleProperty radius;
    private final Pane parent;
    private Circle ball;
    private BooleanExpression isBouncingOffVerticalWall;
    private BooleanExpression isBouncingOffHorizontalWall;

    private NumberBinding bounceOffVerticalWall;
    private NumberBinding bounceOffHorizontalWall;

    public Ball(Pane parent) {
        this.parent = parent;
        positionX = new SimpleDoubleProperty(50);
        positionY = new SimpleDoubleProperty(20);
        velocityX = new SimpleDoubleProperty(150E-9);//en pixel par nanosecond
        velocityY = new SimpleDoubleProperty(100E-9);//en pixel par nanosecond
        radius = new SimpleDoubleProperty(10);
        ball = new Circle();

        createBindings();
        parent.getChildren().addAll(ball);
    }

    private void createBindings() {

        ball.radiusProperty().bind(radius);

        ball.centerXProperty().bind(positionX);
        ball.centerYProperty().bind(positionY);

        isBouncingOffVerticalWall = positionX.greaterThanOrEqualTo(parent.widthProperty().subtract(radius)).or(positionX.lessThan(radius));
        isBouncingOffHorizontalWall = positionY.greaterThanOrEqualTo(parent.heightProperty().subtract(radius)).or(positionY.lessThan(radius));

        bounceOffVerticalWall = when(isBouncingOffVerticalWall).then(velocityX.negate()).otherwise(velocityX);
        bounceOffHorizontalWall = when(isBouncingOffHorizontalWall).then(velocityY.negate()).otherwise(velocityY);
    }


    // move
    public void move(long elapsedTimeInNanoseconds) {
        velocityX.set(bounceOffVerticalWall.doubleValue());
        velocityY.set(bounceOffHorizontalWall.doubleValue());

        positionX.set(positionX.get() + velocityX.get() * elapsedTimeInNanoseconds);
        positionY.set(positionY.get() + velocityY.get() * elapsedTimeInNanoseconds);
    }

    private boolean isBouncingOffVerticalWall() {
        return isBouncingOffVerticalWall.get();
    }

    private boolean isBouncingOffHorizontalWall() {
        return isBouncingOffHorizontalWall.get();
    }
}