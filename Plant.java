package GameOfLife;

import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * Plant class as a child class of Lifeform
 * @author Eric
 *
 */
public class Plant extends Lifeform {
    private static final long serialVersionUID = 1L;
    /**
     * Construct a plant
     * @param cell
     */
    public Plant(Cell cell) {
        super(cell);
        appearance = Color.GREEN;
        sameKindNeighb=2;
        emptyNeighb=3;
        foodNeighb=0;
    }
    
    /**
     * Plant's version of move method
     */
    @Override
    protected void move(ArrayList<Cell> neighbourCells) {
        //does not move
    }
    
    protected void eat() {
        //does not eat
    }

    @Override
    protected String getType() {
        return "Plant";
    }
}
