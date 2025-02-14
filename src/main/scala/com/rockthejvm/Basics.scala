package com.rockthejvm

object Basics extends App {

  // defining a value
  val meaningOfLife: Int = 42

  // Type interference
  val aBoolean = false

  // Strings and Operations
  val aString = "I hate Scala"
  val aComposedString = "I" + " " + "hate" + " " + "Scala"
  val anInterpolatedString = s"The meaning of life is $meaningOfLife"

  // expressions = structures can be reduced to a value
  val anExpression = 2 + 3

  // if expression
  val ifExpression = if(meaningOfLife > 43) 56 else 666
  val chainedIfExpression = {
    if (meaningOfLife > 43) 56
    else if (meaningOfLife < 0) -69
    else if (meaningOfLife > 999) 666
    else 0
  }

  // Code Blocks
  val aCodeBlock = {
    //definitions
    val aLocalValue = 67

    // return value of the block is the value of the last expression
    aLocalValue + 2
  }

  //define a function
  def myFunction(x: Int, y: String): String = {
    y + " " + x
  }

  // recursive function
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }

  /* Example of n = 5 and Recursion

  factorial(5) = 5 * factorial(4)
  factorial(4) = 4 * factorial(3)
  factorial(3) = 3 * factorial(2)
  factorial(2) = 2 * factorial(1)
  factorial(1) = 1
   */

  // The Unit return type = no meaningful value
  // Type of SIDE EFFECTS
  println("I hate Scala")

  val theUnit: Unit = ()

  def myUnitReturningFunction(): Unit = {
    println("I love returning Unit")
  }
  

}
