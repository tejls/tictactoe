package tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tictactoe.event.GameWonEvent;

public class StatsController {

    int crossWins = 0;
    int circleWins = 0;

    protected ApplicationController mainController;

    public void setMainController(ApplicationController mainController){
        this.mainController = mainController;
        mainController.registerHandler(GameWonEvent.class, event -> updateWins(event.getWhoWon()));
    }

    @FXML
    protected Label cross;
    @FXML
    protected Label circle;

    private void updateWins(Player winner){
        if(winner == Player.CROSS){
            crossWins ++;
        } else {
            circleWins ++;
        }
        updateLabels();
    }

    private void updateLabels() {
        cross.setText("X: " + crossWins);
        circle.setText("O: "+ circleWins);
    }
}
