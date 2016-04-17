import static java.lang.System.out;

public class King extends ChessPiece 
{
  //King Constructor
  King(char color, int rownum)
  {
    super('K', color, rownum, 4);
  }
  
  public int getNumberOfMoves(final ChessBoard board, final boolean printMoves)
  {
    int count = 0;
    
    //Call the parent class' methods and update count accordingly.
    //getHorizontalMoves args:  board, piece,startvar, leftbound, rightbound, currow
    count += getHorizontalMoves(board, this, this.col, this.col-1, this.col+1, this.row);
    //getVerticalMoves args:  board, piece,startvar, topbound,  bottombound, currcol
    count += getVerticalMoves(board, this, this.row, this.row+1, this.row-1, this.col);
   
    //See "getKingDiagMoves" below
    count += getKingDiagMoves(board, this, this.row, this.col);
   
    //Print the additional information if specified.
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

  //The king diagonal moves warrant their own function because it would be troublesome
  //to put in all the diagonal bounds in the original getDiagonalMoves method. This
  //is very similar to the said method, but just simplified for the king.
  public int getKingDiagMoves(final ChessBoard board, ChessPiece piece,
                              final int startrow, final int startcol
                              )
  {
    int count = 0;
    
    if (board.checkValidMove(piece, piece.row-1, piece.col-1))
      count++;
    if (board.checkValidMove(piece, piece.row-1, piece.col+1))
      count++;
    if (board.checkValidMove(piece, piece.row+1, piece.col-1))
      count++;
    if (board.checkValidMove(piece, piece.row+1, piece.col+1))
      count++;
    
    return count;
  }
}
