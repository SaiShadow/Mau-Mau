package edu.kit.informatik;

import java.util.ArrayList;

/**
 * represents a standard deck.
 * 
 * @author uogok
 * @version 2
 *
 */
public class Deck {

    private ArrayList<Card> cards;

    /**
     * Constructor. It gets the starting deck in the order as asked in the
     * assignment.
     */
    Deck() {
        this.cards = getDeckInStartOrder();
    }

    /**
     * Returns the deck.
     * @return the list of cards.
     */
    public ArrayList<Card> getHand() {
        return this.cards;
    }

    /**
     * sets the deck to this list.
     * @param hand sets this list to the cards list.
     */
    public void setHand(ArrayList<Card> hand) {
        this.cards = hand;
    }

    /**
     * Returns the size of the deck.
     * @return size of the deck.
     */
    public int getNumberOfCards() {
        return cards.size();
    }

    private ArrayList<Card> getDeckInStartOrder() {

        ArrayList<Card> newDeck = new ArrayList<Card>();
        for (Color variableColor : Color.values()) {
            for (Value variableValue : Value.values()) {

                newDeck.add(new Card(variableValue, variableColor));

            }
        }
        return newDeck;
    }
}
