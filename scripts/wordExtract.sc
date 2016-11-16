import scala.io.Source

@main
def wordExtract(f: String) {
val corpus = Source.fromFile(f).getLines.toVector

val array = corpus.map(_.split("[_,]"))

val words = array.map(arr => arr(1))

for (w <- words) {
  println(w + ",")
}
}
