package org.pigsaw.tictactoe

class Board() {
  self =>

  def apply(row: Int, col: Int): Char = {
    if (col < 0 || col > 2)
      throw new ArrayIndexOutOfBoundsException(s"Column $col out of bounds")
    if (row < 0 || row > 2)
      throw new ArrayIndexOutOfBoundsException(s"Row $row out of bounds")
    ' '
  }

  def takeTurn(row: Int, col: Int, token: Char): Board = {
    new Board() {
      override def apply(r: Int, c: Int): Char = {
        if (r == row && c == col)
          token
        else
          self(r, c)
      }
    }
  }

  def isFilled(row: Int, col: Int): Boolean = {
    (apply(row, col) != ' ')
  }

  private def hasRowWinner(row: Int) =
    isFilled(row,0) && apply(row,0) == apply(row,1) && apply(row,1) == apply(row,2)

  private def hasColWinner(col: Int) =
    isFilled(0,col) && apply(0,col) == apply(1,col) && apply(1,col) == apply(2,col)

  private def hasDiagWinner =
    isFilled(0,0) && apply(0,0) == apply(1,1) && apply(1,1) == apply(2,2)

  def winner: Option[Char] = {
    val rowToken = (0 to 2).find(hasRowWinner).map(row => apply(row,0))
    val colToken = (0 to 2).find(hasColWinner).map(col => apply(0,col))
    val diagToken = (if (hasDiagWinner) Some(apply(0,0)) else None)
    rowToken orElse colToken orElse diagToken
  }
}