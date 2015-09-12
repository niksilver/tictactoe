package org.pigsaw.tictactoe

class Board() {
  self =>

  val empty = ' '

  def apply(row: Int, col: Int): Char = {
    if (col < 0 || col > 2)
      throw new ArrayIndexOutOfBoundsException(s"Column $col out of bounds")
    if (row < 0 || row > 2)
      throw new ArrayIndexOutOfBoundsException(s"Row $row out of bounds")
    empty
  }

  def takeTurn(row: Int, col: Int, token: Char): Board = {
    if (this(row, col) != empty) {
      throw new Exception(s"Cell $row, $col is already filled")
    }
    new Board() {
      override def apply(r: Int, c: Int): Char = {
        if (r == row && c == col)
          token
        else
          self(r, c)
      }
    }
  }

  def isToken(c: Char) = { c != empty }

  def isFilled(row: Int, col: Int): Boolean =
    isToken(this(row, col))

  private def allTheSame(row: Int, col: Int, rowInc: Int, colInc: Int): Boolean =
    this(row, col) == this(row+rowInc, col+colInc) &&
      this(row+rowInc, col+colInc) == this(row+2*rowInc, col+2*colInc)

  def winner: Option[Char] = {
    val lines = Seq( (0,0, 0,1), (1,0, 0,1), (2,0, 0,1), // rows
      (0,0, 1,0), (0,1, 1,0), (0,2, 1,0), // columns
      (0,0, 1,1), (0,2, 1,-1)) // diagonals
    val winner = lines.find(i => isFilled(i._1, i._2) && allTheSame(i._1, i._2, i._3, i._4))
    winner map (i => this(i._1, i._2))
  }

  def coords = for {
    row <- 0 to 2
    col <- 0 to 2
  } yield (row, col)

  def isFull =
    coords.forall( c => isFilled(c._1, c._2) )

  def isEmpty =
    coords.forall( c => !isFilled(c._1, c._2) )
}
