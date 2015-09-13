package org.pigsaw.tictactoe

import org.scalatest.{Matchers, FlatSpec}

/**
 * Created by Nik on 11 Sep 2015.
 */
class GameTest extends FlatSpec with Matchers {

  "boards" should "start with an empty board" in {
    val g = new Game(new Player('X'), new Player('O'))
    val b = g.boards(0)

    b.isEmpty should be (true)
  }

  "turns" should "have some first turn" in {
    val g = new Game(new Player('X'), new Player('O'))
    g.turns.length should be >= (1)
  }

  "boards" should "have the first player's token in the turn" in {
    val g = new Game(new Player('X'), new Player('O'))
    val t1 = g.turns(0)
    g.boards(1)(t1) should equal('X')
  }

  it should "have the first player's token in the turn (with another token to avoid faking)" in {
    val g = new Game(new Player('Z'), new Player('Y'))
    val t1 = g.turns(0)
    g.boards(1)(t1) should equal('Z')
  }

  it should "have alternate player tokens for each turn" in {
    val g = new Game(new Player('A'), new Player('B'))
    g.boards(1)(g.turns(0)) should equal('A')
    g.boards(2)(g.turns(1)) should equal('B')
    g.boards(3)(g.turns(2)) should equal('A')
    g.boards(4)(g.turns(3)) should equal('B')
  }

  "turns" should "have different co-ordinates each turn" in {
    val g = new Game(new Player('A'), new Player('B'))
    g.turns.distinct.length should equal (g.turns.length)
  }

  it should "be nine turns long" in {
    val g = new Game(new Player('A'), new Player('B'))
    g.turns.length should equal (9)
  }

  "boards" should "be 10 boards long (empty board + nine turns)" in {
    val g = new Game(new Player('A'), new Player('B'))
    g.boards.length should equal (10)
  }

  "turns" should "not play the same turns every time" in {
    val sameTurns = for {
      _ <- 1 to 20
      g1 = new Game(new Player('A'), new Player('B'))
      g2 = new Game(new Player('A'), new Player('B'))
    } yield (g1.turns == g2.turns)
    sameTurns should contain (false)
  }
}
