package org.pigsaw.tictactoe

/**
 * Created by Nik on 11 Sep 2015.
 */
class Game(p1: Player, p2: Player) {

  private def otherPlayer(p: Player) =
    if (p1.token == p.token) p2 else p1

  private def turnsAndBoards(accTurns: Seq[(Int,Int)], accBoard: Seq[Board], nextPlayer: Player): (Seq[(Int,Int)], Seq[Board]) = {
    val b = accBoard.head
    val turn = nextPlayer.turn(b)
    turn match {
      case None => (accTurns, accBoard)
      case Some(coord) => {
        val b2 = b.takeTurn(coord, nextPlayer.token)
        turnsAndBoards(coord +: accTurns, b2 +: accBoard, otherPlayer(nextPlayer))
      }
    }
  }

  private val turnsAndBoards: (Seq[(Int,Int)], Seq[Board]) = turnsAndBoards(Nil, Seq(new Board), p1)

  val turns = turnsAndBoards._1.reverse

  val boards = turnsAndBoards._2.reverse
}
