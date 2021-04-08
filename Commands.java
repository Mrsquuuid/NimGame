package Assignment2;

import java.util.*;

/**
 * Description
 * This class includes commands of NimGame.
 * yuzyou@student.unimelb.edu.au
 * No.1159774
 *
 * @author Yuzhe You
 */

public class Commands {
    private static final int PLAYERS_CAPACITY = 100;

    public void startGame(Scanner reader, int initialStones, int upperBound, NimPlayer player1, NimPlayer player2) {
        NimGame game = new NimGame();
        game.setInitialNumber(initialStones);
        game.setUpperBound(upperBound);
        System.out.println();
        System.out.println("Initial stone count: " + initialStones);
        System.out.println("Maximum stone removal: " + upperBound);
        System.out.println("Player 1: " + player1.getFirstName() + " " + player1.getLastName());
        System.out.println("Player 2: " + player2.getFirstName() + " " + player2.getLastName());

        game.run(game, reader, player1, player2);
    }

    public void exit() {
        System.out.println();
    }

    public void help() {
        System.out.println("Type 'commands' to list all available commands");
        System.out.println("Type 'startgame' to play game");
        System.out.println("The player that removes the last stone loses!");
    }

    public void commandList() {
        String[] command = new String[15];
        command[0] = "exit";
        command[1] = "addplayer";
        command[2] = "addaiplayer";
        command[3] = "removeplayer";
        command[4] = "editplayer";
        command[5] = "resetstats";
        command[6] = "displayplayer";
        command[7] = "rankings";
        command[8] = "startgame";
        command[9] = "startadvancedgame";
        command[10] = "commands";
        command[11] = "help";
        String[] parameters = new String[15];
        parameters[0] = "(no parameters)";
        parameters[1] = "(username, secondname, firstname)";
        parameters[2] = "(username, secondname, firstname)";
        parameters[3] = "(optional username)";
        parameters[4] = "(username, secondname, firstname)";
        parameters[5] = "(optional username)";
        parameters[6] = "(optional username)";
        parameters[7] = "(optional asc)";
        parameters[8] = "(initialstones, upperbound, username1, username2)";
        parameters[9] = "(initialstones, username1, username2)";
        parameters[10] = "(no parameters)";
        parameters[11] = "(no parameters)";

        int i = 0;
        while (command[i] != null) {
            System.out.printf("%2s" + ": " + "%-18s" + "%s" + "\n", i + 1, command[i], parameters[i]);
            i++;
        }
    }

    public void addPlayer(ArrayList<NimPlayer> players, String userName, String lastName, String firstName) {
        if (players.size() < PLAYERS_CAPACITY) {
            Boolean alreadyExist = false;
            for (NimPlayer player : players) {
                if (player.getUserName().equals(userName)) {
                    System.out.println("The player already exists.");
                    alreadyExist = true;
                }
            }
            if (alreadyExist == false) {
                NimPlayer player = new NimHumanPlayer(userName, lastName, firstName);
                players.add(player);
            }
        } else {
            System.out.println("Number of payers out of bounds");
        }
    }

    public void addAIPlayer(ArrayList<NimPlayer> players, String userName, String lastName, String firstName) {
        if (players.size() < 100) {
            Boolean alreadyExist = false;
            for (NimPlayer player : players) {
                if (player.getUserName().equals(userName)) {
                    System.out.println("The player already exists.");
                    alreadyExist = true;
                }
            }
            if (alreadyExist == false) {
                NimPlayer player = new NimAIPlayer(userName, lastName, firstName);
                players.add(player);
            }
        } else {
            System.out.println("Number of payers out of bounds");
        }
    }

    public void removePlayer(ArrayList<NimPlayer> players, Scanner reader) {
        System.out.println("Are you sure you want to remove all players? (y/n)");
        String confirmation = reader.nextLine();
        if ("y".equals(confirmation)) {
            Iterator<NimPlayer> iterator = players.iterator();
            while (iterator.hasNext()) {
                NimPlayer player = iterator.next();
                iterator.remove();
            }
        }
    }

    public void removePlayer(ArrayList<NimPlayer> players, String userName) {
        Boolean alreadyExist = false;
        Iterator<NimPlayer> iterator = players.iterator();
        while (iterator.hasNext()) {
            NimPlayer nextPlayer = iterator.next();
            if (nextPlayer.getUserName().equals(userName)) {
                alreadyExist = true;
                iterator.remove();
            }
        }
        if (alreadyExist == false) {
            System.out.println("The player does not exist.");
        }
    }

    public void editPlayer(ArrayList<NimPlayer> players, String userName, String newFamilyName, String newGivenName) {
        Boolean alreadyExist = false;
        for (NimPlayer player : players) {
            if ((player.getUserName()).equals(userName)) {
                alreadyExist = true;
                player.setFirstName(newGivenName);
                player.setLastName(newFamilyName);
            }
        }
        if (alreadyExist == false) {
            System.out.println("The player does not exist.");
        }
    }

    public void resetStats(ArrayList<NimPlayer> players, Scanner reader) {
        System.out.println("Are you sure you want to reset all player statistics? (y/n)");
        String confirmation = reader.nextLine();
        if ("y".equals(confirmation)) {
            for (NimPlayer player : players) {
                player.setWins(0);
                player.setGames(0);
            }
        }
    }

    public void resetStats(ArrayList<NimPlayer> players, String userName) {
        Boolean alreadyExist = false;
        for (NimPlayer player : players) {
            if (player.getUserName().equals(userName)) {
                alreadyExist = true;
                player.setWins(0);
                player.setGames(0);
            }
        }
        if (alreadyExist == false) {
            System.out.println("The player does not exist.");
        }
    }

    public void displayPlayer(ArrayList<NimPlayer> players) {
        ArrayList<NimPlayer> sortedList = new ArrayList<>();
        sortedList.addAll(players);
        Collections.sort(sortedList, new Comparator<NimPlayer>() {
            public int compare(NimPlayer player1, NimPlayer player2) {
                if (player1.getUserName().compareTo(player2.getUserName()) > 0) {
                    return 1;
                } else if (player1.getUserName().compareTo(player2.getUserName()) < 0) {
                    return -1;
                } else return 0;
            }
        });
        for (NimPlayer player : sortedList) {
            System.out.println(player.toString());
        }
    }

    public void displayPlayer(ArrayList<NimPlayer> players, String userName) {
        Boolean alreadyExist = false;
        for (NimPlayer player : players) {
            if (player.getUserName().equals(userName)) {
                alreadyExist = true;
                System.out.println(player.toString());
            }
        }
        if (alreadyExist == false) {
            System.out.println("The player does not exist.");
        }
    }

    public void rankingsAscending(ArrayList<NimPlayer> players) {
        ArrayList<NimPlayer> sortedList = new ArrayList<>();
        sortedList.addAll(players);
        Collections.sort(sortedList, new Comparator<NimPlayer>() {
            public int compare(NimPlayer player1, NimPlayer player2) {
                if (player1.getWinRate() == player2.getWinRate()) {
                    if (player1.getUserName().compareTo(player2.getUserName()) > 0) {
                        return 1;
                    } else if (player1.getUserName().compareTo(player2.getUserName()) < 0) {
                        return -1;
                    } else return 0;
                } else if (player1.getWinRate() > player2.getWinRate()) {
                    return 1;
                } else return -1;
            }
        });
        if (players.size() < 10) {
            for (NimPlayer player : sortedList) {
                String ratio = String.valueOf(player.getWinRate()) + "%";
                System.out.printf("%-5s" + "| " + "%02d" + " games | " + "%s" + " " + "%s",
                        ratio, player.getGames(), player.getFirstName(), player.getLastName());
                System.out.println();
            }
        } else {
            for (int i = 0; i < 10; i++) {
                String ratio = String.valueOf(players.get(i).getWinRate()) + "%";
                System.out.printf("%-5s" + "| " + "%02d" + " games | " + "%s" + " " + "%s",
                        ratio, players.get(i).getGames(), players.get(i).getFirstName(), players.get(i).getLastName());
                System.out.println();
            }
        }
    }

    public void rankingsDescending(ArrayList<NimPlayer> players) {
        ArrayList<NimPlayer> sortedList = new ArrayList<>();
        sortedList.addAll(players);
        Collections.sort(sortedList, new Comparator<NimPlayer>() {
            public int compare(NimPlayer player1, NimPlayer player2) {
                if (player1.getWinRate() == player2.getWinRate()) {
                    if (player1.getUserName().compareTo(player2.getUserName()) > 0) {
                        return 1;
                    } else if (player1.getUserName().compareTo(player2.getUserName()) < 0) {
                        return -1;
                    } else return 0;
                } else if (player1.getWinRate() > player2.getWinRate()) {
                    return -1;
                } else return 1;
            }
        });
        if (players.size() < 10) {
            for (NimPlayer player : sortedList) {
                String ratio = String.valueOf(player.getWinRate()) + "%";
                System.out.printf("%-5s" + "| " + "%02d" + " games | " + "%s" + " " + "%s",
                        ratio, player.getGames(), player.getFirstName(), player.getLastName());
                System.out.println();
            }
        } else {
            for (int i = 0; i < 10; i++) {
                String ratio = String.valueOf(players.get(i).getWinRate()) + "%";
                System.out.printf("%-5s" + "| " + "%02d" + " games | " + "%s" + " " + "%s",
                        ratio, players.get(i).getGames(), players.get(i).getFirstName(), players.get(i).getLastName());
                System.out.println();
            }
        }
    }

    public void startAdvancedGame(Scanner reader, int initialStones, NimPlayer player1, NimPlayer player2) {
        NimAdvancedGame advancedGame = new NimAdvancedGame(reader, initialStones, player1, player2);
        boolean[] available = new boolean[initialStones];
        for (int i = 0; i < available.length; i++) {
            available[i] = true;
        }
        advancedGame.run();
    }
}
