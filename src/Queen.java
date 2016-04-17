import static java.lang.System.out;

public class Queen extends ChessPiece 
{
  //Queen constructor
  Queen(char color, int rownum)
  {
    super('Q', color, rownum, 3);
  }
  
  public int getNumberOfMoves(final ChessBoard board, final boolean printMoves)
  {
    int count = 0;
    
    //Queen has the least restrictive motion, check for all kinds, update count.
    count += getHorizontalMoves(board, this, this.col, 0, 7, this.row);
    count += getVerticalMoves(board, this, this.row, 7, 0, this.col);
    count += getDiagonalMoves(board, this, this.row, this.col);
   
    //Print additional information if specified.
    if (printMoves)
    {
      String output = board.printBasics(this);
      output += board.s;
      output += "  Total Possible Moves: " + count;
      out.println(output);
    }
    board.s = "";
    
    return count;
  }
}
