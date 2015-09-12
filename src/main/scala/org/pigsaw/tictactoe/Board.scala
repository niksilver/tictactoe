package org.pigsaw.tictactoe

class Board() {
  self =>

  type Coord = (Int, Int)

  val empty = ' '

  def apply(row: Int, col: Int): Char = {
    if (col < 0 || col > 2)
      throw new ArrayIndexOutOfBoundsException(s"Column $col out of bounds")
    if (row < 0 || row > 2)
      throw new ArrayIndexOutOfBoundsException(s"Row $row out of bounds")
    empty
  }

  def apply(c: Coord): Char = apply(c._1, c._2)

  def takeTurn(c: Coord, token: Char): Board = {
    val (row, col) = c
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

  def takeTurn(row: Int, col: Int, token: Char): Board = takeTurn((row, col), token)

  def isToken(c: Char) = { c != empty }

  def isFilled(row: Int, col: Int): Boolean =
    isToken(this(row, col))

  def isFilled(c: Coord): Boolean = isFilled(c._1, c._2)

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

  def isFull =
    Board.coords.forall( c => isFilled(c) )

  def isEmpty =
    Board.coords.forall( c => !isFilled(c) )

  def tokenCount: Int =
    Board.coords.map{ c => if (isFilled(c)) 1 else 0 }.sum

  def tokenCount(token: Char): Int =
    Board.coords.map{ c => if (apply(c) == 'X') 1 else 0 }.sum
}

object Board {

  def coords = for {
    row <- 0 to 2
    col <- 0 to 2
  } yield (row, col)

}
