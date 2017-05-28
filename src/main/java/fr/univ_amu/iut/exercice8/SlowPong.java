package fr.univ_amu.iut.exercice8;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SlowPong extends Application {

    /**
     * The moving ball
     */
    private Circle ball;

    /**
     * The Group containing all of the walls, paddles, and ball. This also
     * allows us to requestFocus for KeyEvents on the Group
     */
    private Group pongComponents;

    /**
     * The left and right paddles
     */
    private Paddle leftPaddle;
    private Paddle rightPaddle;

    /**
     * The walls
     */
    private Rectangle topWall;
    private Rectangle rightWall;
    private Rectangle leftWall;
    private Rectangle bottomWall;

    private Button startButton;


    /**
     * The animation of the ball
     */
    private Timeline pongAnimation;

    /**
     * Controls whether the ball is moving right
     */
    private boolean movingRight = true;

    /**
     * Controls whether the ball is moving down
     */
    private boolean movingDown = true;

    private static boolean isInCollisionWith(Circle ball, Rectangle rectangle) {
        return ball.intersects(rectangle.getBoundsInParent());
    }

    /**
     * Sets the initial starting positions of the ball and paddles
     */
    private void initialize() {
        ball.centerXProperty().setValue(250);
        ball.centerYProperty().setValue(250);
        pongComponents.requestFocus();
    }

    /**
     * Checks whether or not the ball has collided with either the paddles,
     * topWall, or bottomWall. If the ball hits the wall behind the paddles, the
     * game is over.
     */

    private void checkForCollision() {
        if (isEndOfGame()) {
            pongAnimation.stop();
            initialize();
        } else if (isBouncingOnWall()) {
            movingDown = !movingDown;
        } else if (isBouncingOnPaddle()) {
            movingRight = !movingRight;
        }
    }

    private boolean isBouncingOnPaddle() {
        return isInCollisionWith(ball, leftPaddle) || isInCollisionWith(ball, rightPaddle);
    }

    private boolean isBouncingOnWall() {
        return isInCollisionWith(ball, bottomWall) || isInCollisionWith(ball, topWall);
    }

    private boolean isEndOfGame() {
        return isInCollisionWith(ball, rightWall) || isInCollisionWith(ball, leftWall);
    }

    @Override
    public void start(Stage stage) {
        pongAnimation = new Timeline(
                new KeyFrame(new Duration(60.0), t -> {
                    checkForCollision();
                    int horzPixels = movingRight ? 1 : -1;
                    int vertPixels = movingDown ? 1 : -1;

                    ball.centerXProperty().setValue(ball.centerXProperty().getValue() + horzPixels);
                    ball.centerYProperty().setValue(ball.centerYProperty().getValue() + vertPixels);
                })
        );

        pongAnimation.setCycleCount(Timeline.INDEFINITE);

        ball = new Circle(0, 0, 10, Color.WHITE);

        topWall = new Rectangle(0, 0, 500, 1);
        leftWall = new Rectangle(0, 0, 1, 500);
        rightWall = new Rectangle(500, 0, 1, 500);
        bottomWall = new Rectangle(0, 500, 500, 1);

        leftPaddle = new Paddle(20);
        rightPaddle = new Paddle(470);

        startButton = new Button("Start!");
        startButton.setLayoutX(225);
        startButton.setLayoutY(470);

        startButton.setOnAction(e -> pongAnimation.playFromStart());

        pongComponents = new Group(ball,
                topWall,
                leftWall,
                rightWall,
                bottomWall,
                leftPaddle,
                rightPaddle,
                startButton);

        pongComponents.setFocusTraversable(true);

        initialize();

        Scene scene = new Scene(pongComponents, 500, 500);
        scene.setFill(Color.GRAY);
        stage.setScene(scene);
        stage.setTitle("SlowPong");
        stage.show();
    }
}