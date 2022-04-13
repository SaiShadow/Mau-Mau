package edu.kit.informatik;

import java.util.ArrayList;

/**
 * Represents a player.
 * @author uogok
 * @version 3
 *
 */
public class Player {

    private int number;
    private ArrayList<Card> hand;

    /**
     * Constructor for the class.
     * @param number
     */
    Player(int number) {
        setNumber(number);
        this.hand = new ArrayList<Card>();
    }

    /**
     * Returns the list which contains the cards.
     * @return the hand, which holds the cards.
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Sets his hand with a list of cards.
     * @param hand
     */
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * Gets the players number.
     * @return the players ID.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the players number.
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }
    
    /**
     * Adds this card to his hand.
     * @param card
     */
    public void addCardToHand(Card card) {
        this.hand.add(card);
    }

}
