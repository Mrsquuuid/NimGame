package Assignment2;

import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Description
 * This class represents AI players.
 * yuzyou@student.unimelb.edu.au
 * No.1159774
 *
 * @author Yuzhe You
 */

public class NimAIPlayer extends NimPlayer {
    public NimAIPlayer() {
    }

    public NimAIPlayer(String userName, String lastName, String firstName) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public NimAIPlayer(String userName, String lastName, String firstName, int wins, int games) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.wins = wins;
        this.games = games;
    }

    @Override
    // normal Nimgame
    public int removeStone(Scanner scanner, int upperBound, int numberOfStones) {
        //after removing stones, the number of stones mod upperbound + 1 = 0;
        Random random = new Random();
        if (numberOfStones % (upperBound + 1) == 1) {
            if (numberOfStones > upperBound) {
                return random.nextInt(upperBound) + 1;
            } else return random.nextInt(numberOfStones) + 1;
        } else {
            if ((numberOfStones % (upperBound + 1) == 0)) {
                return upperBound;
            } else return numberOfStones % (upperBound + 1) - 1;
        }
    }

    @Override
    public String advancedRemove(NimAdvancedGame advancedGame) {
        return advancedMove(advancedGame.getAvailable(), advancedGame.getLastMove());
    }

    // exclude the first and the final round
    public String validMove(int length, String lastMove, boolean[] available) {
        StringTokenizer move = new StringTokenizer(lastMove);
        int index = Integer.parseInt(move.nextToken()) - 1;
        int numberToRemove = Integer.parseInt(move.nextToken());
        index = length - 1 - index;
        if (numberToRemove == 2) {
            if (index > length / 2) {
                index--;
            }
            if ((index + 1) > length || available[index + 1] == false) {
                numberToRemove = 1;
            }
        }
        while (available[index] == false) {
            index = (int) (0 + Math.random() * ((length - 1) - 0 + 1));
        }
        // judge whether stones to remove is legal
        if (numberToRemove == 2 && ((index + 1) > length || available[index + 1] == false)) {
            numberToRemove = 1;
        }
        String position = String.valueOf(index + 1);
        String number = String.valueOf(numberToRemove);
        return position + " " + number;
    }

    // move which ensures victory
    public String advancedMove(boolean[] available, String lastMove) {
        String move;
        boolean flag = true;
        int length = available.length;
        int numberOfLeftOver = 0, index = 0;
        for (int i = 0; i < length; i++) {
            if (!available[i]) {
                flag = false;
            } else {
                numberOfLeftOver++;
                index = i;
            }
        }
        // 1 stone left
        if (numberOfLeftOver == 1) {
            String position = String.valueOf(index + 1);
            move = position + " 1";
            return move;
        }
        // 2 stones left
        else if (numberOfLeftOver == 2 && available[index - 1] == true) {
            String position = String.valueOf(index);
            move = position + " 2";
            return move;
        // number of initial stones is even
        } else if ((length % 2) == 0) {
            if (available[length / 2] == true && available[length / 2 - 1] == true && flag == true) {
                String position = String.valueOf(length / 2);
                move = position + " 2";
                return move;
            } else if (numberOfLeftOver == length - 1 && (available[0] == false || available[length - 1] == false)) {
                String position;
                if (!available[0]) {
                    position = String.valueOf(length / 2 + 1);
                } else {
                    position = String.valueOf(length / 2);
                }
                move = position + " 1";
                return move;
            } else {
                move = validMove(length, lastMove, available);
                return move;
            }
        } else {
            // number of initial stones is odd
            if (available[length / 2] == true && flag == true) {
                String position = String.valueOf(length / 2 + 1);
                move = position + " 1";
                return move;
            } else if (numberOfLeftOver == length - 1 && (available[0] == false || available[length - 1] == false)) {
                String position;
                if (available[0] == false) {
                    position = String.valueOf(length / 2 + 1);
                } else {
                    position = String.valueOf(length / 2);
                }
                move = position + " 2";
                return move;
            } else {
                move = validMove(length, lastMove, available);
                return move;
            }
        }
    }

    @Override
    public void outputRemove(Scanner scanner, int upperBound, int numberOfStones) { }

    @Override
    public void outputRemove(String output) {
        System.out.println();
    }

}
