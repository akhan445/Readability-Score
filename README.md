# Readability Score

This program uses a scientific approach to determine the difficulty level (readability) of a text document. This program uses the template design pattern to accomplish this task.

The Source files for this project can be found by navigating to:
      
      Readability Score/task/src/readability/
      
This Directory contains 2 files:

__Main.java__

Contains the logic of the Readability Score program. Creates a new instance of ReadabilityScore and calls .determineReadability() method to run the program. 

__ReadabilityScore.java__

This file contains the template for running the program.

Under the determineReadability() method you can find the important parts to determining the score of a piece of text. This method encapsulates the entire program into 3 parts:

  1. analyzeText() *[General, same for every program]*
     - Takes a piece of text and analyzes it. Breaks it down into sentences, words, characters, syllables and polysyllables.
  
  2. displayResults() *[General, same for every program]*
     - Displays the results collected from analyzing the text.
  
  3. displayReadability(String args) *[Specific, different for each instance]*
     - Takes an argument entered by the user to determine which test to use to calculate the readability of the text.**
     - 4 methods were used to calculate the readability score:
     
            a) Automated Readability Index (ARI)
        
            b) Flesch-Kincaid readability test (FK)
        
            c) Simple Measure of Gobbledygook (SMOG)
        
            d) Coleman-Liau index (CL)
     
     
 *__**NOTE: For the sake of uncomplicating the project all types of scores were implemented in this Class. The reason for this was because the same index was being used to evaluate what age the text is appropriate for (in real different some scores have different age indexes) and some of the approaches have additional calculations which were not implemented in this project. So to simplify things only functions were created wihtin one class instead of various Class implementations which is usually executed in the template method.__*
