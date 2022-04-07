package Model;

import Execeptions.ColumnIsFullException;
import Execeptions.InvalidColumnException;

public class Game {
    Disc[][] board;
    Player player1;
    Player player2;
    final int numberOfRows = 6;
    final int numberOfColumns = 7;
    boolean turn;


    public Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        randomizePlayers();
        board = new Disc[numberOfRows][numberOfColumns];

    }

    public Disc[][] getBoard() {
        return board;
    }
    //Checks the whole board if there's no null value then game has ended
    public boolean checkIfGameEnded(){
        for (int i = 0; i < numberOfColumns; i++  ){
            if (board[0][i] == null) {
                return false;
            }

        }
        return true;
    }
    public void randomizePlayers(){
        double randomNumber = Math.random();
        Player tempPlayer;
        if (randomNumber >= 0 && randomNumber < 0.5){
            tempPlayer = player1;
            player1 = player2;
            player2 = tempPlayer;
        }

    }
    public void endTurn(){
        turn = !turn;

    }
    public Player getTurnPlayer(){
        if (turn){
            return player2;
        }else {
            return player1;
        }

    }

    public boolean checkWin(int rowNumber, int columnNumber, Player turnPlayer){
        int count = 0;
        //checking for four disc in a row to the right
        for(int i = rowNumber; i< numberOfRows; i++){
            if(board[i][columnNumber].getPlayer() == turnPlayer){
                count++;
                if(count == 4){
                    return true;
                }
            }else{
                    break;
                }
        }
        //Left
        for(int i = columnNumber; i >= 0; i--){
            if(board[rowNumber][i] == null){
                break;
            }
            if(board[rowNumber][i].getPlayer() == turnPlayer){
                count++;
                if(count == 4){
                    return true;
                }
            }else {
                break;
            }
        }

        count = 0;
        //Checking for four in a row downwards
        for(int i = columnNumber; i< numberOfColumns; i++){
            if(board[rowNumber][i] == null){
                break;
            }
            if (board[rowNumber][i].getPlayer() == turnPlayer){
                count++;
                if(count == 4){
                    return true;
                }
            } else{
                    break;
                }
        }


        count = 0;
        //Checking for DownRight
        for (int i = rowNumber, j = columnNumber; i < numberOfRows && j < numberOfColumns; i++, j++){
            if (board[i][j] == null){
                break;
            }
            if(board[i][j].getPlayer() == turnPlayer){
                count++;
                if(count == 4){
                    return true;
                }
            }else {
                    break;
                }

        }
        //Checking for Upwards Right
        for(int i = rowNumber, j = columnNumber; i >= 0 && j < numberOfColumns; i--, j++){
            if(board[i][j] == null){
                break;
            }
            if(board[i][j].getPlayer() == turnPlayer){
                count++;
                if(count == 4){
                    return true;
                }
            }else {
                break;
            }
        }
        count = 0;
        //Checking DownLeft
        for(int i = rowNumber, j = columnNumber; i< numberOfRows && j>=0; i++, j--){
            if (board[i][j] == null){
                break;
            }
            if(board[i][j].getPlayer() == turnPlayer){
                count++;
                if(count == 4){
                    return true;
                }
            }else {
                break;
            }

        }

        //Checking for Upwards Left
        for(int i = rowNumber, j = columnNumber; i>= 0 && j >=0; i--, j--){
            if(board[i][j] == null){
                break;
            }
            if(board[i][j].getPlayer() == turnPlayer){
                count++;
                if(count == 4){
                    return true;
                }
            }else{
                break;
            }
        }
        return false;
    }

    public boolean insertDisc(int columnNumber) throws ColumnIsFullException, InvalidColumnException{
        Disc disc;
        //Checking who disc belongs to
        if(getTurnPlayer().getType() == PlayerType.PLAYERONE){
            disc = new PlayerOneDisc(getTurnPlayer());
        }else {
            disc= new PlayerTwoDisc(getTurnPlayer());
        }
        //Checking if player choose a column that doesn't exist
        if(columnNumber>= numberOfColumns || columnNumber < 0){
            throw new InvalidColumnException();
        }
        //Checking if the column is full
        if(board[0][columnNumber]!= null){
            throw new ColumnIsFullException();
        }
        //looping to drop disc
        for(int i = 0; i < numberOfRows - 1; i++){
            //if the first spot has a disk
            if(board[i+1][columnNumber] != null){
                //drop disk on top
                board[i][columnNumber] = disc;
                //check if player won
                return checkWin(i, columnNumber, getTurnPlayer());
            }
        }
        //drop disk in first spot of column
        board[numberOfRows - 1][columnNumber] = disc;
        //check for win
        return checkWin(numberOfRows - 1, columnNumber, getTurnPlayer());

    }


}
