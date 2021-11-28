import scala.collection.immutable.ListMap
import scala.io.Source

object Task1 extends App{
  val books = List[(String, String)](
    ("Romeo and Juliet", "Books/Romeo and Juliet.txt"),
    ("Metamorphosis.txt", "Books/Metamorphosis.txt"),
    ("Crime and Punishment","Books/Crime and Punishment.txt")
  )
  val loader = new LoadBooks(books)
  val loadedBooks = loader.load()
  printWords(5, loadedBooks)
  println(wordInHowManyDocs("titularcounsellor",loadedBooks))
  val idfs = idf(loadedBooks)
  print(idfs.keys.size)
  printTfIdf(5,tfidf(loadedBooks))

 /* println(textNoPunct take(100) mkString(" "))
  val unsortedCount = textNoPunct.groupMapReduce(identity)(_ => 1)(_+_)
  val count = ListMap(unsortedCount.toSeq.sortWith(_._2 > _._2):_*)
  printWords(5, count)
  //for ((k,v) <- count) print(k,v)
 */
}

def printWords(n: Int, books: List[(String, Map[String, Int], _)]): Unit = {
  books.foreach(
    book => {
      println("Title: " + book._1)
      ListMap(book._2.toSeq.sortWith(_._2 > _._2): _*).take(n).foreach(word => println(word._1 + " " + word._2))
    }
  )
}

def printTfIdf (n: Int, books: Map[String, Map[String, Double]]): Unit = {
  books.foreach(book => {
    println("Title: "+ book._1)
    ListMap(book._2.toSeq.sortWith(_._2 > _._2): _*).take(n).foreach(word => println(word._1 + " " + word._2))
  })
}

def idf (books: List[(String, Map[String, Int], List[String])]) :Map[String, Double] ={
  var allWords = List[String]()
  // Getting all of the words in the documents
  books.foreach( b=> {
    println(b._1 + " " + b._2.keys.toList.size)
    allWords = allWords ::: (b._2.keys.toList)
  } )
  println(allWords.size + "_______")
  val idfVal = allWords.distinct.map(word => Math.log(books.length.toDouble / wordInHowManyDocs(word, books)))
  // geting map of words and their idf Values
  (allWords zip idfVal).toMap
}

def tfidf (books: List[(String, Map[String, Int], List[String])] ): Map[String, Map[String, Double]] ={

  // this can be rewritten without uses of var
  val allIdfs = idf(books)
  var allBookMap = Map[String, Map[String, Double]]()
  books.foreach( book=>{
    println(book._1 + "____________")
    // get tf idf of every word in a book
    var tfidfMap = Map[String, Double]()
    book._2.foreach(word=> {
      println(word._1)

      val bookLength = book._2.foldLeft(0)(_+_._2)
      val tf =  word._2.toDouble/bookLength
      tfidfMap += (word._1 -> tf * allIdfs(word._1))
    })
    allBookMap += (book._1 -> tfidfMap)
  })

  allBookMap
}

def wordInHowManyDocs(word: String, books: List[(_, Map[String, Int],_ )]): Int = {

  //this should be done in a map or something
  var howMany = 0
  books.foreach(b => {
    if (b._2.contains(word))
      howMany += 1
  }  )
  howMany
}

/*
def tfidf (books: List[(String, Map[String, Int], List[String])] ): Map[String, Map[String, Double]] ={

  // this can be rewritten without uses of var
  val allIdfs = idf(books)
  var allBookMap = Map[String, Map[String, Double]]()
  books.foreach( book=>{
    // get tf idf of every word in a book
    var tfidfMap = Map[String, Double]()
    book._2.foreach(word=> {

      val bookLength = book._2.foldLeft(0)(_+_._2)
      val tf =  word._2.toDouble/bookLength
      tfidfMap += (word._1 -> tf * allIdfs(word._1))
    })
    allBookMap += (book._1 -> tfidfMap)
  })

  allBookMap
}
*/
