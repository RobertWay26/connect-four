package Connect4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Paint;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class Controller {
    //Link to main
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
    //BUTTONS
    @FXML private Button col0;
    @FXML private Button col1;
    @FXML private Button col2;
    @FXML private Button col3;
    @FXML private Button col4;
    @FXML private Button col5;
    @FXML private Button col6;
    
    //SLOTS
    @FXML private Circle r0c0;
    @FXML private Circle r0c1;
    @FXML private Circle r0c2;
    @FXML private Circle r0c3;
    @FXML private Circle r0c4;
    @FXML private Circle r0c5;
    @FXML private Circle r0c6;
    @FXML private Circle r1c0;
    @FXML private Circle r1c1;
    @FXML private Circle r1c2;
    @FXML private Circle r1c3;
    @FXML private Circle r1c4;
    @FXML private Circle r1c5;
    @FXML private Circle r1c6;
    @FXML private Circle r2c0;
    @FXML private Circle r2c1;
    @FXML private Circle r2c2;
    @FXML private Circle r2c3;
    @FXML private Circle r2c4;
    @FXML private Circle r2c5;
    @FXML private Circle r2c6;
    @FXML private Circle r3c0;
    @FXML private Circle r3c1;
    @FXML private Circle r3c2;
    @FXML private Circle r3c3;
    @FXML private Circle r3c4;
    @FXML private Circle r3c5;
    @FXML private Circle r3c6;
    @FXML private Circle r4c0;
    @FXML private Circle r4c1;
    @FXML private Circle r4c2;
    @FXML private Circle r4c3;
    @FXML private Circle r4c4;
    @FXML private Circle r4c5;
    @FXML private Circle r4c6;
    @FXML private Circle r5c0;
    @FXML private Circle r5c1;
    @FXML private Circle r5c2;
    @FXML private Circle r5c3;
    @FXML private Circle r5c4;
    @FXML private Circle r5c5;
    @FXML private Circle r5c6;
    
    //WINS
    @FXML private Circle winnerCircle;
    @FXML private Text winText;
    
    //TURN
    @FXML private Circle turnCircle;
    
    //GAME MODE
    @FXML private ToggleButton oneV1Button;
    @FXML private ToggleButton randomAIButton;
    @FXML private ToggleButton smartAIButton;
    
    //Saving/Reset
    @FXML private Button resetButton;
    @FXML private Button saveButton;
    @FXML private Button loadSaveButton;
    @FXML private TextField save;
    @FXML private TextField loadSave;
    
    @FXML private void handleCol0() {
        handlePlay(0);
    }
    @FXML private void handleCol1() {
        handlePlay(1);
    }
    @FXML private void handleCol2() {
        handlePlay(2);
    }
    @FXML private void handleCol3() {
        handlePlay(3);
    }
    @FXML private void handleCol4() {
        handlePlay(4);
    }
    @FXML private void handleCol5() {
        handlePlay(5);
    }
    @FXML private void handleCol6() {
        handlePlay(6);
    }
    private void handlePlay(int col) {
        boolean redsTurn = mainApp.game.getRedsTurn();
        int row = mainApp.game.play(col);
        updateTurnCircle(redsTurn);
        setFillCircle(redsTurn, row, col);
        handleWinner(redsTurn, row, col);
        
        if (mainApp.game.isRandomAI()) {
            handlePlayRandomAI();
        }
        else if (mainApp.game.isSmartAI()) {
            handlePlaySmartAI();
        }
        
        System.out.println(mainApp.game.log);
    }
    
    @FXML private void switchTo1v1() {
        randomAIButton.setSelected(false);
        smartAIButton.setSelected(false);
        mainApp.game.setRandomAI(false);
        mainApp.game.setSmartAI(false);
    }
    @FXML private void switchToRandomAI() {
        oneV1Button.setSelected(false);
        smartAIButton.setSelected(false);
        mainApp.game.setRandomAI(true);
        mainApp.game.setSmartAI(false);
    }
    @FXML private void switchToSmartAI() {
        oneV1Button.setSelected(false);
        randomAIButton.setSelected(false);
        mainApp.game.setRandomAI(false);
        mainApp.game.setSmartAI(true);
    }
    private void handlePlaySmartAI() {
        boolean redsTurn = mainApp.game.getRedsTurn();
            int[] rowAndCol = mainApp.game.playSmartAI();
            updateTurnCircle(redsTurn);
            setFillCircle(redsTurn, rowAndCol[0], rowAndCol[1]);
            handleWinner(redsTurn, rowAndCol[0], rowAndCol[1]);
    }
    private void handlePlayRandomAI() {
            boolean redsTurn = mainApp.game.getRedsTurn();
            int[] rowAndCol = mainApp.game.playRandomAI();
            updateTurnCircle(redsTurn);
            setFillCircle(redsTurn, rowAndCol[0], rowAndCol[1]);
            handleWinner(redsTurn, rowAndCol[0], rowAndCol[1]);
    }
    
    private void updateTurnCircle(boolean redsTurn) {
        if (!redsTurn)
            turnCircle.setFill(Color.RED);
        else
            turnCircle.setFill(Color.YELLOW);
    }

    private void handleWinner(boolean redsTurn, int row, int col) {
        if (mainApp.game.checkWin(row, col)) {
            if (redsTurn)
                winnerCircle.setFill(Color.RED);
            else
                winnerCircle.setFill(Color.YELLOW);
            winText.setVisible(true);
            System.out.println("WINNER!");
            
            col0.setDisable(true);
            col1.setDisable(true);
            col2.setDisable(true);
            col3.setDisable(true);
            col4.setDisable(true);
            col5.setDisable(true);
            col6.setDisable(true);
            
            oneV1Button.setDisable(true);
            randomAIButton.setDisable(true);
            smartAIButton.setDisable(true);
        }
    }
    
    @FXML private void reset() {
        mainApp.game = new ConnectFour();
        
        r0c0.setFill(Paint.valueOf("#7389ae"));
        r1c0.setFill(Paint.valueOf("#7389ae"));
        r2c0.setFill(Paint.valueOf("#7389ae"));
        r3c0.setFill(Paint.valueOf("#7389ae"));
        r4c0.setFill(Paint.valueOf("#7389ae"));
        r5c0.setFill(Paint.valueOf("#7389ae"));
        
        r0c1.setFill(Paint.valueOf("#7389ae"));
        r1c1.setFill(Paint.valueOf("#7389ae"));
        r2c1.setFill(Paint.valueOf("#7389ae"));
        r3c1.setFill(Paint.valueOf("#7389ae"));
        r4c1.setFill(Paint.valueOf("#7389ae"));
        r5c1.setFill(Paint.valueOf("#7389ae"));
        
        r0c2.setFill(Paint.valueOf("#7389ae"));
        r1c2.setFill(Paint.valueOf("#7389ae"));
        r2c2.setFill(Paint.valueOf("#7389ae"));
        r3c2.setFill(Paint.valueOf("#7389ae"));
        r4c2.setFill(Paint.valueOf("#7389ae"));
        r5c2.setFill(Paint.valueOf("#7389ae"));
        
        r0c3.setFill(Paint.valueOf("#7389ae"));
        r1c3.setFill(Paint.valueOf("#7389ae"));
        r2c3.setFill(Paint.valueOf("#7389ae"));
        r3c3.setFill(Paint.valueOf("#7389ae"));
        r4c3.setFill(Paint.valueOf("#7389ae"));
        r5c3.setFill(Paint.valueOf("#7389ae"));
        
        r0c4.setFill(Paint.valueOf("#7389ae"));
        r1c4.setFill(Paint.valueOf("#7389ae"));
        r2c4.setFill(Paint.valueOf("#7389ae"));
        r3c4.setFill(Paint.valueOf("#7389ae"));
        r4c4.setFill(Paint.valueOf("#7389ae"));
        r5c4.setFill(Paint.valueOf("#7389ae"));
        
        r0c5.setFill(Paint.valueOf("#7389ae"));
        r1c5.setFill(Paint.valueOf("#7389ae"));
        r2c5.setFill(Paint.valueOf("#7389ae"));
        r3c5.setFill(Paint.valueOf("#7389ae"));
        r4c5.setFill(Paint.valueOf("#7389ae"));
        r5c5.setFill(Paint.valueOf("#7389ae"));
        
        r0c6.setFill(Paint.valueOf("#7389ae"));
        r1c6.setFill(Paint.valueOf("#7389ae"));
        r2c6.setFill(Paint.valueOf("#7389ae"));
        r3c6.setFill(Paint.valueOf("#7389ae"));
        r4c6.setFill(Paint.valueOf("#7389ae"));
        r5c6.setFill(Paint.valueOf("#7389ae"));
        
        col0.setDisable(false);
        col1.setDisable(false);
        col2.setDisable(false);
        col3.setDisable(false);
        col4.setDisable(false);
        col5.setDisable(false);
        col6.setDisable(false);
            
        oneV1Button.setDisable(false);
        randomAIButton.setDisable(false);
        smartAIButton.setDisable(false);
        
        updateTurnCircle(!mainApp.game.getRedsTurn());
        
        winnerCircle.setFill(Paint.valueOf("#7389ae"));
        winText.setVisible(false);
    }
    @FXML private void handleSave() {
        mainApp.game.saveGame(save.getText());
    }
    @FXML private void handleLoadSave() {
        mainApp.game.loadGame(loadSave.getText());
        mainApp.game.printBoard();
        //updateTurnCircle(mainApp.game.getRedsTurn());
        
        ArrayList<ArrayList<Circle>> circles = new ArrayList<>();
        ArrayList<Circle> temp = new ArrayList<Circle>();
        temp.add(r0c0);
        temp.add(r0c1);
        temp.add(r0c2);
        temp.add(r0c3);
        temp.add(r0c4);
        temp.add(r0c5);
        temp.add(r0c6);
        circles.add(temp);
        temp = new ArrayList<Circle>();
        temp.add(r1c0);
        temp.add(r1c1);
        temp.add(r1c2);
        temp.add(r1c3);
        temp.add(r1c4);
        temp.add(r1c5);
        temp.add(r1c6);
        circles.add(temp);
        temp = new ArrayList<Circle>();
        temp.add(r2c0);
        temp.add(r2c1);
        temp.add(r2c2);
        temp.add(r2c3);
        temp.add(r2c4);
        temp.add(r2c5);
        temp.add(r2c6);
        circles.add(temp);
        temp = new ArrayList<Circle>();
        temp.add(r3c0);
        temp.add(r3c1);
        temp.add(r3c2);
        temp.add(r3c3);
        temp.add(r3c4);
        temp.add(r3c5);
        temp.add(r3c6);
        circles.add(temp);
        temp = new ArrayList<Circle>();
        temp.add(r4c0);
        temp.add(r4c1);
        temp.add(r4c2);
        temp.add(r4c3);
        temp.add(r4c4);
        temp.add(r4c5);
        temp.add(r4c6);
        circles.add(temp);
        temp = new ArrayList<Circle>();
        temp.add(r5c0);
        temp.add(r5c1);
        temp.add(r5c2);
        temp.add(r5c3);
        temp.add(r5c4);
        temp.add(r5c5);
        temp.add(r5c6);
        circles.add(temp);
        
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (mainApp.game.get(row, col) == 0) {
                    circles.get(row).get(col).setFill(Paint.valueOf("#7389ae"));
                }
                else if (mainApp.game.get(row, col) == 1) {
                    circles.get(row).get(col).setFill(Color.RED);
                }
                else {
                    circles.get(row).get(col).setFill(Color.YELLOW);
                }
            }
        }
        
        updateTurnCircle(!mainApp.game.getRedsTurn());
    }
    
    private void setFillCircle(boolean redsTurn, int row, int col) {
        if (row == -1) {
            System.out.println("You can't go there");
        }
        if (col == 0) {
            if (row == 0)
                if (redsTurn)
                    r0c0.setFill(Color.RED);
                else
                    r0c0.setFill(Color.YELLOW);
            if (row == 1)
                if (redsTurn)
                    r1c0.setFill(Color.RED);
                else
                    r1c0.setFill(Color.YELLOW);
            if (row == 2)
                if (redsTurn)
                    r2c0.setFill(Color.RED);
                else
                    r2c0.setFill(Color.YELLOW);
            if (row == 3)
                if (redsTurn)
                    r3c0.setFill(Color.RED);
                else
                    r3c0.setFill(Color.YELLOW);
            if (row == 4)
                if (redsTurn)
                    r4c0.setFill(Color.RED);
                else
                    r4c0.setFill(Color.YELLOW);
            if (row == 5)
                if (redsTurn)
                    r5c0.setFill(Color.RED);
                else
                    r5c0.setFill(Color.YELLOW);
        }
        if (col == 1) {
            if (row == 0)
                if (redsTurn)
                    r0c1.setFill(Color.RED);
                else
                    r0c1.setFill(Color.YELLOW);
            if (row == 1)
                if (redsTurn)
                    r1c1.setFill(Color.RED);
                else
                    r1c1.setFill(Color.YELLOW);
            if (row == 2)
                if (redsTurn)
                    r2c1.setFill(Color.RED);
                else
                    r2c1.setFill(Color.YELLOW);
            if (row == 3)
                if (redsTurn)
                    r3c1.setFill(Color.RED);
                else
                    r3c1.setFill(Color.YELLOW);
            if (row == 4)
                if (redsTurn)
                    r4c1.setFill(Color.RED);
                else
                    r4c1.setFill(Color.YELLOW);
            if (row == 5)
                if (redsTurn)
                    r5c1.setFill(Color.RED);
                else
                    r5c1.setFill(Color.YELLOW);
        }
        if (col == 2) {
            if (row == 0)
                if (redsTurn)
                    r0c2.setFill(Color.RED);
                else
                    r0c2.setFill(Color.YELLOW);
            if (row == 1)
                if (redsTurn)
                    r1c2.setFill(Color.RED);
                else
                    r1c2.setFill(Color.YELLOW);
            if (row == 2)
                if (redsTurn)
                    r2c2.setFill(Color.RED);
                else
                    r2c2.setFill(Color.YELLOW);
            if (row == 3)
                if (redsTurn)
                    r3c2.setFill(Color.RED);
                else
                    r3c2.setFill(Color.YELLOW);
            if (row == 4)
                if (redsTurn)
                    r4c2.setFill(Color.RED);
                else
                    r4c2.setFill(Color.YELLOW);
            if (row == 5)
                if (redsTurn)
                    r5c2.setFill(Color.RED);
                else
                    r5c2.setFill(Color.YELLOW);
        }
        if (col == 3) {
            if (row == 0)
                if (redsTurn)
                    r0c3.setFill(Color.RED);
                else
                    r0c3.setFill(Color.YELLOW);
            if (row == 1)
                if (redsTurn)
                    r1c3.setFill(Color.RED);
                else
                    r1c3.setFill(Color.YELLOW);
            if (row == 2)
                if (redsTurn)
                    r2c3.setFill(Color.RED);
                else
                    r2c3.setFill(Color.YELLOW);
            if (row == 3)
                if (redsTurn)
                    r3c3.setFill(Color.RED);
                else
                    r3c3.setFill(Color.YELLOW);
            if (row == 4)
                if (redsTurn)
                    r4c3.setFill(Color.RED);
                else
                    r4c3.setFill(Color.YELLOW);
            if (row == 5)
                if (redsTurn)
                    r5c3.setFill(Color.RED);
                else
                    r5c3.setFill(Color.YELLOW);
        }
        if (col == 4) {
            if (row == 0)
                if (redsTurn)
                    r0c4.setFill(Color.RED);
                else
                    r0c4.setFill(Color.YELLOW);
            if (row == 1)
                if (redsTurn)
                    r1c4.setFill(Color.RED);
                else
                    r1c4.setFill(Color.YELLOW);
            if (row == 2)
                if (redsTurn)
                    r2c4.setFill(Color.RED);
                else
                    r2c4.setFill(Color.YELLOW);
            if (row == 3)
                if (redsTurn)
                    r3c4.setFill(Color.RED);
                else
                    r3c4.setFill(Color.YELLOW);
            if (row == 4)
                if (redsTurn)
                    r4c4.setFill(Color.RED);
                else
                    r4c4.setFill(Color.YELLOW);
            if (row == 5)
                if (redsTurn)
                    r5c4.setFill(Color.RED);
                else
                    r5c4.setFill(Color.YELLOW);
        }
        if (col == 5) {
            if (row == 0)
                if (redsTurn)
                    r0c5.setFill(Color.RED);
                else
                    r0c5.setFill(Color.YELLOW);
            if (row == 1)
                if (redsTurn)
                    r1c5.setFill(Color.RED);
                else
                    r1c5.setFill(Color.YELLOW);
            if (row == 2)
                if (redsTurn)
                    r2c5.setFill(Color.RED);
                else
                    r2c5.setFill(Color.YELLOW);
            if (row == 3)
                if (redsTurn)
                    r3c5.setFill(Color.RED);
                else
                    r3c5.setFill(Color.YELLOW);
            if (row == 4)
                if (redsTurn)
                    r4c5.setFill(Color.RED);
                else
                    r4c5.setFill(Color.YELLOW);
            if (row == 5)
                if (redsTurn)
                    r5c5.setFill(Color.RED);
                else
                    r5c5.setFill(Color.YELLOW);
        }
        if (col == 6) {
            if (row == 0)
                if (redsTurn)
                    r0c6.setFill(Color.RED);
                else
                    r0c6.setFill(Color.YELLOW);
            if (row == 1)
                if (redsTurn)
                    r1c6.setFill(Color.RED);
                else
                    r1c6.setFill(Color.YELLOW);
            if (row == 2)
                if (redsTurn)
                    r2c6.setFill(Color.RED);
                else
                    r2c6.setFill(Color.YELLOW);
            if (row == 3)
                if (redsTurn)
                    r3c6.setFill(Color.RED);
                else
                    r3c6.setFill(Color.YELLOW);
            if (row == 4)
                if (redsTurn)
                    r4c6.setFill(Color.RED);
                else
                    r4c6.setFill(Color.YELLOW);
            if (row == 5)
                if (redsTurn)
                    r5c6.setFill(Color.RED);
                else
                    r5c6.setFill(Color.YELLOW);
        }
    }
}