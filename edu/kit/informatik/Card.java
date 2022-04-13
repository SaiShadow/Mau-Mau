package edu.kit.informatik;

/**
 * 
 * @author uogok
 * @version 3
 *
 */
public class Card {
    private Value value;
    private Color color;

    /**
     * Constructor
     * @param value the value, which is 7-10 and K,D,B,A
     * @param color there are 4 colors
     */
    Card(Value value, Color color) {
        setValue(value);
        setColor(color);
    }

    /**
     * Gets the value of the card.
     * @return value of the card
     */
    public Value getValue() {
        return value;
    }

    /**
     * Sets the value of the card.
     * @param value sets this parameter to the value of the card.
     */
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * Gets the color.
     * @return Color of the card.
     */
    public Color getColor() {
        return color;
    }

    /**
     * set the color with this parameter
     * @param color sets this value to the color of the card.
     */
    public void setColor(Color color) {
        this.color = color;
    }

}
