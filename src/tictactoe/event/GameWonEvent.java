package tictactoe.event;

import tictactoe.Player;

public class GameWonEvent implements TicTacToeEvent {

    protected Player whoWon;

    public GameWonEvent(Player whoWon){
        this.whoWon = whoWon;
    }

    public Player getWhoWon() {return whoWon;}


}
