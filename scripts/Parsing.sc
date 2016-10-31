import scala.xml._

@main
def script(s: String) = {
  import scala.io.Source
val scholiaEx = Source.fromFile(s).getLines.toVector

val filteredArray = scholiaEx.map(s => s.split("\t")).filter(_.size.toString.contains("2"))

val justSchol = filteredArray.map( a => a(1))

val wordVector = justSchol.map(_.split(" ").filterNot(_.isEmpty))

val allWords = wordVector.flatten

val filteredWords = allWords.filterNot(_.matches("[A-Za-z0-9]+")).filterNot(_.contains("urn"))

val uniqueWords = filteredWords.groupBy( w => w).map(_._1)

val parsedResults = uniqueWords.map( w => (w,parse(w)) )

for (p <- parsedResults) {
  println(p)
}
}

//This is a series of functons taken from Prof. Smtih's spark notebook about how to parse
def lemmaForEntry (nseq: NodeSeq) = {

  if (nseq.size > 0) {
    nseq(0).text
  } else ""
}

def idForEntry (nseq: NodeSeq) = {

  if (nseq.size > 0) {
      nseq(0).text.replaceFirst("http://data.perseus.org/collections/urn:cite:perseus:grclexent.","")
  } else ""
}

def formatEntry(e: Elem) : String = {

  val uriGroup = e \ "@uri"
  val uri = idForEntry(uriGroup)
  val headWordList = e \\ "hdwd"
  val headWord = lemmaForEntry(headWordList)
  uri + "_" + headWord
}


def parse (s: String) = {
  val baseUrl = "https://services.perseids.org/bsp/morphologyservice/analysis/word?lang=grc&engine=morpheusgrc&word="
  val request = baseUrl + s
  try {
    val morphReply = scala.io.Source.fromURL(request).mkString
      import scala.xml._
    val root = XML.loadString(morphReply)
    val entries = root \\ "entry"

    val lexent = entries.map( e => e match {
      case el: Elem => formatEntry(el)
      case _ => ""

    })
  } catch {
    case exc: java.io.IOException => println("Failed to parse " + s)
  }

lexent
}
