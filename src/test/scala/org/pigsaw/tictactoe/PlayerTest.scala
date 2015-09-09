package org.pigsaw.tictactoe

import org.scalatest.Matchers
import org.scalatest.FlatSpec

class PlayerTest extends FlatSpec with Matchers {

  "Player" should "have a token" in {
    val pX = new Player('X')
    pX.token should equal ('X')

    val pO = new Player('O')
    pO.token should equal ('O')
  }

  "turn" should "play randomly on an empty board (meaing 10 moves won't be all the same)" in {
    val p = new Player('X')
    val moves = (1 to 10) map { _ => p.turn }
    val twoMovesAreDifferent = moves exists { m1 => moves exists { m2 => m1 != m2 }}
    twoMovesAreDifferent should be (true)
  }

  it should "always play a legal turn on an empty board" in {
    val p = new Player('O')
    val b = new Board
    (1 to 20).foreach { _ =>
      val turn = p.turn
      // This should not throw an exception
      b.takeTurn(turn._1, turn._2, p.token)
    }
  }

  it should "produce different results when starting from scratch" in {
    val length = 20
    val p1 = new Player('Z')
    val moves1 = (1 to length) map { _ => p1.turn }
    val p2 = new Player('Z')
    val moves2 = (1 to length) map { _ => p2.turn }

    moves1 should not equal (moves2)
  }

  it should "produce different rows when starting from scratch" in {
    val length = 20
    val p1 = new Player('Z')
    val rows1 = (1 to length) map { _ => p1.turn } map { _._1 }
    val p2 = new Player('Z')
    val rows2 = (1 to length) map { _ => p2.turn } map { _._1 }

    rows1 should not equal (rows2)
  }

  it should "produce different columns when starting from scratch" in {
    val length = 20
    val p1 = new Player('Z')
    val cols1 = (1 to length) map { _ => p1.turn } map { _._2 }
    val p2 = new Player('Z')
    val cols2 = (1 to length) map { _ => p2.turn } map { _._2 }

    cols1 should not equal (cols2)
  }

  it should "start on any cell of an empty board given enough goes" in {
    val p = new Player('X')
    val coords = for { i <- 0 to 2; j <- 0 to 2 } yield (i, j)
    coords.foreach { coord =>
      val starters = (1 to 100) map { _ => p.turn }
      starters should contain (coord)
    }
  }
}
