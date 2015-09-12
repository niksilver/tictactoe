package org.pigsaw.tictactoe

import org.scalatest.{Matchers, FlatSpec}

/**
 * Created by Nik on 11 Sep 2015.
 */
class GameTest extends FlatSpec with Matchers {

  "generate" should "start with an empty board" in {
    val g = Game.generate
    val b = g.head

    Board.coords.foreach{ c =>
      b.isFilled(c._1, c._2) should equal (false)
    }
  }
}
