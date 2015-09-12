package org.pigsaw.tictactoe

/**
 * Created by Nik on 11 Sep 2015.
 */
class Game(p1: Player) {
  def state: Seq[Board] = Seq(new Board)

  def turn: Seq[Option[(Int, Int, Char)]] = Seq(Some(0,0, p1.token))
}
