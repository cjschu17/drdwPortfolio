/*This script requires a tab-separated file that has a first column with URN identifiers and a second with XML text of HMT-edited scholia.
It isolates the XML, and extracts JUST the text content of the scholia, excluding the reference identifier and lemmata.*/

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

//Using the defined function xmlExtract, one takes the string of the XML and extracts just the text of the scholia.
val scholiaString = xmlString.map( x => xmlExtract(x))

//Realign the text, so that now the URN identifiers align with just the text of the scholia
val tupletoScholiaString = urnIdents zip scholiaString


//Print out the previous vector of tuples into a .tsv format.
for (table <- tupletoScholiaString) {
  println(table._1 + "\t" + table._2)
}
}

//This function requires a string of XML text and extracts just the text
def xmlExtract (s: String): String = {
//Next, make each XML string into parsable XML using the loadString function from the scala.xml library.
val parsableXML = XML.loadString(s)

//Isolate just the TEI <div> element that contains the 'type="comment"' attribute.
//Here, we know that it is the second child of the root <div> element
val commentXML = parsableXML.child(1)

//Extract the text using the defined function `collectText`
val commentText = collectText(commentXML,"")
commentText
}

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
