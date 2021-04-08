package Assignment2;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Description
 * This class stores the information of player and provides the accessor and mutator methods,
 * which is the super class of NimHumanPlayer and NimAIPlayer.
 * yuzyou@student.unimelb.edu.au
 * No.1159774
 *
 * @author Yuzhe You
 */

public abstract class NimPlayer implements Serializable {
    protected String userName;
    protected String firstName, lastName;
    protected int wins, games, numberToRemove;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public String getUserName() {
        return userName;
    }

    public int getWins() {
        return wins;
    }

    public int getGames() {
        return games;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getWinRate() {
        if (games == 0) {
            return 0;
        } else return (int) (((double) this.wins / (double) this.games) * 100);
    }

    public abstract void outputRemove(Scanner scanner, int upperBound, int numberOfStones);

    public abstract void outputRemove(String output);

    public abstract int removeStone(Scanner reader, int upperBound, int numberOfStones);

    public abstract String advancedRemove(NimAdvancedGame advancedGame);

    public String toString() {
        return userName + ", " + firstName + ", " + lastName + ", " + games + " games, " + wins + " wins";
    }
}
