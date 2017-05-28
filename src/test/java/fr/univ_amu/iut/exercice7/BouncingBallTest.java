package fr.univ_amu.iut.exercice7;

import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;

public class BouncingBallTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new BouncingBall().start(stage);
    }
}