package Assignment2;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Description
 * This class is the main body of the Nim advanced game.
 * yuzyou@student.unimelb.edu.au
 * No.1159774
 *
 * @author Yuzhe You
 */

public class NimAdvancedGame {
    private int initialStones;
    private int numberOfStones;
    private NimPlayer player1;
    private NimPlayer player2;
    private Scanner reader;
    private boolean[] available;
    private String lastMove = "0 0";

    public NimAdvancedGame(Scanner reader, int numberOfStones, NimPlayer Player1, NimPlayer Player2) {
        this.initialStones = numberOfStones;
        this.numberOfStones = numberOfStones;
        this.player1 = Player1;
        this.player2 = Player2;
        this.reader = reader;
        available = new boolean[initialStones];
        for (int i = 0; i < initialStones; i++) {
            available[i] = true;
        }
    }

    private void advancedDisplay(int number, boolean[] available) {
        System.out.print(numberOfStones + " stones left:");
        for (int i = 0; i < number; i++) {
            if (available[i] == true) {
                System.out.print(" <" + (i + 1) + ",*>");
            } else {
                System.out.print(" <" + (i + 1) + ",x>");
            }
        }
        System.out.println();
    }

    private void advancedInitialDisplay(int number, boolean[] available) {
        System.out.println();
        System.out.println("Initial stone count: " + initialStones);
        System.out.print("Stones display:");
        for (int i = 0; i < number; i++) {
            if (available[i] == true) {
                System.out.print(" <" + (i + 1) + ",*>");
            } else {
                System.out.print(" <" + (i + 1) + ",x>");
            }
        }
        System.out.println();
        System.out.println("Player 1: " + player1.getFirstName() + " " + player1.getLastName());
        System.out.println("Player 2: " + player2.getFirstName() + " " + player2.getLastName());
        System.out.println();
        player1.setGames(player1.getGames() + 1);
        player2.setGames(player2.getGames() + 1);
    }

    // judge whether a move is valid and return the value of move
    public String validityJudge(String move, boolean[] available, NimPlayer player) {
        boolean isValid = true;
        while (isValid) {
            try {
                StringTokenizer fragment = new StringTokenizer(move);
                int index = Integer.parseInt(fragment.nextToken()) - 1;
                int numberToRemove = Integer.parseInt(fragment.nextToken());
                if (numberToRemove != 1 && numberToRemove != 2) {
                    throw new InvalidMoveException("Invalid move.");
                } else if (index > initialStones) {
                    throw new InvalidMoveException("Invalid move.");
                } else if (numberToRemove == 1 && available[index] == false) {
                    throw new InvalidMoveException("Invalid move.");
                } else if (numberToRemove == 1 && available[index] == true) {
                    available[index] = false;
                } else if (numberToRemove == 2 && (available[index] == false || available[index + 1] == false)) {
                    throw new InvalidMoveException("Invalid move.");
                } else if (numberToRemove == 2 && (available[index] == true && available[index + 1] == true)) {
                    available[index + 1] = false;
                    available[index] = false;
                }
                numberOfStones = numberOfStones - numberToRemove;
                isValid = false;
            } catch (NoSuchElementException e) {
                System.out.println();
                System.out.println("Invalid move.");
                inputAgain(player, available);
                move = player.advancedRemove(this);
            } catch (NullPointerException e) {
                System.out.println();
                System.out.println("Invalid move.");
                inputAgain(player, available);
                move = player.advancedRemove(this);
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid move.");
                inputAgain(player, available);
                move = player.advancedRemove(this);
            } catch (InvalidMoveException e) {
                System.out.println();
                System.out.println(e.getMessage());
                inputAgain(player, available);
                move = player.advancedRemove(this);
            }
        }
        return move;
    }

    private void inputAgain(NimPlayer Player, boolean[] available) {
        System.out.println();
        advancedDisplay(initialStones, available);
        System.out.println(Player.getFirstName() + "\'s turn - which to remove?");
    }

    private void advancedWinDisplay(NimPlayer player) {
        System.out.println("Game Over");
        System.out.println(player.getFirstName() + " " + player.getLastName() + " wins!");
        player.setWins(player.getWins() + 1);
    }

    // entrance of an advanced game
    public void run() {
        String lastMoveTrial;
        advancedInitialDisplay(initialStones, available);
        while (true) {
            advancedDisplay(initialStones, available);
            System.out.println(player1.getFirstName() + "\'s turn - which to remove?");
            lastMoveTrial = player1.advancedRemove(this);
            lastMove = validityJudge(lastMoveTrial, available, player1);
            player1.outputRemove(lastMove);
            if (numberOfStones == 0) {
                advancedWinDisplay(player1);
                break;
            }

            advancedDisplay(initialStones, available);
            System.out.println(player2.getFirstName() + "\'s turn - which to remove?");
            lastMoveTrial = player2.advancedRemove(this);
            lastMove = validityJudge(lastMoveTrial, available, player2);
            player2.outputRemove(lastMove);
            if (numberOfStones == 0) {
                advancedWinDisplay(player2);
                break;
            }
        }
    }

    public int getInitialStone() {
        return initialStones;
    }

    public int getNumberOfStones() {
        return numberOfStones;
    }

    public Scanner getReader() {
        return reader;
    }

    public boolean[] getAvailable() {
        return available;
    }

    public String getLastMove() {
        return lastMove;
    }

}
