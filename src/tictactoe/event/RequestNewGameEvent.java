package tictactoe.event;

public class RequestNewGameEvent implements TicTacToeEvent{
    protected boolean computer;

    public void setComputer(boolean computer) {this.computer = computer;}

    public boolean getComputer() {return computer;}
}
