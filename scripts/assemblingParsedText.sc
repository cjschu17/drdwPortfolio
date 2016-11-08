import scala.io.Source

val text = Source.fromFile("drdwPortfolio/editions/Scholia/scholiaExtracted.tsv").getLines.toVector

val array = text.map(_.split("\t")).filter(_.size == 2)

val tuple = array.map( arr => (arr(0),arr(1).replaceAll( "[\\{\\}\\\\>,\\[\\]\\.·⁑:\"·]+","").split(" ").filterNot(_.isEmpty).filterNot(_.matches("[A-Za-z0-9]+"))))

val parseResults = Source.fromFile("hmt-analytics/datasets/form-id-triples.tsv").getLines.toVector

val parsedArrays = parseResults.map(_.split("\t"))

val replacements = replacement(tuple) 

def replacement(e: Vector[(String, Array[String])]): Vector[String] = {
var line = ""
for (t <- e) {
  for (word <- t._2){
    for (p <- parsedArrays) {
    if (word == p(0)) {
      line += t._1 + "\t" + p(2) + "\n"
    }

    }
  }
}
line.split("\n").toVector
}
