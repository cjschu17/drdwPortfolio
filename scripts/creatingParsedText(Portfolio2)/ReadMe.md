This set of scala scripts is designed to go from a 2-column edition of the raw XML edition of the Venetus A scholia and convert it into a 2-column parsed edition of the Venetus A scholia. In both editions, the first column contains the URN of the scholion, and the second column contains the content of the scholia.

The order of the scripts in order to create a parsed text is as follows:

1. scholiaTextExtract.sc
2. parseTextBy10s.sc
3. extractMorpheusPoS.sc
4. assemblingParsedText.sc
