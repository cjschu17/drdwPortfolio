import scala.io.Source

@main
def stopwords(freq: String, nonstopwords: String) {

  val uncuratedSW = scala.io.Source.fromFile(freq).getLines.toArray
  val totalNum = uncuratedSW.size

  val nonSW = scala.io.Source.fromFile(nonstopwords).getLines.mkString.split(",")
  val numTakenOut = nonSW.size

  val finalSW = uncuratedSW.filterNot(nonSW.contains(_))

  require (finalSW.size == totalNum - numTakenOut)

  for (word <- finalSW) {
    println(word)
  }
}
