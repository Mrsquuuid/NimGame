package Assignment2;

import java.util.Scanner;

/**
 * Description
 * This class represents human players.
 * yuzyou@student.unimelb.edu.au
 * No.1159774
 *
 * @author Yuzhe You
 */

public class NimHumanPlayer extends NimPlayer {
    public NimHumanPlayer(String userName, String lastName, String firstName) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public NimHumanPlayer(String userName, String lastName, String firstName, int wins, int games) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.wins = wins;
        this.games = games;
    }

    @Override
    // normal Nimgame
    public int removeStone(Scanner reader, int upperBound, int numberOfStones) {
        if (reader.hasNextInt()) {
            numberToRemove = Integer.valueOf(reader.nextLine());
        } else {
            numberToRemove = 0;
            String buffer = reader.nextLine();
        }
        return numberToRemove;
    }

    @Override
    public String advancedRemove(NimAdvancedGame advancedGame) {
        String Move = advancedGame.getReader().nextLine();
        return Move;
    }

    @Override
    public void outputRemove(Scanner scanner, int upperBound, int numberOfStones) { }

    @Override
    public void outputRemove(String output) {
        System.out.println();
    }
}
