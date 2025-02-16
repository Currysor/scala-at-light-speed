import scala.collection.immutable.ArraySeq

object Altklausur_ws2223 extends App {

  // Aufgabe 1
  class Parent {
    def printMe(): Unit = println("Parent class")
  }

  class ChildOne extends Parent{

  }

  class ChildTwo extends Parent {
    override def printMe(): Unit = println("ChildTwo class")
  }

  def printThat(something: Parent): Unit = {
    something.printMe()
  }

  def printAction(something: Parent): Unit = {
    println("Parent action")
  }

  def printAction(something: ChildTwo): Unit = {
    println("ChildTwo action")
  }


  val x = new ChildOne()
  val y = new ChildTwo()
  printAction(y)
  printThat(y)
  printAction(x)
  printThat(x)

// Aufgabe 2

  def doubleMe ( i : Long ) = i * 2
  val one = List ( " a " , " b " , " c " )
  val two = List ( doubleMe (1) , 2.0 , 3.0)
  val three = ArraySeq( " hello " , " there " , " x " ).map(s => s.size)
  val four = ( " four " , (40.0 , 2))
  val five = ( x : String , y : String ) => x + " " + y + " ! "
  val six = five ( " hello " , " there " )



// Aufgabe 4

  trait Person {
    /* Name der Person */
    def name: String
    /* Alter der Person */
    def age: Int
  }


  case class Professor(name: String, age: Int) extends Person
  case class Student(name: String, age: Int, hasBachelor: Boolean) extends Person

  /* Beispiel Program */
  val persons = Seq(
    Student("Host", 23, false),
    Student("Rudi", 18, false),
    Professor("Prof. Dr. Hirnriss", 52),
    Student("Gerlinde", 19, true),
    Student("Roswitha", 25, true)
  )
  /** Findet alle Professoren. */
  def getProfessors(persons: Seq[Person]): Seq[Person] = {
    persons.filter(p => p.isInstanceOf[Professor]).map(_.asInstanceOf[Professor])
  }


  /** Findet alle Studenten die im Master eingeschrieben sind.
   * D.h Sie haben einen Bachelorabschluss.
   */
  def getMasterStudents(persons: Seq[Person]): Seq[Person] = {
    persons.filter(p => p.isInstanceOf[Student] && p.asInstanceOf[Student].hasBachelor).map(_.asInstanceOf[Student])
  }



  /** Berechnet das Durchschnittsalter aller Bachelorstudenten */
  def getAverageAgeOfBachelorStudents(persons: Seq[Person]): Double = {
    val bStudents = persons.filter(p => (p.isInstanceOf[Student]) && (!p.asInstanceOf[Student].hasBachelor)).map(_.asInstanceOf[Student].age)
    if (bStudents.nonEmpty)
      bStudents.sum / bStudents.length
      else 0.0
  }



  /** Erstellt eine Liste mit Betreuern der Gestalt (Student, Some(Professor)). */
  def getMasterSupervisors(persons: Seq[Person]): Seq[(Person, Option[Person])] = {
    val masterStudents = getMasterStudents(persons)
    val professors = getProfessors(persons)

    masterStudents.zipAll(professors, null, null).map {
      case (student, professor) => (student, Option(professor))
    }
  }



  println(getProfessors(persons))
  println(getMasterStudents(persons))
  println(getAverageAgeOfBachelorStudents(persons))
  println(getMasterSupervisors(persons))


  // Aufgabe 5
  /** Ein Wegpunkt entlang eines Pfades . */
  trait Waypoint {
    /** Gibt den Namen des Wegpunktes zurück . */
    def name: String
    /** Gibt die Koordinaten des Wegpunktes zurück . */
    def coordinates: Coordinates3D
  }

  /** Ein Punkt in einem kartesischen Koordinatensystem . */
  trait Coordinates3D {
    /** Gibt die x - Koordinate zurück . */
    def x: Double
    /** Gibt die y - Koordinate zurück . */
    def y: Double
    /** Gibt die Höhe (z - Koordinate ) zurück . */
    def elevation: Double
  }

  /** Eine Streckenlänge . */
  trait Distance {
    /** Gibt die Distanz in Metern zurück . */
    def toMeters: Double
    /** Gibt die Distanz in englischen Meilen zurück . */
    def toMiles: Double
  }

  /** Ein Distanzmaß zwischen zwei Objekten vom Typ A . */
  trait DistanceFunction[A] {
    /** Gibt die Distanz zwischen zwei Objekten zurück . */
    def apply(from: A, to: A): Distance
  }

  /** Bildet Paare aus aufeinanderfolgenden Elementen .
   *
   * pairwise ( Seq (1 , 2 , 3)) -> Seq ( (1 , 2) , (2 , 3) )
   */
  def pairwise[A](input: Seq[A]): Seq[(A, A)] = input.zip(input.tail)

  /** Gibt den Namen eines Wegpunktes mit maximaler Höhe zurück,
   falls dieser in der Eingabe existiert, ansonsten None. */
  def maxElevationWaypoint(waypoints: Seq[Waypoint]): Option[String] = {
    waypoints.maxByOption(_.coordinates.elevation).map(_.name)
  }
}
