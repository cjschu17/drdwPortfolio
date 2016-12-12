import scala.io.Source
import java.io._

@main
def rearrangingData(sourceFile: String) {

  val rawTable = scala.io.Source.fromFile(sourceFile).getLines.toVector

  val rawTableArrays = rawTable.map(_.split("\t"))

  for (a <- rawTableArrays) {
  require (a.size == 18)
  }

  val numbers: Array[Int] = Array(3,4,5,6,7,8,9,10,11,12,13,14,15,16,17)

  for (n <- numbers) {

    val fileCount = n - 2
    val bestScores = tableMaker(rawTableArrays,n)
    val fileName = "table" + fileCount + ".tsv"
    val outputFile = new File(fileName)
    val printer = new PrintWriter(outputFile)
    printer.write(bestScores)
    printer.close
    println(bestScores)
  }
}


def tableMaker(arrays: Vector[Array[String]], integer: Int ): String = {

  var line = ""
  for (arr <- arrays) {


    if (arr(integer).matches("0.9[0-9]+")) {
      line += arr(1) + "\t" + arr(integer) + "\n"
    }


  }
  val finalVector = line.split("\n").toVector
  val sorted = finalVector.map(_.split("\t")).sortBy(_(1))
  val reassembledText = sorted.map(_.mkString("\t")).mkString("\n")
  reassembledText
}
