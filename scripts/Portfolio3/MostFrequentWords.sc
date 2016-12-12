#!/usr/bin/env amm

@main
def zipf(f: String, num: Int) {

	import scala.io.Source

	val lines = Source.fromFile(f).getLines.toVector

	val scholiaLines = lines.map(_.split("\t")).map( w => w(1))

	val words = scholiaLines.map(_.split(", ")).flatten

	val wordfreqs = words.groupBy(w => w).map { case (k,v) => (k,v.size)}

	val sorted = wordfreqs.toSeq.sortBy(_._2).toVector

	val mostFreqWords = sorted.takeRight(num).map(_._1)

	for (kvpair <- mostFreqWords) {
		println(kvpair)
	}
}
