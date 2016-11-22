import scala.io.Source
import java.io._

@main
def chunking(stopwords: String, text: String) {

  val parsedText = scala.io.Source.fromFile(text).getLines.toVector

  val parsedArray = parsedText.map(_.split("\t")).filter(_.size == 2)
  val urn = parsedArray.map(_(0))
  val scholiaText = parsedArray.map(_(1).split(", "))

  val SW = scala.io.Source.fromFile(stopwords).getLines.toArray


  val filteredArray = scholiaText.map(_.filterNot(SW.contains(_)))
  val filteredString = filteredArray.map(_.mkString(" "))

  require ( urn.size == filteredString.size )

  val filteredText = urn zip filteredString

  var count = 0

  for (f <- filteredText) {

    count = count + 1

    val docName = "scholia" + count + ".tsv"
    val outputFile = new File (docName)
    val printer = new PrintWriter(outputFile)
    printer.write(f._1 + "\t" + f._2)
    printer.close
  }
}
