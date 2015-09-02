package org.pigsaw.tictactoe

import org.scalatest.Matchers
import org.scalatest.FlatSpec

class PlayerTest extends FlatSpec with Matchers {

  "org.pigsaw.tictactoe.Player" should "have a token" in {
    val pX = new Player('X')
    pX.token should equal ('X')

    val pO = new Player('O')
    pO.token should equal ('O')
  }


}
