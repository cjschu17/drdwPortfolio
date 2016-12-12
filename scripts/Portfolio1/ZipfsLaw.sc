#!/usr/bin/env amm

@main
def zipf(f: String) {
    println("This is the file we will analyze: " + f)


import scala.io.Source

val lines = Source.fromFile(f).getLines.toVector

val words = lines.mkString.split("\\W").filter(_.nonEmpty)

val wordfreqs = words.groupBy(w => w).map { case (k,v) => (k,v.size)}

val sorted = wordfreqs.toSeq.sortBy(_._2)

for (kvpair <- sorted) {
  println(kvpair)
}
println("This is the file we will analyze: " + f)
}
