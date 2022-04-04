import java.util.ArrayList;
/** represents player, who has a baord.
 * */
public class Player {
    /** board object
     * */
    private Board board;
    /** initializes the board
     * */
    public Player(){
        board = new Board();
    }
    /** returns player board
     *
     * @return the board.
     * */
    public Board getBoard(){
        return board;
    }

}
