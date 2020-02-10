package GameOfLife;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.paint.Color;

/**
 * Abstract class Lifeform
 * @author Eric
 *
 */
public abstract class Lifeform implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int health;
    /**
     * Boolean variable represents if life is alive
     */
    protected boolean isAlive = true;
    /**
     * Boolean variable represents if life has moved
     */
    protected boolean moveDone=false;
    /**
     * Boolean variable represents if life is newly created
     */
    protected boolean isNewLife=false;
    
    /**
     * HashSet of the food
     */
    protected HashSet<String> edible = new HashSet<String>();
    /**
     * Represents color of the life
     */
    protected Color appearance;
    /**
     * Represents cell that the life is pointing to
     */
    protected Cell cell;
    protected final Set<String> types = new HashSet<String>();
    protected int emptyNeighb;
    protected int sameKindNeighb;
    protected int foodNeighb;
    
    /**
     * Represents a super constructor for its children classes
     * @param cell
     */
    public Lifeform(Cell cell) {
        this.cell=cell;
    }
    
    /**
     * abstract method move
     * @param neighbourCells
     */
    protected void move(ArrayList<Cell> neighbourCells) {
        ArrayList<Cell> foodCells = new ArrayList<Cell>();
        ArrayList<Cell> emptyCells = new ArrayList<Cell>();
        // cannot move to a same life
        for (Cell cell:neighbourCells) {
            if(cell.getLife()==null) {
                emptyCells.add(cell);
            }else if(edible.contains(cell.getLife().getType())) {
                foodCells.add(cell);
            }
        }
        //if there is food nearby
        if (foodCells.size()>0){
            // the old cell is now empty
            this.cell.removeLife();
            // pick a random cell
            int val = RandomGenerator.nextNumber(foodCells.size());
            Cell newCell = foodCells.get(val);
            // eat
            this.eat(newCell.getLife());
            // the Life now points to the new cell
            this.cell = newCell;
            // the new cell also points to the Life
            this.cell.addLife(this);
        }
        //not food nearby but empty space nearby
        else if (emptyCells.size()>0) {
            // lose one life for a move without eating
            this.health--;
            // the old cell is now empty
            this.cell.removeLife();
            int val = RandomGenerator.nextNumber(emptyCells.size());
            Cell newCell = emptyCells.get(val);
            // the Life now points to the new cell
            this.cell = newCell;
            // the new cell also points to the Life
            this.cell.addLife(this);
        }
        else {
            // no place to move
            this.health--;
        }
        // no health = die
        if (this.health == 0) {
            this.die();
        }
        // this Life already moved in this turn
        this.moveDone = true;
    }
    
    /**
     * abstract method seed
     * @param neighbourCells
     */
    protected void giveBirth(ArrayList<Cell> neighbourCells) {
        int countEmpty=0;
        int countSameKind=0;
        int countFood=0;
        ArrayList<Cell> emptyCells = new ArrayList<Cell>();
        for (Cell cell:neighbourCells) {
            if (cell.getLife()==null) {
                countEmpty++;
                emptyCells.add(cell);
            }
            else if (cell.getLife().getType().equals(this.getType())) {
                countSameKind++;
            }
            else if (this.edible.contains(cell.getLife().getType())) {
                countFood++;
            }
        }
        if(countEmpty>=emptyNeighb&&countSameKind>=sameKindNeighb&&countFood>=foodNeighb){
            Lifeform newLife = null;
            int val=RandomGenerator.nextNumber(emptyCells.size());
            Cell birthingCell=emptyCells.get(val);
            String type = this.getType();
            if (type.equals("Plant")) {
                newLife = new Plant(birthingCell);
            }
            else if (type.equals("Herbivore")) {
                newLife = new Herbivore(birthingCell);
            }
            else if (type.equals("Carnivore")) {
                newLife = new Carnivore(birthingCell);  
            }
            else if (type.equals("Omnivore")) {
                newLife = new Omnivore(birthingCell);
            }
            newLife.isNewLife=true;
            birthingCell.addLife(newLife);
        }
        
    }
    /**
     * lifeform dies
     */
    private void die() {
        this.isAlive=false;
        this.cell.removeLife();
    }
    
    /**
     * lifeform eats another lifeform
     * @param food
     */
    private void eat(Lifeform food) {
        this.health=5;
        food.die();
    }
    /**
     * return the type of lifeform
     * @return String
     */
    protected abstract String getType();

    /**
     * Return appearance of the life
     * @return Color
     */
    public Color getAppearance() {
        return appearance;
    }
    
    /**
     * Check if the lifeform has moved
     * @return boolean
     */
    public boolean hasMoved() {
        return this.moveDone;
    }
    
    /**
     * Check if the lifeform is newly created
     * @return boolean
     */
    public boolean isNewLife() {
        return this.isNewLife;
    }
    
    /**
     * Abstract method newTurn
     */
    public void newTurn() {
        this.moveDone=false;
        this.isNewLife=false;
    }
}