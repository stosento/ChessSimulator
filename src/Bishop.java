import static java.lang.System.out;

public class Bishop extends ChessPiece 
{
  //Constructor for a bishop.
  Bishop(char color, int rownum, int colnum)
  {
    super('B', color, rownum, colnum);
  }
  public int getNumberOfMoves(final ChessBoard board, final boolean printMoves)
  {
    int count = 0;
    
    //Update count with all possible diagonal moves accessible. 
    count += getDiagonalMoves(board, this, this.row, this.col);
   
    //Print additional information if asked.
    if (printMoves)
    {
      String output = board.printBasics(this);                  //Prints
      output += board.s;
      output += "  Total Possible Moves: " + count;
      out.println(output);
    }
    board.s = "";
    
    return count;
  }
}
