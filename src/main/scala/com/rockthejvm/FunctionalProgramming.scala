package com.rockthejvm

object FunctionalProgramming extends App {

  class Person(name: String) {
    def apply(age: Int) = println(s"I have aged $age years")
  }

  // apply method INVOKES bob as a function ==> bob(43) == bob.apply(43)
  val bob = new Person("Bob")
  bob.apply(43)
  bob(43)

  // Functional Programming
  /*
  - compose functions
  - pass functions as args
  - return functions as results

  ==> FunctionX
   */

  // Example for a simple function for doubling
  val doubler: Function1[Int, Int] = new Function[Int, Int] {
    override def apply(x: Int): Int = 2 * x
  }
  println(doubler(4))

  // Easier written syntax
  val ezDoubler: Int => Int = (x: Int) => 2 * x
  println(ezDoubler(4))

  // Higher order functions
  // ==> take functions as args or return functions as results
  // .map
  // Uses a given function and applies it to a given data structure
  val aMappedList: List[Int] = List(1, 2, 3).map(x => x + 1)
  println(aMappedList)

  // .flatmap
  // Uses a given function and applies it, but also concatenates the results into one
  val aFlatMappedList = List(1,2,3).flatMap(x => List(x, 2 * x))
  println(aFlatMappedList)

  // .filter
  val aFilteredList = List(1,2,3,4,5).filter(_ <= 3)   // val aFilteredList = List(1,2,3,4,5).filter(x => x <= 3)
  println(aFilteredList)

  // all pairs between the numbers 1,2,3 and the letters a,b,c
  val allPairs = List(1,2,3).flatMap(number => List("a", "b", "c").map(letter => s"$number-$letter"))
  println(allPairs)

  // for comprehensions
  // equivalent to the map/flatmap chain
  val alternativePairs = for {
    number <- List(1,2,3)
    letter <- List("a", "b", "c")
  } yield s"$number-$letter"

  /// Lists ///
  val aList = List(1,2,3,4,5)
  val firstElement = aList.head
  val rest = aList.tail
  val aPrependedList = 0 :: aList         // List(0,1,2,3,4,5)
  val anExtendedList = 0 +: aList :+ 6    // List(0,1,2,3,4,5,6)

  /// Sequences ///
  val aSequence: Seq[Int] = Seq(1,2,3)
  val accessesedElement = aSequence(1)      // The element at index 1 = 2

  // vectors
  val aVector = Vector(1,2,3,4,5)

  // sets = NO DUPLICATES
  val aSet = Set(1,2,3,4,1,2,3)       // Set(1,2,3,4)
  val setHas5 = aSet.contains(5)      // False
  val anAddedSet = aSet + 5           // Set(1,2,3,4,5)
  val aRemovedSet = aSet - 3          // Set(1,2,4,5)

  // ranges
  val aRange = 1 to 100
  val twoByTwo = aRange.map(x => 2 * x).toList     // 2,4,6,8,...,200

  // tuples = groups of values under the same value
  val aTuple = ("Bon Jovi", "Rock", 1982)

  // maps
  val aPhoneBook: Map[String, Int] = Map(
    ("Tarzan", 66438),
    ("Jane" -> 7342)      // alternative syntax
  )















}
