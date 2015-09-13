package org.pigsaw.tictactoe

import org.scalatest.{Matchers, FlatSpec}

/**
 * Created by Nik on 11 Sep 2015.
 */
class GameTest extends FlatSpec with Matchers {

  "state" should "start with an empty board" in {
    val g = new Game(new Player('X'), new Player('O'))
    val b = g.state(0)

    b.isEmpty should be (true)
  }

  "turn" should "have some first turn" in {
    val g = new Game(new Player('X'), new Player('O'))
    g.turn.length should be >= (1)
  }

  "state" should "have the first player's token in the turn" in {
    val g = new Game(new Player('X'), new Player('O'))
    val t1 = g.turn(0)
    g.state(1)(t1) should equal('X')
  }

  it should "have the first player's token in the turn (with another token to avoid faking)" in {
    val g = new Game(new Player('Z'), new Player('Y'))
    val t1 = g.turn(0)
    g.state(1)(t1) should equal('Z')
  }

  it should "have alternate player tokens for each turn" in {
    val g = new Game(new Player('A'), new Player('B'))
    g.state(1)(g.turn(0)) should equal('A')
    g.state(2)(g.turn(1)) should equal('B')
    g.state(3)(g.turn(2)) should equal('A')
    g.state(4)(g.turn(3)) should equal('B')
  }

  "turn" should "have different co-ordinates each turn" in {
    val g = new Game(new Player('A'), new Player('B'))
    g.turn.distinct.length should equal (g.turn.length)
  }

  it should "be nine turns long" in {
    val g = new Game(new Player('A'), new Player('B'))
    g.turn.length should equal (9)
  }

  "board" should "be 10 boards long (empty board + nine turns)" in {
    val g = new Game(new Player('A'), new Player('B'))
    g.state.length should equal (10)
  }

  "turn" should "not play the same turns every time" in {
    val sameTurns = for {
      _ <- 1 to 20
      g1 = new Game(new Player('A'), new Player('B'))
      g2 = new Game(new Player('A'), new Player('B'))
    } yield (g1.turn == g2.turn)
    sameTurns should contain (false)
  }
}
