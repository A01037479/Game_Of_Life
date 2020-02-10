package GameOfLife;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * World class sets game are and boundary.
 * @author Eric
 *
 */
public class World{
    private static final int length = 500;
    private static Cell[][] cells = new Cell[50][50];
    
    /**
     * Constructs a world
     */
    public World() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell();
            }
        }
    }
    /**
     * return world length
     * @return int
     */
    public int getLength() {
        return length;
    }

    /**
     * return cells in array
     * @return Cell[][]
     */
    public static Cell[][] getCells() {
        return cells;
    }

    /**
     * initialize the world in GUI
     * @param stage
     */
    public void init(Stage stage) {
        GridPane root = new GridPane();
        for (int i = 0; i < length; i = i + 10) {
            for (int j = 0; j < length; j = j + 10) {
                // add cells to grid
                Rectangle r = new Rectangle(10 - 0.5, 10 - 0.5, cells[i / 10][j / 10].getColor());
                r.setStroke(Color.BLACK);
                r.setStrokeWidth(0.5);
                root.add(r, i, j);

                int val = RandomGenerator.nextNumber(99);
                if (val >= 80) {
                    // add herbivores to grid
                    cells[i / 10][j / 10].addLife(new Herbivore(cells[i / 10][j / 10]));
                    Circle c = new Circle(10 / 2, cells[i / 10][j / 10].getColor());
                    root.add(c, i, j);
                } else if (val >= 60) {
                    // add plants to grid
                    cells[i / 10][j / 10].addLife(new Plant(cells[i / 10][j / 10]));
                    Circle c = new Circle(10 / 2, cells[i / 10][j / 10].getColor());
                    root.add(c, i, j);
                } else if (val >= 50) {
                    // add Carnivore
                    cells[i / 10][j / 10].addLife(new Carnivore(cells[i / 10][j / 10]));
                    Circle c = new Circle(10 / 2, cells[i / 10][j / 10].getColor());
                    root.add(c, i, j);
                } else if (val >= 45 ) {
                    cells[i / 10][j / 10].addLife(new Omnivore(cells[i / 10][j / 10]));
                    Circle c = new Circle(10 / 2, cells[i / 10][j / 10].getColor());
                    root.add(c, i, j);
                }
            }
        }

     show(stage);
    }

    /**
     * manages all cells/lifeform in one turn
     * @param stage
     */
    public void turn(Stage stage) {
        //set all Life to be ready for the new turn
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                Cell cell = cells[i][j];
                if (cell.getLife() != null) {
                    cell.getLife().newTurn();
                }
            }
        }
        //start the turn
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                Cell cell = cells[i][j];
                if (cell.getLife() != null) {
                    // package all neighbor cells in an ArrayList
                    ArrayList<Cell> neighbours = new ArrayList<Cell>();
                    if (i - 1 >= 0 && j - 1 >= 0)
                        neighbours.add(cells[i - 1][j - 1]);
                    if (i - 1 >= 0)
                        neighbours.add(cells[i - 1][j]);
                    if (i - 1 >= 0 && j + 1 <= 49)
                        neighbours.add(cells[i - 1][j + 1]);
                    if (j - 1 >= 0)
                        neighbours.add(cells[i][j - 1]);
                    if (j + 1 <= 49)
                        neighbours.add(cells[i][j + 1]);
                    if (i + 1 <= 49 && j - 1 >= 0)
                        neighbours.add(cells[i + 1][j - 1]);
                    if (i + 1 <= 49)
                        neighbours.add(cells[i + 1][j]);
                    if (i + 1 <= 49 && j + 1 <= 49)
                        neighbours.add(cells[i + 1][j + 1]);
                    // give birth to lifeform
                    if (cell.getLife()!=null&&!cell.getLife().isNewLife()) {
                        cell.getLife().giveBirth(neighbours);
                    }
                    // move the lifeform
                    if (cell.getLife()!=null&&!cell.getLife().hasMoved()) {
                        cell.getLife().move(neighbours);
                    } 
                }
            }
        }
        // show new lifeform on grid
        show(stage);
    }
    /**
     * show the stage
     * @param stage
     */
    public void show(Stage stage) {
        GridPane root = new GridPane();
        for (int i = 0; i < length; i = i + 10) {
            for (int j = 0; j < length; j = j + 10) {
                // add cells to grid
                Rectangle r = new Rectangle(10 - 0.5, 10 - 0.5, Color.WHITE);
                r.setStroke(Color.BLACK);
                r.setStrokeWidth(0.5);
                root.add(r, i, j);
                Cell cell = cells[i / 10][j / 10];
                if (cell.getLife() != null) {
                    Circle c = new Circle(10 / 2, cells[i / 10][j / 10].getColor());
                    root.add(c, i, j);
                }

            }
        }
        Scene scene = new Scene(root, 540, 550);
        stage.setScene(scene);
        stage.setTitle("Game");
        stage.show();

    }
}
