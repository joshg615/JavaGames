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
                //gets the game and checks if there's any null values. if there's no null value returns true
            if (getGame().checkIfGameEnded()){
                didGameEnd = true;
                    break;
            }
            //Prints the board to the screen
            view.printBoard(getGame());
            int columnToInsert = 0;
            try{
                //try's to get a number value input from the current player. -1 to avoid the zero index;
                columnToInsert = view.playTurn(game.getTurnPlayer().getName()) -1;
                //catches the input if it isn't a number
            }catch (NumberFormatException e){
                System.out.println("PLEASE ENTER A NUMBER");
            }
            try{
                //try's to insert the disc in the column number input returns true if the
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
