import static java.lang.System.out;

public class Pawn extends ChessPiece 
{
  //Pawn constructor
  Pawn(char color, int rownum, int colnum)
  {
    super('P', color, rownum, colnum);
  }
  
  public int getNumberOfMoves(final ChessBoard board, final boolean printMoves)
  {
    int count = 0;
    
    //See "getPawnMoves" method below
    count += getPawnMoves(board, this);
    
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

  //This method is more complex since the moves of the pawn are very dependent on the
  //state of the squares around it. See inside the method for specific details.
  public int getPawnMoves(ChessBoard board, ChessPiece piece)
  {
    int count = 0, startrow, forward, forward2;
    
    //Set the startrow int variable to the appropriate number depending on the color,
    //I use this to check if the given pawn is home or not. Also, set the forward
    //and forward2 variables accordingly.
    if (piece.color == 'b')
    {
      startrow = 6;
      forward = piece.row-1;
      forward2 = piece.row-2;
    }
    else
    {
      startrow = 1;
      forward = piece.row+1;
      forward2 = piece.row+2;
    }
    
    //Vertical moves are only valid if they are empty, count++ if is empty.
    if (board.checkValidEmpty(piece, forward, piece.col))
    {
      count++;
      
      //If the piece is on it's starting location, we also have to check if two spaces
      //in front of the pawn is empty since the first space in front of it was empty.
      if (piece.row == startrow && board.checkValidEmpty(piece, forward2, piece.col))
      {
        count++;
        board.printAdditional(forward2, piece.col);
      }
    }
    
    //Check if the diagonal spaces have pieces of the opposite color on them.
    if (board.checkValidCapture(piece, forward, piece.col-1))
      count++;
    if (board.checkValidCapture(piece, forward, piece.col+1))
      count++;
       
    return count;
  }
}
