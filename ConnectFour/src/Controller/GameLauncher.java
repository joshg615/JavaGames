package Controller;

import Execeptions.ColumnIsFullException;
import Execeptions.InvalidColumnException;
import Model.Game;
import View.GameView;

public class GameLauncher {
    GameView view;
    Game game ;

    public GameLauncher(){
        view = new GameView();
        game = view.makeGame();
        boolean didGameEnd = false;
        while(true){
            if (getGame().checkIfGameEnded()){
                didGameEnd = true;
                    break;
            }
            view.printBoard(getGame());
            int columnToInsert = -1;
            try{
                columnToInsert = view.playTurn(game.getTurnPlayer().getName());
            }catch (NumberFormatException e){
                System.out.println("PLEASE ENTER A NUMBER");
            }
            try{
                if (getGame().insertDisc(columnToInsert)){
                    break;
                }
                getGame().endTurn();
            }catch (ColumnIsFullException e){
                System.out.println("THIS COLUMN IS FULL");
            }catch (InvalidColumnException e){
                System.out.println("PLEASE SPECIFY A CORRECT COLUMN TO INSERT IN");
            }
        }
        if(didGameEnd){
            System.out.println("GAME ENDED, THERE IS NO WINNER");
        }else{
            System.out.println("***************************************");
            System.out.println("***************************************");
            System.out.println(getGame().getTurnPlayer().getName() + " HAS WON!!!!!!!!!!!!!!!!!");
            System.out.println("***************************************");
            System.out.println("***************************************");
        }
    }

    public static void main(String[] args) {
        new GameLauncher();
    }

    public Game getGame() {
        return game;
    }
}
