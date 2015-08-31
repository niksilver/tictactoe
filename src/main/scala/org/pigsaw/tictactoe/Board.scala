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

  def isToken(c: Char) = { c != ' ' }

  def isFilled(row: Int, col: Int): Boolean =
    isToken(apply(row, col))

  private def allTheSame(row: Int, col: Int, rowInc: Int, colInc: Int): Boolean =
    apply(row, col) == apply(row+rowInc, col+colInc) &&
      apply(row+rowInc, col+colInc) == apply(row+2*rowInc, col+2*colInc)

  def winner: Option[Char] = {
    val lines = Seq( (0,0, 0,1), (1,0, 0,1), (2,0, 0,1), // rows
      (0,0, 1,0), (0,1, 1,0), (0,2, 1,0), // columns
      (0,0, 1,1), (0,2, 1,-1)) // diagonals
    val winner = lines.find(i => isFilled(i._1, i._2) && allTheSame(i._1, i._2, i._3, i._4))
    winner map (i => apply(i._1, i._2))
  }
}
