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

  private def rowWinner(row: Int) =
    isFilled(row,0) && apply(row,0) == apply(row,1) && apply(row,1) == apply(row,2)

  def winner: Option[Char] = {
    if (rowWinner(0))
      Some(apply(0, 0))
    else if (rowWinner(1))
      Some(apply(1, 0))
    else if (rowWinner(2))
      Some(apply(2, 0))
    else
      None
  }
}
