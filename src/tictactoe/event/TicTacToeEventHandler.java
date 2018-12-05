package tictactoe.event;

public interface TicTacToeEventHandler<T extends TicTacToeEvent> {

    public void handleEvent(T event);
}
