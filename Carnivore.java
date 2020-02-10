package GameOfLife;

import javafx.scene.paint.Color;
/**
 * Carnivore as a child class of Lifefrom
 * @author Eric
 *
 */
public class Carnivore extends Lifeform{
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a Carnivore.
     * @param cell
     */
    public Carnivore(Cell cell) {
        super(cell);
        appearance = Color.RED;
        health = 5;
        edible.add("Herbivore");
        edible.add("Omnivore");
        sameKindNeighb=1;
        emptyNeighb=3;
        foodNeighb=2;
    }

    @Override
    protected String getType() {
        return "Carnivore";
    }

    

}
