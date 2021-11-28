import java.io._


case class Pet(species: String, name: String, age: Int)

object Hello extends App {
  val pets = List(
    Pet("cat", "sassy", 2), Pet("cat", "bella", 3),
    Pet("dog", "poppy", 3), Pet("dog", "bodie", 4), Pet("dog", "poppy", 2),
    Pet("bird", "coco", 2), Pet("bird", "kiwi", 1)
  )
  val map2 = pets.groupBy(_.species)
  for ((k,v) <- map2) print(k,v)
  println()
  val map1 = pets.groupBy(_.species).mapValues(_.map(_.name))
  for ((k,v) <- map1) print(k,v)
  val map3 = pets.groupBy(_.species).mapValues(_.size)
  println()
  for ((k,v) <- map3) print(k,v)
  val map4 = pets.groupMapReduce(_.species)(_ => 1)(_+_)
  println()
  for ((k,v) <- map4) print(k,v)

  val map5 = pets.groupMap(_.species)(_ => 1)
  println()
  for ((k,v) <- map5) print(k,v)

  val map6 = pets.groupMap(_.species)(_.name)
  println()
  for ((k,v) <- map6) print(k,v)
}
