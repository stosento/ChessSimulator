import static java.lang.System.out;

public class Rook extends ChessPiece 
{
  //Rook constructor
  Rook(char color, int rownum, int colnum)
  {
    super('R', color, rownum, colnum);
  }
  
  public int getNumberOfMoves(final ChessBoard board, final boolean printMoves)
  {
    int count = 0;
    
    //Rook can travel either horizontal or vertical, update count while checking this.
    count += getHorizontalMoves(board, this, this.col, 0, 7, this.row);
    count += getVerticalMoves(board, this, this.row, 7, 0, this.col);
   
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
