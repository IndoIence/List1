object Task2 {

  import scala.collection.mutable.ListBuffer


  def Map(input: List[(Int, List[Int])] ): List[(Int,Int)] = {

    // this can be improved by using foldright but i cant get it to work
    //val ListWithFold = ListOfTuples.foldRight(List[(Int,Int)]())((tuple: (Int, List[Int]), acc) => tuple._2.map((_,tuple._1) :: ac
    var ListofTuples = List[(Int,Int)]()
    input.foreach(tuple => ListofTuples = ListofTuples ++ tuple._2.map((_,tuple._1)))
    ListofTuples
  }
  def Reduce(ListOfTuples: List[(Int, Int)]): Map[Int, List[Int]] = {

    ListOfTuples.groupBy(_._1).map( (k,v) => ( k,v.map(_._2) ) )
  }

    def main(args: Array[String]): Unit = {
      val ListOfTuples = List((1, List(2,3)), (3, List(1, 5)), (2, List(5)), (5, List()))



      var MapResult = Map(ListOfTuples)
      val Inverted = Reduce(MapResult).toList
      println(Inverted)


    }


}
