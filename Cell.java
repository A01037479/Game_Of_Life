package GameOfLife;

import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * Cell represents a position in the game world
 * @author Eric
 *
 */
public class Cell implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Color of cell
     */
    private Color color;
    /**
     * Cell contains a Lifeform
     */
    private Lifeform life;
    
    /**
     * Constructs a cell
     */
    public Cell() {
        color = Color.WHITE;
    }

    /**
     * Return the color of cell
     * @return Color
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Return the lifeform inside the cell
     * @return Lifeform
     */
    public Lifeform getLife() {
        return this.life;
    }
    
    /**
     * Add a lifeform to the cell
     * @param life
     */
    public void addLife(Lifeform life) {
        this.life = life;
        this.color = life.getAppearance();
    }

    /**
     * Remove the lifefrom from a cell
     */
    public void removeLife() {
        this.life = null;
        this.color = Color.WHITE;
    }
}
