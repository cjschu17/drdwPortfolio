/*This script is intended to create a final version of a parsed text of Iliad scholia.
It requires a version of the Iliad scholia that has already been extracted from its original
XML format, and is now in a two-column format of urn identifier and scholia text. In addition,
this script also requires that one already has run morphological analysis on the scholia text, and therefore
has a file containing the morphological parsing for every word in the scholia corpus.

Usage:

    amm assemblingParsedText.sc SCHOLIATEXT, UNIQUEWORDSPARSED

*/

import scala.io.Source


case class IdTriple (surface: String, pos: String, lemma: String)

@main
def assemble(f: String, data: String) {
  /*These lines import the file containing unparsed scholia text
  and reduces the text to only contain Greek words, void of punctuation and extraneous
  XML mark-up that might have been left behind in the extraction process.*/
  val text = Source.fromFile(f).getLines.toVector
  val array = text.map(_.split("\t")).filter(_.size == 2)
  // Tuple of URNs and Lists of words
  val tupleNoPunc = array.map( arr => (arr(0),arr(1).replaceAll( "[\\{\\}\\\\>,\\[\\]\\.·⁑:\"·]+","").split(" ")))
  val tupleOfWords = tupleNoPunc.map( tup => (tup._1,tup._2.filterNot(_.isEmpty).filterNot(_.matches("[A-Za-z0-9]+"))))

  /*This prepares the data from the morphological parser, read from 3 tab-delimited columns.*/
  val parseResults = Source.fromFile(data).getLines.toVector
  // A list of 3-element Arrays
  val parsedArrays = parseResults.map(_.split("\t"))

  val tripleObjects = parsedArrays.map(array => IdTriple(array(0), array(1), array(2)) )

  //This pairs every word in the unparsed corpus with its parsed counterpart
  //While keeping each word associated with its respective URN identifier
  val urnToParse = replacement(tupleOfWords,tripleObjects)

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

def tripleForString(s: String, triples: Vector[IdTriple] ): Vector[IdTriple] = {
    val resultList = triples.filter(_.surface == s)
    if (resultList.size > 0) {
      resultList
    } else {
      val oneEntry = IdTriple(s,"unknown",s)
      Vector(oneEntry)
    }
}


/**
* @param wdLists A Tuple of URNs and Lists of words
* @param tripleList A List of 3-element Arrays
* @returns A Vector of Strings in output format of urn + tab + analysis
*/
def replacement(wdLists: Vector[(String, Array[String])],  tripleList: Vector[IdTriple]) = {
  var line = ""


  for (wdList <- wdLists) {
    val urn = wdList._1
    val words = wdList._2
    for (word <- words) {

      for (answer <- tripleForString(word,tripleList) ) {
          line += urn + "\t" + answer.lemma + "\n"
      }
    }
  }
  line.split("\n").toVector
}
