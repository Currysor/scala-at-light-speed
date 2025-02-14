package com.rockthejvm

object ObjectOrientation extends App {

  // Object body
  // extends App makes the class runnable for IntelliJ

  // class and instance
  class Animal {
    // define fields
    val age: Int = 0
    // define methods
    def eat() = println("I am eating")
  }
  val anAnimal = new Animal

  // Vererbung (Inheritance)
  // constructor definition ==> NOT fields (Dog.name does not work)
  class Dog(val name: String) extends Animal
  val aDog = new Dog("Lassie")

  // constructor definition with a val acts like a field
  aDog.name

  // subtype polymorphism
  // the most derived (abgeleitete) method will be called at runtime
  val aDeclaredAnimal: Animal = new Dog("Bello")
  aDeclaredAnimal.eat()

  // abstract class
  // Not all methods/variables need to be defined
  // private = only accessible by the class itself
  // protected = class itself and all derived (abgeleiteten) classes
  abstract class WalkingAnimal {
    val hasLegs = true
    def walk(): Unit
  }

  // "interface" = ultimate abstract type
  // not implemented/defined methods for later implementation in concrete classes
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  // Also valid method name
  trait Philosopher {
    def ?!(thought: String): Unit
  }

  // single class inheritance, multi-trait mixing
  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println("I am eating u, animal!")
    // override of Animal eat also possible
    override def eat(): Unit = super.eat()

    def ?!(thought: String): Unit = println(s"I was thinking of $thought")
  }

  // infix notation = object method argument (ONLY possible for methods with ONE argument)
  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog
  aCroc ?! "What if i could fly?"

  // operators in Scala are actually methods
  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2)

  // anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so i can eat anything!")
  }

  // singleton object
  // The only instance of MySingleton type
  object MySingleton {
    val mySpecialValue = 666
    def mySpecialMethod(): Int = 6969
    def apply(x: Int): Int = x + 1
  }

  // apply allows easier use of object creation
  MySingleton.mySpecialMethod()
  MySingleton.apply(65)
  MySingleton(65)

  // Companion Object
  // Companions can access each others private fields/methods
  // singleton Animal and instances of Animal are different things
  object Animal {
    val canLiveIndefinitley = false
  }
  // Zugriff
  val animalsCanLiveForever = Animal.canLiveIndefinitley

  // case classes
  /* automatically includes
    - sensible equals
    - hash code
    - serialization
    - companion with apply
    - pattern matching
   */
  case class Person(name: String, age: Int)

  // can be constructed without new
  val bob = Person("Bob", 54)

  //exceptions with try and catch
  try {
    // code that can throw
    val x: String = null
    x.length
  } catch {
    case e: Exception => "some Error Message"
  } finally {
    // execute no matter what happens
  }

  // generics
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  }

  // using a generic with a concrete type
  val aList: List[Int] = List(1,2,3) // List.apply(1,2,3)
  val first = aList.head                    // Int
  val rest = aList.tail                     // Int
  val aStringList = List("hello", "Scala")  // String
  val firstString = aStringList.head        // String

  // Take Away
  // POINT 1: Scala usually operates with immutable objects/values
  // ANY modification must return ANOTHER object
  /*
    Benefits:
    1) Multithreading
    2) Clear sense of the code
   */
  val reversedList = aList.reverse // returns NEW list

  // POINT 2: Scala is closest to object orientated programming
  
}
