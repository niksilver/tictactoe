package org.pigsaw.tictactoe

/**
 * Created by Nik on 19 Aug 2015.
 */
class Player(val token: Char) {

  def turn: (Int, Int) = {
    val row = (Math.random() * 3).toInt
    val col = (Math.random() * 3).toInt
    (row, col)
  }
}