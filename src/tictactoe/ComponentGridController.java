package tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;


public class ComponentGridController {

    @FXML
    protected GridPane grid;

    protected ApplicationController mainController;

    public ApplicationController getMainController() {return mainController;}

    public void initialize(){
        try{
            mainController = new ApplicationController();
            FXMLLoader ticTacToeLoader = new FXMLLoader(getClass().getResource("tictactoe.fxml"));
            Node board = ticTacToeLoader.load();
            BoardController boardController = ticTacToeLoader.getController();
            boardController.setMainController(mainController);
            FXMLLoader buttonsLoader = new FXMLLoader(getClass().getResource("buttons.fxml"));
            Node buttons = buttonsLoader.load();
            ButtonsController buttonsController = buttonsLoader.getController();
            buttonsController.setMainController(mainController);
            FXMLLoader statsLoader = new FXMLLoader(getClass().getResource("stats.fxml"));
            Node stats = statsLoader.load();
            StatsController statsController = statsLoader.getController();
            statsController.setMainController(mainController);
            grid.add(buttons, 0, 0);
            grid.add(board, 0, 1);
            grid.add(stats, 0 ,2);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
