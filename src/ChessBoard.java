import static java.lang.System.out;

public class ChessBoard
{
  //Declare variables
  ChessPiece [][] board;
  char [] letters = {'a','b','c','d','e','f','g','h'};
  String s = "";
  
  //Basic Constructor for the board
  ChessBoard()
  {
    //Construct the board
    board = new ChessPiece[8][8];
  }
   
  //Purpose: Instantiates each of the chess pieces and places them on the board.
  //Arguments: none
  void initialize()
  {
    //Initialize Kings, defaults: 'K',col=4
    ChessPiece blackKing = new King('b',7);
    ChessPiece whiteKing = new King('w',0);
   
    //Place the Kings on the board
    board[blackKing.row][blackKing.col] = blackKing;
    board[whiteKing.row][whiteKing.col] = whiteKing;
    
    //Initialize Queens, defaults: 'Q',col=3
    ChessPiece blackQueen = new Queen('b',7);
    ChessPiece whiteQueen = new Queen('w',0);
    
    //Place the Queens on the board
    board[blackQueen.row][blackQueen.col] = blackQueen;
    board[whiteQueen.row][whiteQueen.col] = whiteQueen;
    
    //Initialize Bishops, default: 'B'
    ChessPiece blackBishop1 = new Bishop('b',7,2);
    ChessPiece blackBishop2 = new Bishop('b',7,5);
    ChessPiece whiteBishop1 = new Bishop('w',0,2);
    ChessPiece whiteBishop2 = new Bishop('w',0,5);
   
    //Place the Bishops on the board
    board[blackBishop1.row][blackBishop1.col] = blackBishop1;
    board[blackBishop2.row][blackBishop2.col] = blackBishop2;
    board[whiteBishop1.row][whiteBishop1.col] = whiteBishop1;
    board[whiteBishop2.row][whiteBishop2.col] = whiteBishop2;
    
    //Initialize Knights, default: 'N'
    ChessPiece blackKnight1 = new Knight('b',7,1);
    ChessPiece blackKnight2 = new Knight('b',7,6);
    ChessPiece whiteKnight1 = new Knight('w',0,1);
    ChessPiece whiteKnight2 = new Knight('w',0,6);
    
    //Place the Knights on the board
    board[blackKnight1.row][blackKnight1.col] = blackKnight1;
    board[blackKnight2.row][blackKnight2.col] = blackKnight2;
    board[whiteKnight1.row][whiteKnight1.col] = whiteKnight1;
    board[whiteKnight2.row][whiteKnight2.col] = whiteKnight2;
    
    //Initialize Rooks, default: 'R'
    ChessPiece blackRook1 = new Rook('b',7,0);
    ChessPiece blackRook2 = new Rook('b',7,7);
    ChessPiece whiteRook1 = new Rook('w',0,0);
    ChessPiece whiteRook2 = new Rook('w',0,7);
    
    //Place the Rooks on the Board
    board[blackRook1.row][blackRook1.col] = blackRook1;
    board[blackRook2.row][blackRook2.col] = blackRook2;
    board[whiteRook1.row][whiteRook1.col] = whiteRook1;
    board[whiteRook2.row][whiteRook2.col] = whiteRook2;
    
    //Initialize Pawns, default: 'P'
    ChessPiece [] blackPawn = new Pawn[8];
    ChessPiece [] whitePawn = new Pawn[8];
    for (int i=0; i<=7; i++)
    {
      blackPawn[i] = new Pawn('b',6,i);
      whitePawn[i] = new Pawn('w',1,i);
    }
    
    //Place the Pawns on the board
    for (int i=0; i<=7; i++)
    {
      board[blackPawn[i].row][blackPawn[i].col] = blackPawn[i];
      board[whitePawn[i].row][whitePawn[i].col] = whitePawn[i];
    }
    
    //Set the appropriate squares to occupied
    for (int i=0; i<=7; i++)
    {
      for (int j=0; j<=7; j++)
      {
        if (i<2 && i>5)
          board[i][j].occupied = true;
      }
    }
    
  }
  
  //Purpose: Returns true if the coordinate given to it is valid (in bounds).
  //Arguments: row, col
  public boolean isInbounds(final int rowNum, final int colNum)
  {
    if (rowNum < 0 || rowNum > 7 || colNum < 0 || colNum > 7)
      return false;

    return true;
  }

  private boolean isOccupied(final int destrow, final int destcol)
  {
    return board[destrow][destcol] == null ? false : true;
  }

  private boolean isDifferentColor(ChessPiece basepiece, final int destrow, final int destcol)
  {
    return board[destrow][destcol].color == basepiece.color ? false : true;
  }
  
  //Purpose: Returns true if the given location is a valid move for the piece
  //         given in the arguments.
  //Arguments: piece, row destination, col destination
  public boolean checkValidMove(ChessPiece basepiece, final int destrow, final int destcol)
  {
    if (isInbounds(destrow, destcol) && (!isOccupied(destrow, destcol) || isDifferentColor(basepiece, destrow, destcol)))
    {
      s += printAdditional(destrow, destcol);
      return true;
    }
    return false;
  }
  
  //Purpose: Returns true if the given board location is empty.
  //Arugments: piece, row destination, col destination
  public boolean checkValidEmpty(ChessPiece basepiece, final int destrow, final int destcol)
  { 
    if (isInbounds(destrow, destcol) && !isOccupied(destrow, destcol))
    {
      s += printAdditional(destrow, destcol);
      return true; 
    }
    
    else
      return false;
  }

  //Purpose: Returns true if the given location holds a piece of the opposite color.
  //Arguments: piece, row destination, col destination
  public boolean checkValidCapture(ChessPiece basepiece, int destrow, int destcol)
{
  if (isInbounds(destrow, destcol) && isOccupied(destrow, destcol) && isDifferentColor(basepiece, destrow, destcol))
  {
    s += printAdditional(destrow, destcol);
    return true; 
  }

  else
    return false;
  }
  
  //Purpose: Returns a reference to the piece at the specified row and column, or returns
  //         null if the specified space does not contain a piece. Also prints an error 
  //         message if the given coordinate is not valid, returns null.
  //Arguments: row, col
  ChessPiece getPieceAt(final int row, final int col)
  {
    if (isInbounds(row, col))
      return board[row][col];
    else
    {
      printInvalidCoord(row, col);
      return null;
    }
  }
  
  //Purpose: Removes the piece from specified row and col. Has no effect if the space
  //         is empty. Prints error message if the space is not valid.
  //Arguments: row, col
  public void removePiece(final int rowNum, final int colNum)
  {
    if (isInbounds(rowNum, colNum))
    {
      board[rowNum][colNum] = null;
    }
    else
    {
      printInvalidCoord(rowNum, colNum);
      return;
    }
  }
  
  //Purpose: Places the piece specified as the first parameter in the space indicated
  //         via the rowNum and colNum parameters. If the space already contains a piece,
  //         it is replaced with the piece passed in, and is not considered an error.
  //         Prints error message if the space is invalid.
  //Arguments: pieceToPlace, rowNum, colNum
  public void placePiece(final ChessPiece pieceToPlace, final int rowNum, final int colNum)
  {
    if (isInbounds(rowNum, colNum))
    {
      board[pieceToPlace.row][pieceToPlace.col] = null;
      
      board[rowNum][colNum] = pieceToPlace;
      board[rowNum][colNum].occupied = true;
      pieceToPlace.row = rowNum;
      pieceToPlace.col = colNum;
    }
    else
    {
      printInvalidCoord(rowNum, colNum);
      return;
    }
  }

  //Purpose: Prints out the current state of the chess board.
  //Arugments: none
  void print()
  {
    String outBoard = "";
    String line = "  |--|--|--|--|--|--|--|--| \n";
    
    for (int i=board.length-1; i>=0; i--)
    {
      outBoard += (i+1) + " ";

      for (int j=0; j<board[i].length; j++)
      {
        if (board[i][j] != null)
          outBoard += "|" + board[i][j].color + board[i][j].kind;
        else
          outBoard += "|" + "  ";
      }
      outBoard += "|";
      out.println(line + outBoard);
      outBoard = "";
    }
    
    outBoard = " ";
    for (int i=0; i<8; i++)
    {
      outBoard += "  " + letters[i];
    }
    out.println(line + outBoard);
  }  

  //Purpose: Prints either if the space given is vacant or occupied, is only called 
  //         if printMoves bool in "getNumberOfMoves" is called, and this method is
  //         what is responsible for it.
  //Arguments: destrow, destcol
  public String printAdditional(int destrow, int destcol)
  {
    boolean empty = false;
    
    if (board[destrow][destcol] == null)
      empty = true;
  
    String s = "";
    
    if (empty)
    {
      s += "   Vacant: " + letters[destcol] + (destrow+1) + " \n";
    }
    else
    {
      s += "  Take " + board[destrow][destcol].color + board[destrow][destcol].kind;
      s += ": " + letters[destcol] + (destrow+1) + "\n";
    }
    
    return s;
  }
  
  //Purpose: Prints an error message indicating the row and col given are invalid.
  //Arguments: rowNum, colNum
  public void printInvalidCoord(final int rowNum, final int colNum)
  {
    out.println("The given coordinates: " + rowNum + " " + colNum + " are invalid.");
  }

  //Purpose: Goes along with the printAdditional method, this prints the color, kind,
  //         and location of the given piece before printing the valid moves.
  //Arguments: piece
  public String printBasics(ChessPiece piece)
  {
    String s = "";
    s += "" + piece.color + piece.kind + " at " + letters[piece.col] + (piece.row+1);
    s += " valid moves: \n";
    
    return s;
  }
}