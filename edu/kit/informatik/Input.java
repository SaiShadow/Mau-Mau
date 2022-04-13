package edu.kit.informatik;

import java.util.Scanner;

/**
 * Processes the input.
 * 
 * @author uogok
 * @version 3.61
 */
public final class Input {

    private Input() {
        throw new AssertionError("Utility Classes must not be instantiated!");
    }

    /**
     * main method.
     * 
     * @param args
     */
    public static void main(String[] args) {
        GameManager gm = new GameManager();
        Scanner sc = new Scanner(System.in);
        final String errorMessage = "Error, invalid input. "
                + "Please start the game with: start <Seed>, or end the program with: quit";
        String sentence = null;
        boolean didGameStart = false;
        while ((sentence = sc.nextLine()) != null) {
            try {
                String[] words = sentence.split(" ");
                if (!didGameStart || gm.didGameEndedNormally()) {
                    switch (words[0]) {
                        case "start":
                            if (gm.didGameEndedNormally()) {
                                gm = new GameManager();
                            }
                            gm.start(Integer.valueOf(words[1]));
                            didGameStart = true;
                            break;
                        case "quit":
                            if (sentence.length() == 3 + 1) {
                                sc.close();
                                return;
                            }
                        default:
                            System.out.println(errorMessage);
                            break;
                    }
                } else {
                    switch (words[0]) {
                        case "start":
                            gm = new GameManager();
                            gm.start(Integer.valueOf(words[1]));
                            break;
                        case "show":
                            if (words[1].equals("game")) {
                                gm.showGame();
                                break;
                            }
                            gm.showPlayersHand(Integer.valueOf(words[1]));
                            break;
                        case "discard":
                            gm.discard(Integer.valueOf(words[1]), words[2]);
                            break;
                        case "pick":
                            gm.pick(Integer.valueOf(words[1]));
                            break;
                        case "quit":
                            if (sentence.length() == 3 + 1) {
                                sc.close();
                                return;
                            }
                        default:
                            System.out.println("Error, invalid input.");
                            break;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException a) {
                System.out.println("Error, the given input doesn't contain the required number of words. "
                        + "A few words are missing in your input to run this command.");

            } catch (NumberFormatException e) {
                System.out.println("Error, the given input doesn't contain the required format for the number.");

            } catch (NullPointerException n) {
                System.out.println("Error, the card given is not a part of the Mau-Mau deck.");

            } catch (IllegalArgumentException i) {
                String[] exceptionMessage = i.toString().split(": ");
                System.out.println(exceptionMessage[1]);

            } catch (IndexOutOfBoundsException indexOut) {
                System.out.println("Error, player with this number does not exist. "
                        + "Please select a player between numbers 1-4 inclusive.");
            }
        }
        sc.close();
    }
}
