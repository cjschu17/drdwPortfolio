import scala.io.Source

@main
def zipf(f: String) {

val rawText = Source.fromFile(f).getLines.mkString

val charCount = rawText.size

val sentCount = rawText.split("\\.").size

val words = rawText.split("\\W").filter(_.nonEmpty)

val wordCount = words.size

val uniqWordCount = words.groupBy(w => w).size

println("The file, " + f + ", has " +charCount + " characters, " + wordCount + " words, " + uniqWordCount + " unique words, and " +sentCount + " sentences." )
}
