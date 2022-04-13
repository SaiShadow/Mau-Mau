package edu.kit.informatik;

/**
 * Represents the value of a card.
 * @author uogok
 * @version 4.9.5
 *
 */
public enum Value {

    /**
     * SEVEN
     */
    SEVEN("7"), 
    /**
     * EIGHT
     */
    EIGHT("8"), 
    /**
     * NINE
     */
    NINE("9"), 
    /**
     * TEN
     */
    TEN("10"), 
    /**
     * JACK
     */
    JACK("B"), 
    /**
     * QUEEN
     */
    QUEEN("D"), 
    /**
     * KING
     */
    KING("K"), 
    /**
     * ACE
     */
    ACE("A");

    private String val;

    /**
     * 
     * @param val
     */
    Value(String val) {
        this.val = val;
    }

    /**
     * 
     * @return the value of the card.
     */
    public String getVal() {
        return val;
    }
}
