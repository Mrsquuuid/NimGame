package Assignment2;

import java.util.Scanner;

/**
 * Description
 * This class stores the information of player and provides the accessor and mutator methods
 * yuzyou@student.unimelb.edu.au
 * No.1159774
 *
 * @author Yuzhe You
 */

public class NimGame {
    private int upperBound = 0; // the maximum number of stones the player can remove
    private int initialNumber = 0;// number of initial stones
    private int numberOfStones = 0;// number of stones in real time
    private int round = 0;// record how many rounds the game has gone through

    // entrance of a game
    public void run(NimGame game, Scanner reader, NimPlayer player1, NimPlayer player2) {
        numberOfStones = initialNumber;
        game.setRound(1);
        while (numberOfStones > 0) {
            System.out.println();
            if (game.round % 2 == 1) {
                oneRound(game, reader, numberOfStones, player1);
            } else {
                oneRound(game, reader, numberOfStones, player2);
            }
            game.round = game.round + 1;
        }
        player1.setGames(player1.getGames() + 1);
        player2.setGames(player2.getGames() + 1);
        System.out.println();
        System.out.println("Game Over");

        if (!isP1Winner(game.round)) {
            player1.setWins(player1.getWins() + 1);
            System.out.println(player1.getFirstName() + " " + player1.getLastName() + " wins!");
        } else {
            player2.setWins(player2.getWins() + 1);
            System.out.println(player2.getFirstName() + " " + player2.getLastName() + " wins!");
        }
    }

    // the operation performed by a player in one round of the game
    private void oneRound(NimGame game, Scanner reader, int stoneNumber, NimPlayer player) {
        System.out.println(stoneNumber + " stones left:" + outputStars(stoneNumber));
        System.out.println(player.getFirstName() + "'s turn - remove how many?");
        player.outputRemove(reader, upperBound, numberOfStones);
        int numberToRemove = 0;
        Boolean isValid = true;
        try {
            numberToRemove = player.removeStone(reader, upperBound, numberOfStones);
            if (numberToRemove <= 0 || numberToRemove > upperBound || numberOfStones - numberToRemove < 0) {
                isValid = false;
                throw new InvalidMoveException("Invalid move. You must remove between 1 and " +
                        Math.min(upperBound, numberOfStones) + " stones.");
            }
        } catch (InvalidMoveException e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        } finally {
            if (isValid == true) {
                game.setStoneNumber(game.getStoneNumber() - numberToRemove);
            } else {
                oneRound(game, reader, stoneNumber, player);//
            }
        }
    }

    // output the number of stones
    private String outputStars(int stoneNumber) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < stoneNumber; i++) {
            s.append(" *");
        }
        return s.toString();
    }

    // output the final outcome of the two players
    private String outputResult(int round) {
        if (round == 0) {
            return "0 game";
        } else if (round == 1) {
            return "1 game";
        } else {
            return round + " games";
        }
    }

    private boolean isP1Winner(int round) {
        return round % 2 != 1;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    public int getNumberOfStones() {
        return numberOfStones;
    }

    public void setNumberOfStones(int numberOfStones) {
        this.numberOfStones = numberOfStones;
    }

    public int getInitialNumber() {
        return initialNumber;
    }

    public void setInitialNumber(int initialNumber) {
        this.initialNumber = initialNumber;
    }

    public int getStoneNumber() {
        return numberOfStones;
    }

    public void setStoneNumber(int stoneNumber) {
        this.numberOfStones = stoneNumber;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
