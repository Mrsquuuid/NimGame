package Assignment2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Description
 * The class Nimsys is the main application of Nim.
 * The class PlayerList is to store the information of game players.
 * yuzyou@student.unimelb.edu.au
 * No.1159774
 *
 * @author Yuzhe You
 */

public class Nimsys {
    private Scanner reader;
    private static ArrayList<NimPlayer> players;

    public Nimsys(Scanner scanner) {
        this.reader = scanner;
        players = new ArrayList<NimPlayer>();
    }

    public void loadGame(String filename) {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(filename));
            PlayerList playerData = (PlayerList) inputStream.readObject();
            players = playerData.players;
            // System.out.println("Game loaded: ");
        } catch (FileNotFoundException e) {
            // System.out.println("Could not open file: " + filename);
        } catch (IOException e) {
            // System.out.println("Could not read from file.");
        } catch (ClassNotFoundException e) {
            // System.out.println("Class not found.");
        }
    }

    public void saveStatus(String filename) {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(filename));
            PlayerList playerData = new PlayerList(players);
            outputStream.writeObject(playerData);
            outputStream.close();
            //System.out.println("Game state written to: " + filename);
        } catch (IOException e) {
            //System.out.println("Could not open file: " + filename);
            System.out.println(e.getMessage());
        }
    }

    // read all kinds of commands in this game.
    public static void readCommand(Scanner reader, Commands command, ArrayList<NimPlayer> players) {
        String action = reader.nextLine();
        StringTokenizer st = new StringTokenizer(action, " ");
        int index = 0;
        String[] tokens = new String[5];
        while (st.hasMoreTokens()) {
            tokens[index] = st.nextToken();
            index++;
        }
        String commandKey = tokens[0];
        if (!"exit".equals(commandKey)) {
            try {
                if ("addplayer".equals(commandKey)) {
                    if (index >= 4) {
                        String userName = tokens[1].substring(0, tokens[1].length() - 1);
                        String lastName = tokens[2].substring(0, tokens[2].length() - 1);
                        String firstName = tokens[3];
                        command.addPlayer(players, userName, lastName, firstName);
                        System.out.println();
                    } else throw new InvalidArgumentsException("Incorrect number of arguments supplied to command.");
                } else if ("addaiplayer".equals(commandKey)) {
                    if (index >= 4) {
                        String userName = tokens[1].substring(0, tokens[1].length() - 1);
                        String lastName = tokens[2].substring(0, tokens[2].length() - 1);
                        String firstName = tokens[3];
                        command.addAIPlayer(players, userName, lastName, firstName);
                        System.out.println();
                    } else throw new InvalidArgumentsException("Incorrect number of arguments supplied to command.");
                } else if ("removeplayer".equals(commandKey)) {
                    if (tokens[1] == null) {
                        command.removePlayer(players, reader);
                    } else {
                        String userName = tokens[1];
                        command.removePlayer(players, userName);
                    }
                    System.out.println();
                } else if ("editplayer".equals(commandKey)) {
                    if (index >= 4) {
                        String userName = tokens[1].substring(0, tokens[1].length() - 1);
                        String lastName = tokens[2].substring(0, tokens[2].length() - 1);
                        String firstName = tokens[3];
                        command.editPlayer(players, userName, lastName, firstName);
                        System.out.println();
                    } else throw new InvalidArgumentsException("Incorrect number of arguments supplied to command.");
                } else if ("resetstats".equals(commandKey)) {
                    if (tokens[1] == null) {
                        command.resetStats(players, reader);
                    } else {
                        String userName = tokens[1];
                        command.resetStats(players, userName);
                    }
                    System.out.println();
                } else if ("displayplayer".equals(commandKey)) {
                    if (tokens[1] == null) {
                        command.displayPlayer(players);
                    } else {
                        String userName = tokens[1];
                        command.displayPlayer(players, userName);
                    }
                    System.out.println();
                } else if ("rankings".equals(commandKey)) {
                    if (tokens[1] == null) {
                        command.rankingsDescending(players);
                    } else {
                        String sequence = tokens[1];
                        if (sequence.equals("asc")) {
                            command.rankingsAscending(players);
                        } else if (sequence.equals("desc")) {
                            command.rankingsDescending(players);
                        }
                    }
                    System.out.println();
                } else if ("startgame".equals(commandKey)) {
                    if (index >= 5) {
                        int initialStones = Integer.valueOf(tokens[1].substring(0, tokens[1].length() - 1));
                        int upperBound = Integer.valueOf(tokens[2].substring(0, tokens[2].length() - 1));
                        String userName1 = tokens[3].substring(0, tokens[3].length() - 1);
                        String userName2 = tokens[4];
                        int indexOfPlayer1 = -1, indexOfPlayer2 = -1;
                        for (int i = 0; i < players.size(); i++) {
                            if (userName1.equals(players.get(i).getUserName())) {
                                indexOfPlayer1 = i;
                            }
                            if (userName2.equals(players.get(i).getUserName())) {
                                indexOfPlayer2 = i;
                            }
                        }
                        if (indexOfPlayer1 >= 0 && indexOfPlayer2 >= 0) {
                            if (indexOfPlayer1 == indexOfPlayer2) {
                                System.out.println("No two same username!");
                            } else
                                command.startGame(reader, initialStones, upperBound,
                                        players.get(indexOfPlayer1), players.get(indexOfPlayer2));
                        } else {
                            System.out.println("One of the players does not exist.");
                        }
                        System.out.println();
                    } else throw new InvalidArgumentsException("Incorrect number of arguments supplied to command.");
                } else if ("startadvancedgame".equals(commandKey)) {
                    if (index >= 4) {
                        int initialStones = Integer.valueOf(tokens[1].substring(0, tokens[1].length() - 1));
                        String userName1 = tokens[2].substring(0, tokens[2].length() - 1);
                        String userName2 = tokens[3];
                        int indexOfPlayer1 = -1, indexOfPlayer2 = -1;
                        for (int i = 0; i < players.size(); i++) {
                            if (userName1.equals(players.get(i).getUserName())) {
                                indexOfPlayer1 = i;
                            }
                            if (userName2.equals(players.get(i).getUserName())) {
                                indexOfPlayer2 = i;
                            }
                        }
                        if (indexOfPlayer1 >= 0 && indexOfPlayer2 >= 0) {
                            if (indexOfPlayer1 == indexOfPlayer2) {
                                System.out.println("No two same username!");
                            } else
                                command.startAdvancedGame(reader, initialStones,
                                        players.get(indexOfPlayer1), players.get(indexOfPlayer2));
                        } else {
                            System.out.println("One of the players does not exist.");
                        }
                        System.out.println();
                    } else throw new InvalidArgumentsException("Incorrect number of arguments supplied to command.");
                } else if ("commands".equals(commandKey)) {
                    command.commandList();
                    System.out.println();
                } else if ("help".equals(commandKey)) {
                    command.help();
                    System.out.println();
                } else {
                    throw new InvalidCommandException("'" + commandKey + "'" + " is not a valid command.");
                }
            } catch (InvalidArgumentsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
                System.out.println();
            } finally {
                System.out.print("$ ");
                readCommand(reader, command, players);
            }
        } else command.exit();
    }

     public static void main(String[] args) {
        System.out.println("Welcome to Nim");
        System.out.println();
        System.out.println("Please enter a command to continue or type 'help' for more information");

        Scanner reader = new Scanner(System.in);
        Nimsys system = new Nimsys(reader);
        Commands command = new Commands();

        String filename = "players.dat";
        File fileObject = new File(filename);
        if (fileObject.exists()) {
            system.loadGame(filename);
        }
        System.out.println();
        System.out.print("$ ");
        readCommand(reader, command, players); // read commands
        system.saveStatus(filename);
    }
}

class PlayerList implements Serializable {
    public ArrayList<NimPlayer> players;

    public PlayerList(ArrayList<NimPlayer> players) {
        this.players = players;
    }
}



