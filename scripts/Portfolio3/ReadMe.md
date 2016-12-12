This set of scripts is designed to prepare a parsed text for topic modeling in a spark notebook environment.

The order of scripts for this set are as follows:

1. MostFrequentWords.sc
2. CreatingStopWords.sc
3. Chunking.sc

The first script requires two parameters. The first is just the parsed text version of the scholia text that was created using the portfolio 2 scripts. The second parameter is just a number representing how many of the most frequent words you want to add to your stop word list. This script just results in a simple text file. 

One has to then look through the result from the first script and separately record in another text file all the words from the most frequent word list you created that you do not want included in the stop word list. This separate text file should only be contained on a single line, and each of these non-stop words should be separated by a comma.
