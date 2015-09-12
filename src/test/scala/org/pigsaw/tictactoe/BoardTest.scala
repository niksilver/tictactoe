package org.pigsaw.tictactoe

import org.scalatest.{ShouldMatchers, FlatSpec}

class BoardTest extends FlatSpec with ShouldMatchers {

  "New board" should "be empty in key locations" in {
    val b = new Board()
    b(0,0) should equal (' ')
    b(1,1) should equal (' ')
    b(2,0) should equal (' ')
    b(2,2) should equal (' ')
  }

  "apply" should "throw exception if accessing column slightly too far out" in {
    an [ArrayIndexOutOfBoundsException] should be thrownBy {
      val b = new Board()
      b(0, 3)
    }
  }

  it should "throw exception if accessing column much too far out" in {
    an [ArrayIndexOutOfBoundsException] should be thrownBy {
      val b = new Board()
      b(0, 10)
    }
  }

  it should "throw exception if accessing column slightly too far back" in {
    an [ArrayIndexOutOfBoundsException] should be thrownBy {
      val b = new Board()
      b(0, -1)
    }
  }

  it should "throw exception if accessing column much too far back" in {
    an [ArrayIndexOutOfBoundsException] should be thrownBy {
      val b = new Board()
      b(0, -10)
    }
  }

  it should "throw exception if accessing row slightly too far out" in {
    an [ArrayIndexOutOfBoundsException] should be thrownBy {
      val b = new Board()
      b(3, 0)
    }
  }

  it should "throw exception if accessing row much too far out" in {
    an [ArrayIndexOutOfBoundsException] should be thrownBy {
      val b = new Board()
      b(10, 0)
    }
  }

  it should "throw exception if accessing row slightly too far back" in {
    an [ArrayIndexOutOfBoundsException] should be thrownBy {
      val b = new Board()
      b(-1, 0)
    }
  }

  it should "throw exception if accessing row much too far back" in {
   an [ArrayIndexOutOfBoundsException] should be thrownBy {
      val b = new Board()
      b(-10, 0)
    }
  }

  "takeTurn" should "place the token in an empty cell" in {
    val b0 = new Board()
    b0(0,0) should equal (' ')

    val b1 = b0.takeTurn(0, 0, 'Z')
    b1(0,0) should equal ('Z')
  }

  it should "place the token in an empty cell (another token to avoid faking)" in {
    val b0 = new Board()
    b0(0,0) should equal (' ')

    val b1 = b0.takeTurn(0, 0, 'Y')
    b1(0,0) should equal ('Y')
  }

  it should "not place the token in every cell" in {
    val b0 = new Board()
    b0(0,0) should equal (' ')
    b0(0,1) should equal (' ')
    b0(1,0) should equal (' ')

    val b1 = b0.takeTurn(0, 0, 'Z')
    b1(0,0) should equal ('Z')
    b1(0,1) should equal (' ')
    b1(1,0) should equal (' ')
  }

  it should "not place the token in every cell - second test to avoid faking" in {
    val b0 = new Board()
    b0(2,0) should equal (' ')
    b0(0,1) should equal (' ')
    b0(1,0) should equal (' ')

    val b1 = b0.takeTurn(2, 0, 'Z')
    b1(2,0) should equal ('Z')
    b1(0,1) should equal (' ')
    b1(1,0) should equal (' ')
  }

  it should "allow us to build up a board over several turns" in {
    val b0 = new Board()
    b0(0,1) should equal (' ')
    b0(1,0) should equal (' ')
    b0(2,0) should equal (' ')

    val b1 = b0.takeTurn(0, 1, 'Z')
    b1(0,1) should equal ('Z')
    b1(1,0) should equal (' ')
    b1(2,0) should equal (' ')

    val b2 = b1.takeTurn(1, 0, 'Z')
    b2(0,1) should equal ('Z')
    b2(1,0) should equal ('Z')
    b2(2,0) should equal (' ')

    val b3 = b2.takeTurn(2, 0, 'Z')
    b3(0,1) should equal ('Z')
    b3(1,0) should equal ('Z')
    b3(2,0) should equal ('Z')
  }

  it should "reject a turn in a filled cell" in {
    val b0 = new Board()
    val b1 = b0.takeTurn(0, 0, 'X')
    an [Exception] should be thrownBy {
      b1.takeTurn(0, 0, 'Y')
    }
  }

  "isFilled" should "be true if cell is filled with some token" in {
    val b0 = new Board()
    val b1 = b0.takeTurn(0, 0, 'Z')
    b1.isFilled(0,0) should equal (true)
  }

  it should "be true if cell is filled with some other token (to avoid faking)" in {
    val b0 = new Board()
    val b1 = b0.takeTurn(0, 0, 'Y')
    b1.isFilled(0,0) should equal (true)
  }

  it should "be false for any cell on the initial board" in {
    val b = new Board()
    b.isFilled(0,0) should equal (false)
    b.isFilled(1,0) should equal (false)
    b.isFilled(0,1) should equal (false)
    b.isFilled(2,2) should equal (false)
  }

  "winner" should "be None for an empty board" in {
    val b = new Board()
    b.winner should equal (None)
  }

  it should "be the winning token if there are three on the first row" in {
    val b0 = new Board()
    val b1 = b0.takeTurn(0, 0, 'X').takeTurn(0, 1, 'X').takeTurn(0, 2, 'X')
    b1.winner should equal (Some('X'))
  }

  it should "be None if the first row is XYY" in {
    val b0 = new Board()
    val b1 = b0.takeTurn(0, 0, 'X').takeTurn(0, 1, 'Y').takeTurn(0, 2, 'Y')
    b1.winner should equal (None)
  }

  it should "be the winning token if there are three on the second row" in {
    val b0 = new Board()
    val b1 = b0.takeTurn(1, 0, 'X').takeTurn(1, 1, 'X').takeTurn(1, 2, 'X')
    b1.winner should equal (Some('X'))
  }

  it should "be the winning token if there are three on the third row" in {
    val b0 = new Board()
    val b1 = b0.takeTurn(2, 0, 'X').takeTurn(2, 1, 'X').takeTurn(2, 2, 'X')
    b1.winner should equal (Some('X'))
  }

  it should "be the winning token if there are three on the first column" in {
    val b0 = new Board()
    val b1 = b0.takeTurn(0, 0, 'X').takeTurn(1, 0, 'X').takeTurn(2, 0, 'X')
    b1.winner should equal (Some('X'))
  }

  it should "be the winning token if there are three on the second column" in {
    val b0 = new Board()
    val b1 = b0.takeTurn(0, 1, 'X').takeTurn(1, 1, 'X').takeTurn(2, 1, 'X')
    b1.winner should equal (Some('X'))
  }

  it should "be the winning token if there are three on the third column" in {
    val b0 = new Board()
    val b1 = b0.takeTurn(0, 2, 'X').takeTurn(1, 2, 'X').takeTurn(2, 2, 'X')
    b1.winner should equal (Some('X'))
  }

  it should "be the winning token if there are three on the forward diagonal" in {
    val b0 = new Board()
    val b1 = b0.takeTurn(0, 0, 'X').takeTurn(1, 1, 'X').takeTurn(2, 2, 'X')
    b1.winner should equal (Some('X'))
  }

  it should "be the winning token if there are three on the backwards diagonal" in {
    val b0 = new Board()
    val b1 = b0.takeTurn(0, 2, 'X').takeTurn(1, 1, 'X').takeTurn(2, 0, 'X')
    b1.winner should equal (Some('X'))
  }

  "isFull" should "be false for an empty board" in {
    val b = new Board
    b.isFull should equal (false)
  }

  it should "be true if all cells are filled" in {
    val b = (new Board).
      takeTurn(0, 0, 'X').takeTurn(0, 1, 'X').takeTurn(0, 2, 'X').
      takeTurn(1, 0, 'X').takeTurn(1, 1, 'X').takeTurn(1, 2, 'X').
      takeTurn(2, 0, 'X').takeTurn(2, 1, 'X').takeTurn(2, 2, 'X')
    b.isFull should be (true)
  }

  it should "be false if all cells are filled except one" in {
    val b = (new Board).
      takeTurn(0, 0, 'X').takeTurn(0, 1, 'X').takeTurn(0, 2, 'X').
      takeTurn(1, 0, 'X').takeTurn(1, 1, 'X').takeTurn(1, 2, 'X').
      takeTurn(2, 0, 'X').takeTurn(2, 1, 'X')
    b.isFull should be (false)
  }

  it should "be false if all cells are filled except another one (to avoid faking)" in {
    val b = (new Board).
      takeTurn(0, 0, 'X').takeTurn(0, 1, 'X').
      takeTurn(1, 0, 'X').takeTurn(1, 1, 'X').takeTurn(1, 2, 'X').
      takeTurn(2, 0, 'X').takeTurn(2, 1, 'X').takeTurn(2, 2, 'X')
    b.isFull should be (false)
  }

  "isEmpty" should "be true for an empty board" in {
    val b = new Board
    b.isEmpty should be (true)
  }

  it should "be false when there is some cell filled" in {
    val b = (new Board).takeTurn(0,0,'X')
    b.isEmpty should be (false)
  }

  it should "be false when there is some other cell filled (to avoid faking)" in {
    val b = (new Board).takeTurn(1,1,'X')
    b.isEmpty should be (false)
  }
}
