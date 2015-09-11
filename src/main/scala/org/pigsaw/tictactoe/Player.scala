package org.pigsaw.tictactoe

import scala.annotation.tailrec

/**
 * Created by Nik on 19 Aug 2015.
 */
class Player(val token: Char) {

  private def randomTurn = {
    val row = (Math.random() * 3).toInt
    val col = (Math.random() * 3).toInt
    (row, col)
  }

  def turn(b: Board): Option[(Int, Int)] = {
    val turns = Stream.continually(randomTurn)
    // We should be able to find a turn in 200 tries if
    // there is a turn to take at all
    val opTurn = turns.take(200).find( t => !b.isFilled(t._1, t._2) )
    opTurn
  }
}