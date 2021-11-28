import scala.io.Source

def square(x: Int) = x * x

case class Pet(species: String, name: String, age: Int)

val pets = List(
  Pet("cat", "sassy", 2), Pet("cat", "bella", 3),
  Pet("dog", "poppy", 3), Pet("dog", "bodie", 4), Pet("dog", "poppy", 2),
  Pet("bird", "coco", 2), Pet("bird", "kiwi", 1)
)


val a = 3
val b = 3

val myMap = Map[String, Double](("Lesgo",1),("Two",2))
val myKeys = List("1","3") ::: myMap.keys.toList
myMap.keys.contains("No")
