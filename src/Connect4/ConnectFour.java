package Connect4;

import java.util.Random;
import java.util.ArrayList;
import java.io.*;

public class ConnectFour {
    
    private ArrayList<ArrayList<Integer>> data;
    private boolean redsTurn;
    private boolean randomAI;
    private boolean smartAI;
    public ArrayList<Integer> log;
    
    public ConnectFour() {
        data = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                row.add(0);
            }
            data.add(row);
        }
        
        redsTurn = true;
        randomAI = false;
        smartAI = false;
        
        log = new ArrayList<Integer>();
    }
    
    public int get(int row, int col) {
        return data.get(row).get(col);
    }
    public boolean getRedsTurn() {
        return redsTurn;
    }
    
    public int play(int col) {
        for (int i = 5; i >= 0; i--) {
            if (data.get(i).get(col) == 0) {
                if (redsTurn)
                    data.get(i).set(col, 1);
                else
                    data.get(i).set(col, 2);
                redsTurn = !redsTurn;
                log.add(col);
                return i;
            }
        }
        return -1;
    }
    
    public int[] playRandomAI() {
        Random rand = new Random();
        int col = rand.nextInt(7);
        while (data.get(0).get(col) != 0) { //Ensuring slot is not full
            col = rand.nextInt(7);
        }
        return new int[] {play(col), col};
    }
    public void setRandomAI(boolean condition) {
        randomAI = condition;
    }
    public boolean isRandomAI() {
        return randomAI;
    }
    
    public int[] playSmartAI() { // Trys to win first or block second
        for (int col = 0; col <= 6; col++) {
            if (data.get(0).get(col) != 0) { //Chcking if collumn is playable
                continue;
            }
            
            //Attempting to win
            int simulatedRow = simulateMove(col, false);
            if (checkWin(simulatedRow, col)) {
                play(col);
                return new int[]{simulatedRow, col};
            }
            data.get(simulatedRow).set(col, 0);
            
            //Attempting to block
            simulatedRow = simulateMove(col, true);
            if (checkWin(simulatedRow, col)) {
                data.get(simulatedRow).set(col, 0);
                play(col);
                return new int[]{simulatedRow, col};
            }
            data.get(simulatedRow).set(col, 0);
        }
        
        return playRandomAI();
    }
    private int simulateMove(int col, boolean forOpponent) {
        int playerToken;
        if ((redsTurn && !forOpponent) || (!redsTurn && forOpponent))
            playerToken = 1;
        else
            playerToken = 2;
        for (int i = 5; i >= 0; i--) {
            if (data.get(i).get(col) == 0) {
                data.get(i).set(col, playerToken);
                return i;
            }
        }
        return -1;
    }
    public void setSmartAI(boolean condition) {
        smartAI = condition;
    }
    public boolean isSmartAI() {
        return smartAI;
    }
    
    public boolean checkWin(int row, int col) {
        return checkWinVerticle(row, col) || checkHorizontal(row, col) || checkDiagonal1(row, col) || checkDiagonal2(row, col);
    }
    private boolean checkWinVerticle(int row, int col) {
        int matches = 0;
        if (row <= 2)
            for (int i = 1; i < 4; i++) {
                if (data.get(row).get(col) == data.get(row +i).get(col))
                    matches++;
                else
                    break;
                if (matches == 3)
                    return true;
            }
        return false;
    }
    private boolean checkHorizontal(int row, int col) {
        int matches = 0; 
        for (int i = 1; col - i >= 0; i++)
            if (data.get(row).get(col) == data.get(row).get(col - i))
                matches++;
            else
                break;
        for (int i = 1; i + col <= 6 && matches < 3; i++)
            if (data.get(row).get(col) == data.get(row).get(col + i))
                matches++;
            else
                break;
        return matches >= 3;
    }
    private boolean checkDiagonal1(int row, int col) { //Checks from bottom left to top right
        int matches = 0;
    // Check from the current position up and to the right
    for (int i = 1; (row - i >= 0 && col + i <= 6); i++) {
        if (data.get(row).get(col) == data.get(row - i).get(col + i)) {
            matches++;
        } else {
            break;
        }
    }
    // Check from the current position down and to the left
    for (int i = 1; (row + i <= 5 && col - i >= 0); i++) {
        if (data.get(row).get(col) == data.get(row + i).get(col - i)) {
            matches++;
        } else {
            break;
        }
    }
    return matches >= 3;
    }
    private boolean checkDiagonal2(int row, int col) { //Checks from top left to bottom right
        int matches = 0;
        for (int i = 1; (row - i >= 0 && col - i >= 0); i++)
            if (data.get(row).get(col) == data.get(row - i).get(col - i))
                matches++;
            else
                break;
        for (int i = 1; (row + i <= 5 && col + i <= 6 && matches< 3); i++)
            if (data.get(row).get(col) == data.get(row + i).get(col + i))
                matches++;
            else
                break;
        return matches >= 3;
    }
    
    public void saveGame(String filename) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (ArrayList<Integer> row : data) {
                for (int i = 0; i < row.size(); i++) {
                    out.print(row.get(i));
                    if (i < row.size() - 1) out.print(",");
                }
                out.println();
            }
            out.println(redsTurn);
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }
    public void loadGame(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            for (int i = 0; i < 6; i++) {
                line = br.readLine();
                String[] values = line.split(",");
                for (int j = 0; j < values.length; j++) {
                    data.get(i).set(j, Integer.parseInt(values[j]));
                }
            }
            redsTurn = Boolean.parseBoolean(br.readLine());
        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
        }
    }
    
    public void printBoard() {
        System.out.println("Current game state:");
        for (int row = 0; row < data.size(); row++) {
            for (int col = 0; col < data.get(row).size(); col++) {
                int cell = data.get(row).get(col);
                char displayChar = ' ';
                switch (cell) {
                    case 1: displayChar = 'R'; break; // 'R' for Red
                    case 2: displayChar = 'Y'; break; // 'Y' for Yellow
                    default: displayChar = '.'; // '.' for empty
                }
                System.out.print("|" + displayChar);
            }
            System.out.println("|");
        }
        // Print column numbers for reference
        System.out.println(" 0 1 2 3 4 5 6 ");
    }
}

