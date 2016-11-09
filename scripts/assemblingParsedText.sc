/*This script is intended to create a final version of a parsed text of Iliad scholia.
It requires a version of the Iliad scholia that has already been extracted from its original
XML format, and is now in a two-column format of urn identifier and scholia text. In addition,
this script also requires that one already has run morphological analysis on the scholia text, and therefore
has a file containing the morphological parsing for every word in the scholia corpus. */

import scala.io.Source

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

@main
def assemble(f: String, data: String) {

/*These lines import the file containing unparsed scholia text
and reduces the text to only contain Greek words, void of punctuation and extraneous
XML mark-up that might have been left behind in the extraction process.*/
val text = Source.fromFile(f).getLines.toVector
val array = text.map(_.split("\t")).filter(_.size == 2)
val tupleNoPunc = array.map( arr => (arr(0),arr(1).replaceAll( "[\\{\\}\\\\>,\\[\\]\\.·⁑:\"·]+","").split(" ")))
val tupleOfWords = tupleNoPunc.map( tup => (tup._1,tup._2.filterNot(_.isEmpty).filterNot(_.matches("[A-Za-z0-9]+"))))

/*This prepares the data from the morphological parser.*/
val parseResults = Source.fromFile(data).getLines.toVector
val parsedArrays = parseResults.map(_.split("\t"))

//This pairs every word in the unparsed corpus with its parsed counterpart
//While keeping each word associated with its respective URN identifier
val urnToParse = replacement(tupleOfWords)

//Creates a map of URN identifer -> Vector of Parsed entities
val newlyParsedText = urnToParse.map(_.split("\t")).groupBy(w => w(0)).map{ case (k,v) => (k,v.map(e => e(1)))}

//Extracts keys and values and assembles them into a tuple.
val urns = newlyParsedText.keysIterator.toVector
val parsedText = newlyParsedText.valuesIterator.toVector
val finalParsedText = urns.zip(parsedText)

for (c <- finalParsedText) {
  println(c._1 + "\t" + c._2)
  }
}
