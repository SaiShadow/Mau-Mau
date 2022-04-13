package edu.kit.informatik;

/**
 * Represents the discard pile, it only shows one card, which is on the top.
 * @author uogok
 * @version 1
 *
 */
public class DiscardPile {

    private Card topCardOfPile;

    /**
     * gets Top Card Of the discard Pile.
     * @return top card of the discard pile.
     */
    public Card getTopCardOfPile() {
        return topCardOfPile;
    }

    /**
     * sets the top card of the discard pile.
     * @param topCardOfPile is to be set as the top card of the pile.
     */
    public void setTopCardOfPile(Card topCardOfPile) {
        this.topCardOfPile = topCardOfPile;
    }

}
