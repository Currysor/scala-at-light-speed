import scala.collection.mutable

object Altklasur_ss22 extends App {

    case class CIntervall(val lo: Long, val hi: Long) {
      def contains(value: Long): Boolean = (lo <= value) && (value <= hi)
      def isEmpty: Boolean = lo > hi
    }

    def countContained(interval: CIntervall, values: Seq[Long]): Int = {
      values.count(v => (interval.lo <= v) && (v <= interval.hi))
    }

    val aCIntervall = CIntervall(1,5)
    val ascCIntervall = CIntervall(2,7)
    val athCIntervall = CIntervall(3,23)
    val afhCIntervall = CIntervall(4,9)
    val values: Seq[Long] = Seq(1,2,3)
    val counted = countContained(aCIntervall, values)
    println(counted)


    def getSizes(intervals: Seq[CIntervall]): Seq[Long] = {
      intervals.map(x => x.hi - x.lo + 1)
    }

    val manyCIntervals: Seq[CIntervall] = Seq(aCIntervall, ascCIntervall, athCIntervall, afhCIntervall)
    val sized = getSizes(manyCIntervals)
    println(sized)


    def boundingInterval(values: Seq[Long]): CIntervall = {
      values match {
        case Seq() => CIntervall (0, 1)
        case nonEmptyValues => CIntervall (values.min, values.max)
      }
    }

    val boundingValues:Seq[Long] = Seq()
    println(boundingInterval(boundingValues))


    def firstToContainAll(intervals: Seq[CIntervall], values: Seq[Long]): Option[CIntervall] = {
      intervals.find(interval => values.forall(interval.contains))
    }

  val valuesToContain: Seq[Long] = Seq(3,4,7,9,24)
  println(firstToContainAll(manyCIntervals, valuesToContain))

  /** Ein dünn besetztes Array.
   *
   * @constructor Erzeugt ein neues Array mit der übergebenen Größe.
   * @param size die feste Größe dieses Arrays
   * @param default der Standardwert D für dieses Array
   * @throws IllegalArgumentException wenn eine negative Größe übergeben wird.
   */
  class MutableSparseArray[A](val size: Int, val default: A) {
    require(size >= 0, "Die Größe des Arrays muss nicht-negativ sein.")

    // bildet die Arrayposition auf den jeweiligen Wert ab
    private val entries = mutable.HashMap[Int, A]()

    /** Gibt zurück, welches Element an der übergebenen Position gespeichert ist.
     *
     * @throws IndexOutOfBoundsException wenn die Position im Array nicht existiert
     */
    def apply(position: Int): A = {
      if (position < 0 || position >= size) throw new IndexOutOfBoundsException("Index außerhalb des Bereichs")
      entries.getOrElse(position, default)
    }

    /** Schreibt den übergebenen Wert an die übergebene Position im Array.
     *
     * @throws IndexOutOfBoundsException wenn die Position im Array nicht existiert
     */
    def update(position: Int, value: A): Unit = {
      if (position < 0 || position >= size) throw new IndexOutOfBoundsException("Index außerhalb des Bereichs")
      if (value == default) entries.remove(position) else entries(position) = value
    }

    /** Gibt eine Sequenz mit allen Einträgen dieses Arrays zurück,
     * inklusive der nicht explizit gespeicherten Standardwerte. */
    def asDense: Seq[A] = {
      (0 until size).map(apply)
    }

    /** Gibt eine Zeichenkette mit allen Einträgen dieses Arrays zurück,
     * inklusive der nicht explizit gespeicherten Standardwerte. */
    override def toString: String = asDense.mkString("[", ", ", "]")
  }

}
