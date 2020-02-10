package GameOfLife;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * The Game class extends Application
 * It creates a World object and listens to mouse clicking.
 * @author Eric
 *
 */
public class Game extends Application {
    public void start(Stage stage) {
        RandomGenerator.reset();
        //create a world
        World world = new World();
        //initialize the world
        world.init(stage);
        //mouse listener
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

            public void handle(MouseEvent e) {
                System.out.println("Clicked for next turn");
                //next turn
                world.turn(stage);
            }
        };
        stage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    }

}
