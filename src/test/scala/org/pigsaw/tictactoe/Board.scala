package org.pigsaw.tictactoe

class Board() {
  def apply(row: Int, col: Int): Char = {
    if (col <= -1 || col >= 3) throw new ArrayIndexOutOfBoundsException(s"Column $col out of bounds")
    if (row <= -1 || row >= 3) throw new ArrayIndexOutOfBoundsException(s"Row $row out of bounds")
    ' '
  }
}
