This set of scripts is designed to prepare a parsed text for topic modeling in a spark notebook environment.

The order of scripts for this set are as follows:

1. MostFrequentWords.sc
2. CreatingStopWords.sc
3. Chunking.sc

The first script requires two parameters. The first is just the parsed text version of the scholia text that was created using the portfolio 2 scripts. The second parameter is just a number representing how many of the most frequent words you want to add to your stop word list. This script just results in a simple text file. 

One has to then look through the result from the first script and separately record in another text file all the words from the most frequent word list you created that you do not want included in the stop word list. This separate text file should only be contained on a single line, and each of these non-stop words should be separated by a comma.

Once the non-stop word list is completed, you can use the second script which finalizes the stop word list. It also takes two parameters. The first parameter is the uncurated stop word list from the previous script, while the second paramter is the list of non-stop words you have just created. This will create a simple text file of your finalized stop word list.

The final script takes, again two parameters. The first is your finalized stop word list, and the second is the parsed text from Portfolio 2. The result of this script is a very large number of individual files that consist of only one scholion each. It is best to put all of these individual chunks into their own folder.
