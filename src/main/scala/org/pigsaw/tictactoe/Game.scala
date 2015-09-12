package org.pigsaw.tictactoe

/**
 * Created by Nik on 11 Sep 2015.
 */
class Game {
  def state: Seq[Board] = Seq(new Board)

  def turn: Seq[Option[(Int, Int, Char)]] = Seq(Some(0,0,'X'))
}
