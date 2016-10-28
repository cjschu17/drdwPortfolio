/*This script requires a tab-separated file that has a first column with URN identifiers and a second with XML text of HMT-edited scholia.
It isolates the XML, and attempts to extract JUST the text content of the scholia, excluding the reference identifier and lemmata.*/

import scala.io.Source
import scala.xml.XML


@main
def extract(f: String) {

//First step is to simply import the .tsv file
val lines = Source.fromFile(f).getLines.toVector

//Isolate the identifiers
val urnIdents = lines.map( v => v.split("\t") ).map( x => x(0) )

//Next, split each string in the vector on a tab, and isolate the XML from each resulting vector
val xmlString = lines.map( v => v.split("\t") ).map( x => x(1) )

//Next, make each XML string into parsable XML using the loadString function from the scala.xml library.
val parsableXML = xmlString.map( string => XML.loadString(string) )

//Isolate just the TEI <div> element that contains the 'type="comment"' attribute.
//Here, we know that it is the second child of the root <div> element
val comments = parsableXML.map( node => node.child(1) )

//Extract the text!
val scholiaString = comments.map( c => collectText(c,""))

val tupletoScholiaString = urnIdents zip scholiaString

val wordVector = scholiaString.map(_.split(" ").filterNot(_.isEmpty))

val tupleUrnToWords = urnIdents zip wordVector

for (table <- tupletoScholiaString) {
  println(table._1 + "\t" + table._2)
}
}

/*val allWords = wordVector.flatten

val uniqueWords = allWords.filterNot(_.contains("urn")).groupBy( w => w).map(_._1)
}*/

def collectText(n: xml.Node, s: String): String ={
  var txt = s
  n match {
    case t: xml.Text => {txt = txt + t.text}
    case el: xml.Elem => {
      for (ch <- el.child) {
        txt += collectText(ch, s)}
      }
  }
  txt
}
