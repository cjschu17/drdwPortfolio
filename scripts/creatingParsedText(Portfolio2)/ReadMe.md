This set of scala scripts is designed to go from a 2-column edition of the raw XML edition of the Venetus A scholia and convert it into a 2-column parsed edition of the Venetus A scholia. In both editions, the first column contains the URN of the scholion, and the second column contains the content of the scholia.

The order of the scripts in order to create a parsed text is as follows:

1. scholiaTextExtract.sc
2. parseTextBy10s.sc
3. extractMorpheusPoS.sc
4. assemblingParsedText.sc

The first script just requires an input of a two-column, .tsv file, in which the first column consists of just the URN of a scholion, and the second column contains the XML content of that scholion. The result will be a version of the text that is still a two-column .tsv file that consists of a URN in the first column and a version of the scholia in the second column that is unparsed but devoid of any XML markup.

The second script requires the output from the previous script. This script breaks up the text of the scholia and finds all the unique lexical tokens. It then parses them and prints out the results in groups of 10. The results are in another two-column format, though this time the first column is with word being parsed, and the second column is the XML result from Morpheus containing the results of the parsing. This script will create many, many different files, and it is necessary to concatenate all the files after completion in order that all the parses remain in just one location.

The third script requries just the concatenated result from the previous script. The result of this script is a three-column .tsv file which contains the surface form of a word in the first column, its part of speech in the second, and its dictionary form or lemma in the third column.

The final script requires two parameters. The first is the result of the first script, i.e. a version of the Iliad scholia in a two-column .tsv file where the first column is the scholia URN and the second column is the text extracted from its original XML. The second parameter is the three-column .tsv from the previous script. This final script will result in a final, parsed version of the scholia!
