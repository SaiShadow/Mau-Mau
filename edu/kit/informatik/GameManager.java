package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Is the game manager.
 * 
 * @author uogok
 * @version 6.9
 */
public class GameManager {

    private Deck mainDeck;
    private DiscardPile mainDiscardPile;
    private ArrayList<Player> players;
    private int numberOfTurns;
    private boolean gameEndedNormally;
    private final int maxNumberOfPlayers = 4;

    /**
     * Constructor.
     */
    GameManager() {
        this.mainDeck = new Deck();
        this.mainDiscardPile = new DiscardPile();
        this.players = new ArrayList<Player>(maxNumberOfPlayers);
        this.numberOfTurns = 0;
        addStartingPlayers();
        setGameEndedNormally(false);
    }

    /**
     * Returns true if game ended normally.
     * 
     * @return return true if game ended normally, that is if it was a draw or
     *         someone won.
     */
    public boolean didGameEndedNormally() {
        return gameEndedNormally;
    }

    /**
     * Sets gameEndedNormally.
     * 
     * @param gameEndedNormally sets this value to gameEndedNormally.
     */
    public void setGameEndedNormally(boolean gameEndedNormally) {
        this.gameEndedNormally = gameEndedNormally;
    }

    private void addStartingPlayers() {
        for (int i = 1; i <= maxNumberOfPlayers; i++) {
            this.players.add(new Player(i));
        }
    }

    /**
     * Returns the player whose turn it is.
     * 
     * @return the player who has to play.
     */
    public int getWhichPlayersTurn() {
        return (numberOfTurns % players.size()) + 1;
    }

    private void shuffleDeck(int seed) {
        Collections.shuffle(mainDeck.getHand(), new Random(seed));
    }

    private Card drawFromDeck() {
        Card topCard = mainDeck.getHand().get(0);
        mainDeck.getHand().remove(0);
        return topCard;
    }

    private void addTopCardToDiscardPile() {
        mainDiscardPile.setTopCardOfPile(drawFromDeck());
    }

    /**
     * Checks if the card in the players hand can be discarded onto the discard
     * pile.
     * 
     * @param cardInDiscardPile card on top of the discard pile.
     * @param cardInPlayersHand card the player wants to discard.
     * @return true, if the color or the value of the cards match.
     */
    private boolean doCardsMatch(Card cardInDiscardPile, Card cardInPlayersHand) {
        boolean valMatch = cardInDiscardPile.getValue() == cardInPlayersHand.getValue();
        boolean colorMatch = cardInDiscardPile.getColor() == cardInPlayersHand.getColor();
        return valMatch || colorMatch;
    }

    private void giveAllPlayers5Cards() {
        for (int i = 0; i < players.size(); i++) {
            Player variablePlayer = players.get(i);
            for (int j = 0; j < 5; j++) {
                variablePlayer.addCardToHand(drawFromDeck());
            }
        }
    }

    /**
     * starts the program.
     * 
     * @param seed is to be shuffled using this seed.
     */
    public void start(int seed) {
        shuffleDeck(seed);
        giveAllPlayers5Cards();
        addTopCardToDiscardPile();
        System.out.println("Player " + getWhichPlayersTurn() + " takes the turn.");
    }

    private String cardInString(Card card) {
        String cardValue = card.getValue().getVal();
        String cardColor = card.getColor().toString();
        String output = cardValue + cardColor;
        return output;
    }

    /**
     * Shows the top card of the DiscardPile and number of cards left in deck.
     */
    public void showGame() {
        String cardOnDiscardPile = cardInString(mainDiscardPile.getTopCardOfPile());
        System.out.println(cardOnDiscardPile + " / " + mainDeck.getNumberOfCards());
    }

    /**
     * Shows all the cards in the players hand in the "natural order".
     * 
     * @param playerID players number
     * @throws IndexOutOfBoundsException
     */
    public void showPlayersHand(int playerID) throws IndexOutOfBoundsException {

        ArrayList<Card> wantedPlayerHand = players.get(playerID - 1).getHand();
        List<String> wantedPlayerHandCardsInString = new ArrayList<>();

        for (int i = 0; i < wantedPlayerHand.size(); i++) {

            wantedPlayerHandCardsInString.add(cardInString(wantedPlayerHand.get(i)));

        }
        Collections.sort(wantedPlayerHandCardsInString);

        String hand = "";
        if (wantedPlayerHandCardsInString.size() == 1) {

            hand = wantedPlayerHandCardsInString.get(0);

        } else {
            for (int i = 0; i < wantedPlayerHand.size() - 1; i++) {

                hand = hand + wantedPlayerHandCardsInString.get(i) + ",";

            }
            hand = hand + wantedPlayerHandCardsInString.get(wantedPlayerHandCardsInString.size() - 1);
        }
        System.out.println(hand);
    }

    private Value getCardValue(char val) {
        Value valueOfCard = null;
        switch (val) {
            case '7':
                valueOfCard = Value.SEVEN;
                break;
            case '8':
                valueOfCard = Value.EIGHT;
                break;
            case '9':
                valueOfCard = Value.NINE;
                break;
            case 'B':
                valueOfCard = Value.JACK;
                break;
            case 'D':
                valueOfCard = Value.QUEEN;
                break;
            case 'K':
                valueOfCard = Value.KING;
                break;
            case 'A':
                valueOfCard = Value.ACE;
                break;
            default:
                break;
        }
        return valueOfCard;
    }

    private Color getCardColor(char color) {

        Color colorOfCard = null;
        switch (color) {
            case 'E':
                colorOfCard = Color.E;
                break;
            case 'L':
                colorOfCard = Color.L;
                break;
            case 'H':
                colorOfCard = Color.H;
                break;
            case 'S':
                colorOfCard = Color.S;
                break;
            default:
                break;
        }
        return colorOfCard;
    }

    private Card getCardWithName(String cardName) throws NullPointerException {

        if (cardName.length() == 3) {
            char colorVal = cardName.charAt(2);
            return new Card(Value.TEN, getCardColor(colorVal));
        }

        char val = cardName.charAt(0);
        char color = cardName.charAt(1);
        return new Card(getCardValue(val), getCardColor(color));
    }

    /**
     * Discard the particular card from the player and sets it on the discard pile.
     * 
     * @param playerID
     * @param cardName
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public void discard(int playerID, String cardName) throws NullPointerException, IllegalArgumentException {

        if (playerID == getWhichPlayersTurn()) {
            Player playerNeeded = players.get(playerID - 1);
            Card cardFromPlayer = getCardWithName(cardName);
            boolean cardOnHand = false;
            boolean cardsMatch = doCardsMatch(mainDiscardPile.getTopCardOfPile(), cardFromPlayer);

            if (playerNeeded.getHand().size() == 1 && cardsMatch) {

                System.out.println("Game over: Player " + playerID + " has won.");
                setGameEndedNormally(true);
                return;

            } else if (cardsMatch) {
                for (int i = 0; i < playerNeeded.getHand().size(); i++) {
                    if (cardInString(playerNeeded.getHand().get(i)).equals(cardName)) {
                        cardOnHand = true;
                        cardFromPlayer = playerNeeded.getHand().get(i);
                        break;
                    }
                }
                if (cardOnHand) {
                    mainDiscardPile.setTopCardOfPile(cardFromPlayer);
                    playerNeeded.getHand().remove(cardFromPlayer);
                    numberOfTurns++;
                } else {
                    throw new IllegalArgumentException(
                            "Error, Player " + playerID + " doesn't have card " + cardName + " in his hand.");
                }
            } else {
                System.out.println("Error, " + cardName + " cannot be stacked on "
                        + cardInString(mainDiscardPile.getTopCardOfPile()) + ".");
            }
        } else {
            throw new IllegalArgumentException("Error, it isn't Player " + playerID + "'s turn. It is Player "
                    + getWhichPlayersTurn() + "'s turn.");
        }
    }

    /**
     * The player draw the top card from the deck and puts it in his hand.
     * 
     * @param playerID this player draws the card.
     */
    public void pick(int playerID) throws IllegalArgumentException {
        if (playerID == getWhichPlayersTurn()) {

            Player playerNeeded = players.get(playerID - 1);
            boolean doesPlayerHaveAMatchingCard = checkIfPlayerHasMatchingCard(playerNeeded);

            if (!doesPlayerHaveAMatchingCard) {
                if (mainDeck.getNumberOfCards() == 1) {
                    setGameEndedNormally(true);
                    System.out.println("Game over: Draw.");
                    return;
                }
                playerNeeded.addCardToHand(drawFromDeck());
                numberOfTurns++;
            } else {
                throw new IllegalArgumentException("Error, Player " + playerID
                        + " already has a card in his hand which can be discarded. "
                        + "So please discard the suitable card.");
            }
        } else {
            throw new IllegalArgumentException("Error, it isn't Player " + playerID + "'s turn. It is Player "
                    + getWhichPlayersTurn() + "'s turn.");
        }
    }

    private boolean checkIfPlayerHasMatchingCard(Player player) {

        for (int i = 0; i < player.getHand().size(); i++) {
            if (doCardsMatch(mainDiscardPile.getTopCardOfPile(), player.getHand().get(i))) {
                return true;
            }
        }
        return false;

    }

}
