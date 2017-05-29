package fr.univ_amu.iut.exercice8;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SlowPong extends Application {

    private BooleanProperty startVisible = new SimpleBooleanProperty(true);
    private Ball ball;
    private Pane pongPane;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Button startButton;
    private AnimationTimer pongAnimation;

    private BooleanExpression isBouncingOffVerticalWall;
    private BooleanExpression isBouncingOffHorizontalWall;

    private boolean isBouncingOffPaddles() {
        return isBouncingOffLeftPaddle() || isBouncingOffRightPaddle();
    }

    private boolean isBouncingOffLeftPaddle() {
        return ball.collided(leftPaddle);
    }

    private boolean isBouncingOffRightPaddle() {
        return ball.collided(rightPaddle);
    }

    private boolean isBouncingOffVerticalWall() {
        return isBouncingOffVerticalWall.get();
    }

    private boolean isBouncingOffHorizontalWall() {
        return isBouncingOffHorizontalWall.get();
    }

    @Override
    public void start(Stage stage) {
        pongAnimation = createAnimation();

        ball = new Ball();

        leftPaddle = new Paddle(10);
        rightPaddle = new Paddle(470);

        startButton = createStartButton();
        pongPane = createPongPane();

        configureStage(stage);

        createBindings();
    }

    private void createBindings() {
        isBouncingOffVerticalWall = ball.centerXProperty().greaterThanOrEqualTo(pongPane.widthProperty().subtract(ball.radiusProperty())).or(ball.centerXProperty().lessThan(ball.radiusProperty()));
        isBouncingOffHorizontalWall = ball.centerYProperty().greaterThanOrEqualTo(pongPane.heightProperty().subtract(ball.radiusProperty())).or(ball.centerYProperty().lessThan(ball.radiusProperty()));

        rightPaddle.xProperty().bind(pongPane.widthProperty().subtract(30));

        startButton.layoutXProperty().bind(pongPane.widthProperty().subtract(startButton.widthProperty()).divide(2));
        startButton.layoutYProperty().bind(pongPane.heightProperty().subtract(30));
    }


    private void configureStage(Stage stage) {
        Scene scene = new Scene(pongPane, 500, 500);
        scene.setFill(Color.GRAY);
        stage.setScene(scene);
        stage.setTitle("SlowPong");
        stage.show();
    }

    private Pane createPongPane() {
        Pane pongPane = new Pane(ball,
                leftPaddle,
                rightPaddle,
                startButton);
        pongPane.setFocusTraversable(true);
        pongPane.requestFocus();
        return pongPane;
    }

    private Button createStartButton() {
        Button startButton = new Button("Start!");
        startButton.setOnAction(e -> pongAnimation.start());
        startButton.visibleProperty().bind(startVisible);
        return startButton;
    }

    private AnimationTimer createAnimation() {
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);

        return new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (lastUpdateTime.get() > 0) {
                    long elapsedTimeInNanoseconds = timestamp - lastUpdateTime.get();
                    updatePong(elapsedTimeInNanoseconds);
                }
                lastUpdateTime.set(timestamp);
            }

            @Override
            public void start() {
                lastUpdateTime.set(System.nanoTime());
                startVisible.set(false);
                super.start();
            }

            @Override
            public void stop() {
                lastUpdateTime.set(System.nanoTime());
                startVisible.set(true);
                super.stop();
            }
        };
    }

    private void updatePong(long elapsedTimeInNanoseconds) {
        checkBouncing();
        moveBall(elapsedTimeInNanoseconds);
    }

    private void checkBouncing() {
        if (isBouncingOffPaddles())
            ball.velocityXProperty().set(-ball.getVelocityX());

        if (isBouncingOffHorizontalWall())
            ball.velocityYProperty().set(-ball.getVelocityY());

        if (isBouncingOffVerticalWall()) {
            startNewGame();
        }
    }

    private void moveBall(long elapsedTimeInNanoseconds) {
        ball.centerXProperty().set(ball.centerXProperty().get() + ball.velocityXProperty().get() * elapsedTimeInNanoseconds);
        ball.centerYProperty().set(ball.centerYProperty().get() + ball.velocityYProperty().get() * elapsedTimeInNanoseconds);
    }

    private void startNewGame() {
        pongAnimation.stop();
        ball.setCenterX(250);
        ball.setCenterY(250);
    }
}