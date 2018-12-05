package tictactoe;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tictactoe.event.GameWonEvent;
import tictactoe.event.RequestNewGameEvent;

public class BoardController {

    protected ApplicationController mainController;

    public void setMainController(ApplicationController mainController){
        this.mainController = mainController;
        mainController.registerHandler(RequestNewGameEvent.class, event -> restartGame(event.getComputer()));
    }

    @FXML
    protected GridPane grid;

    protected boolean gameEnded;
    protected Board board;
    protected boolean computer;

    public void initialize() {
        for (Node child : grid.getChildren()) {
            Integer row = Optional.ofNullable(GridPane.getRowIndex(child)).orElse(0);
            Integer column = Optional.ofNullable(GridPane.getColumnIndex(child)).orElse(0);
            child.setOnMouseClicked(event -> handleMove(row, column));
        }
        restartGame(true);
    }


    private void handleMove(Integer row, Integer column) {
        if (!gameEnded) {
                if (board.getPlayerForField(row, column) == null) {
                    board.makeMove(row, column);
                    if(!checkVictoryAndRegister() & computer){
                        board.computerPlayerMove();
                    }
                }
            drawBoard();
            }
            if(!gameEnded){
                checkVictoryAndRegister();
            }
        }
    private boolean checkVictoryAndRegister(){
        Player winner = board.checkVictory();
        if (winner != null){
            gameEnded = true;
            showVictoryMessage(winner);
            mainController.handleEvent(new GameWonEvent(winner));
            return true;
        }
        return false;
    }
    private void drawBoard() {
        for (Node child : grid.getChildren()) {
            Integer row = Optional.ofNullable(GridPane.getRowIndex(child)).orElse(0);
            Integer column = Optional.ofNullable(GridPane.getColumnIndex(child)).orElse(0);
            Pane pane = (Pane) child;
            pane.getChildren().clear();
            Player inCell = board.getPlayerForField(row, column);
            if (inCell != null) {
                Label label = new Label(inCell.getSign());
                label.setPrefWidth(30.0);
                label.setPrefHeight(30.0);
                label.setAlignment(Pos.CENTER);
                pane.getChildren().add(label);
            }
        }
    }

    private void showVictoryMessage(Player wins) {
        Alert alert = new Alert(AlertType.WARNING, "Player " + wins + " wins!", ButtonType.OK);
        alert.showAndWait();
    }

    private void restartGame(boolean computer){
        gameEnded = false;
        this.computer = computer;
        board = new Board();
        drawBoard();

    }

}
