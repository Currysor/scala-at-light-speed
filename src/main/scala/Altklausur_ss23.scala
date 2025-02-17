object Altklausur_ss23 extends App {

  case class ImperialHeight(val feet: Int, val inches: Int) {
    if ((feet < 0) || (inches < 0))
      throw new IllegalArgumentException("Eingabe negativ")

    if (inches > 11)
      throw new IllegalArgumentException("Inches zu groß")

    def modify(deltaInches: Int): ImperialHeight = {
      val totalInches = feet * 12 + inches + deltaInches
      if (totalInches < 0) {
        throw new IllegalArgumentException("Ergebnis wäre negativ")
      }

      val newFeet = totalInches / 12
      val newInches = totalInches % 12

      new ImperialHeight(newFeet, newInches)
    }

    override def equals(obj: Any): Boolean = {
      obj match {
        case that: ImperialHeight => this.feet == that.feet && this.inches == that.inches
        case _ => false
      }
    }

    override def hashCode(): Int = {
      31 * feet.hashCode() + inches.hashCode()
    }
  }

  object ImperialHeight {
    def fromInches(in: Int): ImperialHeight = {
      if (in < 0)
        throw new IllegalArgumentException("Eingabe negativ")

      val newFeet = in / 12
      val newInch = in % 12
      new ImperialHeight(newFeet, newInch)
    }
  }


  val anImperialHeight = ImperialHeight(4, 1)
  val anoImperialHeight = ImperialHeight(4, 1)
  val thirdImperialHeight = ImperialHeight.fromInches(14)

  println(anImperialHeight == anoImperialHeight)
  println(thirdImperialHeight)




  // Aufgabe 6
  case class Person(name: String, height: ImperialHeight) {

  }

  def sortPersonsByHeight(s: Seq[Person]): Seq[Person] = {
    implicit val imperialHeightOrdering: Ordering[ImperialHeight] = Ordering.by(h => h.feet * 12 + h.inches)
    s.sortBy(x => x.height)
  }

  val aPerson = Person("Bob", ImperialHeight(6, 10))
  val aSecPerson = Person("Bobbie", ImperialHeight(6, 11))
  val aThPerson = Person("Bobbinski", ImperialHeight(7, 3))

  val aPersonSeq: Seq[Person] = Seq(aPerson, aSecPerson, aThPerson)
  val sortedPersons: Seq[Person] = sortPersonsByHeight(aPersonSeq)
  println(sortedPersons)

  def findLargestPersonWithName(name: String, s: Seq[Person]): Option[Person] = {
    val sortedPersons = sortPersonsByHeight(s)
    sortedPersons.find(_.name == name)
  }

  println(findLargestPersonWithName("Bobbinski", aPersonSeq))



  // Aufgabe 7

  /** Repräsentiert vorgemerkte Änderungen in einer [[Map]]. */
  trait Changes[K, V]

  /** Speichert eine Map, bei der noch keine Änderungen vorgemerkt wurden. */
  case class Unchanged[K, V](map: Map[K, V]) extends Changes[K, V]

  /** Bedeutet, dass in `base` das Paar `(key, newValue)` eingefügt werden soll. */
  case class Modify[K, V](key: K, newValue: V, base: Changes[K, V]) extends Changes[K, V]

  /** Bedeutet, dass in `base` der Schlüssel `key` gelöscht werden soll. */
  case class Delete[K, V](key: K, base: Changes[K, V]) extends Changes[K, V]


  /** Prüft, ob ein gegebener Schlüssel nach Anwendung der
   * vorgemerkten Änderungen vorhanden ist.
   *
   * @param searchKey der Schlüssel, nach dem gesucht wird
   * @param map       die Map mit vorgemerkten Änderungen, in der gesucht wird
   * @return true, wenn der Schlüssel vorhanden ist, sonst false
   */
  def containsKey[K, V](searchKey: K, map: Changes[K, V]): Boolean = map match {
    case Unchanged(m) => m.contains(searchKey) // Basisfall: Prüfe direkt in der ursprünglichen Map
    case Modify(key, _, base) =>
      if (key == searchKey) true // Wenn der Schlüssel explizit geändert wurde, ist er vorhanden
      else containsKey(searchKey, base) // Sonst weiter in der Historie suchen
    case Delete(key, base) =>
      if (key == searchKey) false // Falls der Schlüssel gelöscht wurde, ist er nicht vorhanden
      else containsKey(searchKey, base) // Sonst weiter in der Historie suchen
  }

}