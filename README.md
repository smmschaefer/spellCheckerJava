# spellCheckerJava
Currently in O(n) speed, will update to Trie or BKTree soon.
Finds the best correctly spelled word match for an entered misspelled word.

# WordGen.java

Takes in a list of english words.
Generates words of the following cases..

people = PEOePlE (random caps)
people = pppeeeple (additional characters)
people = paeple (vowels scrambled)

A word can be generated with 1 or more of these mistakes.  

# Checker.java

Takes in a list of english words.
Takes single input or a file from STDIN and attempts to find the best correct spelled word.
It finds the best fit word by using Levenshtein Distance Algorithm. ( https://en.wikipedia.org/wiki/Levenshtein_distance )
The overall idea is to find the shortest distance from an inputted word (misspelled) and a correctly spelled word. This is done by looking at the number of insertions, deletions, and substitutions made.  

The program currently runs in O(n) using ArrayList to hold the inputted words.  

The next version will most likely use Trie or a BKTree.  I like the idea of using a BKTree to keep the use of Levenshteins Distance Algorithm.  

