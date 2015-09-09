package org.pigsaw.tictactoe

import scala.annotation.tailrec

/**
 * Created by Nik on 19 Aug 2015.
 */
class Player(val token: Char) {

  def turn(b: Board): (Int, Int) = {
    @tailrec
    def calc: (Int, Int) = {
      val row = (Math.random() * 3).toInt
      val col = (Math.random() * 3).toInt
      if (!b.isFilled(row,col))
        (row, col)
      else
        calc
    }
    calc
  }
}