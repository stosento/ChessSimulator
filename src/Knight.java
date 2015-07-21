import static java.lang.System.out;

public class Knight extends ChessPiece 
{
  //Knight constructor
  Knight(char c, int rownum, int colnum)
  {
    super(c);
    kind = 'N';
    row = rownum;
    col = colnum;
  }
  
  public int getNumberOfMoves(final ChessBoard board, final boolean printMoves)
  {
    int count = 0;
   
    //See "getKnightMoves" method before
    count += getKnightMoves(board, this);
   
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
  
  //A knight can potentially have 8 possible moves, and this method is responsible
  //for checking the validity for all of these possible locations. The knight is very
  //unique from all other pieces, which warrants it's own method.
  public int getKnightMoves(ChessBoard board, ChessPiece piece)
  {
    int count = 0;
    
    if (board.checkValidMove(piece, piece.row-2, piece.col+1))
      count++;
    if (board.checkValidMove(piece, piece.row+2, piece.col+1))
      count++;
    if (board.checkValidMove(piece, piece.row-2, piece.col-1))
      count++;
    if (board.checkValidMove(piece, piece.row+2, piece.col-1))
      count++;
    
    if (board.checkValidMove(piece, piece.row-1, piece.col+2))
      count++;
    if (board.checkValidMove(piece, piece.row+1, piece.col+2)) 
      count++;
    if (board.checkValidMove(piece, piece.row-1, piece.col-2)) 
      count++;
    if (board.checkValidMove(piece, piece.row+1, piece.col-2)) 
      count++;
 
    return count;
  }
}
