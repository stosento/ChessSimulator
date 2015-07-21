public abstract class ChessPiece
{
  //Declare variables
  char color, kind;
  int row, col;
  boolean occupied;
  
  //Constructor
  ChessPiece(char c)
  {
    color = c;
  }
  
  public abstract int getNumberOfMoves(final ChessBoard board, final boolean printMoves);

  //The three functions below are the main functions for getting valid moves for all
  //the functions in the derived classes. In all of them, I start at the given piece
  //location and from there check each direction via the checkValidMove function and
  //move on to the next position if valid.
  
  //Depending on the function, I pass in the starting row or col that will stay constant,
  //the board, piece, as well as the bounds, which allows me to specify the piece's
  //limitations, for example, a queen's horizontal movement vs. a king's horizontal
  //movement.
  
  public int getHorizontalMoves(final ChessBoard board, ChessPiece piece,
                                final int start, 
                                final int leftbound, final int rightbound, 
                                final int row
                                )
  {
    int count = 0;
    
    //Move left
    for (int i = start-1; i >= leftbound; i--)
    {
      if (board.checkValidMove(piece, row, i))
        count++;
      else
        break;   
    }
    
    //Move right
    for (int i = start+1; i <= rightbound; i++)
    {
      if (board.checkValidMove(piece, row, i))
        count++;
      else
        break;
    }  
    return count;
  }
  
  public int getVerticalMoves(final ChessBoard board, ChessPiece piece,
                              final int start, 
                              final int topbound, final int bottombound, 
                              final int col
                              )
  {
    int count = 0;
    
    //Move up
    for (int i = start+1; i <= topbound; i++)
    {
      if (board.checkValidMove(piece, i, col))
        count++;
      else
        break;       
    }
    //Move down
    for (int i = start-1; i >= bottombound; i--)
    {      
      if (board.checkValidMove(piece, i, col))
        count++;
      else
        break;
    }
    return count;
  }
   
  public int getDiagonalMoves(final ChessBoard board, ChessPiece piece,
                              final int startrow, final int startcol
                              )
  {
    int count = 0, i, j;
    
    //Move DOWN and LEFT
    i = startrow-1;
    j = startcol-1;
    while (board.checkValidCoord(i, j))
    {
      if (board.checkValidMove(piece, i, j))
        count++;
      else
        break;
      i--; j--;
    }
    //Move DOWN and RIGHT
    i = startrow-1;
    j = startcol+1;
    while (board.checkValidCoord(i, j))
    {
      if (board.checkValidMove(piece, i, j))
        count++;
      else
        break;
      i--; j++;
    }
    //Move UP and LEFT
    i = startrow+1;
    j = startcol-1;
    while (board.checkValidCoord(i, j))
    {
      if (board.checkValidMove(piece, i, j))
        count++;
      else
        break;
      i++; j--;
    }
    //Move UP and RIGHT
    i = startrow+1;
    j = startcol+1;
    while (board.checkValidCoord(i, j))
    {
      if (board.checkValidMove(piece, i, j))
        count++;
      else
        break;
      i++; j++;
    }
    return count;
  }
}