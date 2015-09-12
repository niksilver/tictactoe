package org.pigsaw.tictactoe

import org.scalatest.{Matchers, FlatSpec}

/**
 * Created by Nik on 11 Sep 2015.
 */
class GameTest extends FlatSpec with Matchers {

  "state" should "start with an empty board" in {
    val g = new Game
    val b = g.state(0)

    b.isEmpty should be (true)
  }

  "turn" should "have some turn as the first turn" in {
    val g = new Game
    g.turn(0) should not be (empty)
  }
}
