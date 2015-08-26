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

  it should "allow build up a board over several turns" in {
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
}
