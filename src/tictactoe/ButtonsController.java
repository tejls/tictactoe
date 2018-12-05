package tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tictactoe.event.RequestNewGameEvent;

public class ButtonsController {

    public ApplicationController mainController;

    public void setMainController(ApplicationController mainController){
        this.mainController = mainController;
    }

    @FXML
    protected Button computer;
    @FXML
    protected Button player;

    public void computerPressed(){
        RequestNewGameEvent comp = new RequestNewGameEvent();
        comp.setComputer(true);
        mainController.handleEvent(comp);
    }

    public void playerPressed(){
        RequestNewGameEvent player = new RequestNewGameEvent();
        player.setComputer(false);
        mainController.handleEvent(player);
    }
}
