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

  it should "throw exception if accessing column slightly too far out" in {
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
}
