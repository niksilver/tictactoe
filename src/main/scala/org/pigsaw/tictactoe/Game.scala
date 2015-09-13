package org.pigsaw.tictactoe

/**
 * Created by Nik on 11 Sep 2015.
 */
class Game(p1: Player, p2: Player) {

  private def otherPlayer(p: Player) =
    if (p1.token == p.token) p2 else p1

  def allTurns(accTurns: Seq[(Int,Int)], accBoard: Seq[Board], nextPlayer: Player): (Seq[(Int,Int)], Seq[Board]) = {
    val b = accBoard.head
    val turn = nextPlayer.turn(b)
    turn match {
      case None => (accTurns, accBoard)
      case Some(coord) => {
        val b2 = b.takeTurn(coord, nextPlayer.token)
        allTurns(coord +: accTurns, b2 +: accBoard, otherPlayer(nextPlayer))
      }
    }
  }

  val allTurns: (Seq[(Int,Int)], Seq[Board]) = allTurns(Nil, Seq(new Board), p1)

  val turn = allTurns._1.reverse

  val state = allTurns._2.reverse
}
