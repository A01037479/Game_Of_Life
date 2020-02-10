package GameOfLife;

import javafx.scene.paint.Color;

/**
 * Herbivore as a child class of Lifefrom
 * @author Eric
 *
 */
public class Herbivore extends Lifeform{
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a Herbivore.
     * @param cell
     */
    public Herbivore(Cell cell) {
        super(cell);
        appearance = Color.YELLOW;
        health = 5;
        edible.add("Plant");
        sameKindNeighb=1;
        emptyNeighb=2;
        foodNeighb=2;
    }

    @Override
    protected String getType() {
        return "Herbivore";
    }

}
