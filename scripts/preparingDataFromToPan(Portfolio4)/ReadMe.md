These scripts help analyze results from a ToPan topic model.

creatingStrongCorrelationPerTopic.sc takes the usable theta table from the portfolio 3 editions. The "tableMaker" function within this script extracts only the scholia within each topic which have a score of 0.9 or greater for that topic. From this, it creates a separate table for each topic, including only these high-scoring scholia. These scholia are sorted by their weights within each topic.

scholiaBreakdown.sc takes a text file which contains the names of all of the files made from creatingStrongCorrelationPerTopic.sc. For each of these files, this script prints out the breakdown of the types of scholia. That is, it tells how many of the scholia are main, interior, intermarginal, etc.

scholiaFreq.sc is very similar to the previous script, but it calculates and prints out the breakdown of all of the scholia in the hmt archive. 
