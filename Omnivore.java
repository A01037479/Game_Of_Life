package GameOfLife;

import javafx.scene.paint.Color;
/**
 * Omnivore as a child class of Lifefrom
 * @author Eric
 *
 */
public class Omnivore extends Lifeform{
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a Omnivore.
     * @param cell
     */
    public Omnivore(Cell cell) {
        super(cell);
        appearance = Color.BLUE;
        health = 5;
        edible.add("Herbivore");
        edible.add("Carnivore");
        edible.add("Plant");
        sameKindNeighb=1;
        emptyNeighb=3;
        foodNeighb=1;
    }

    @Override
    protected String getType() {
        return "Omnivore";
    }


}
