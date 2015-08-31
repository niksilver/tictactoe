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

  private def allTheSame(row: Int, col: Int, rowInc: Int, colInc: Int): Boolean =
    apply(row, col) == apply(row+rowInc, col+colInc) &&
      apply(row+rowInc, col+colInc) == apply(row+2*rowInc, col+2*colInc)

  private def hasRowWinner(row: Int) =
    isFilled(row,0) && allTheSame(row, 0, 0, 1)

  private def hasColWinner(col: Int) =
    isFilled(0,col) && allTheSame(0, col, 1, 0)

  private def hasDiagWinner(inc: Int) =
    isFilled(0,1-inc) && allTheSame(0, 1-inc, 1, inc)

  def winner: Option[Char] = {
    val rowToken = (0 to 2).find(hasRowWinner).map(row => apply(row,0))
    val colToken = (0 to 2).find(hasColWinner).map(col => apply(0,col))
    val diagToken = Seq(1, -1).find(hasDiagWinner).map(inc => apply(0,1-inc))
    rowToken orElse colToken orElse diagToken
  }
}
