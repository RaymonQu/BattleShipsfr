import java.util.Scanner;

public class BattleShipGame {
    private Player player;
    private Scanner scan;
    private boolean gameOver;

    public BattleShipGame(){
        player = new Player();
        scan = new Scanner(System.in);
        gameOver = false;
    }

    //make ai, make game
    public void start(){
        System.out.println("             BATTLE SHIP\n\n\n\n        Press Enter to Start");
        scan.nextLine();
        System.out.println("This is your board. Where would you like to place your 2 tile long destroyer?");
        player.getBoard().createBoard();
        player.getBoard().printBoard();
        Scan
    }


    public void shoot(int row, int column){
        player.getBoard().setMissOrHit(row, column);
    }

    public void updateBoard(){
        player.getBoard().printBoard();
    }

}
