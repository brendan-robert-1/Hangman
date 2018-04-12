# Hangman

This is a simple Java command line implementation of the popular hangman game. It can either be provided a word on the command line or ommit a word and have 
a random word chosen instead. It attempts to solve the puzzle by guessing words one at a time. The dictionary used seen in the `bin` directory has been reduced to only english words
that have english letters. No '-' are allowed for example, this is for simplicities sake. The program either solves the puzzle by guessing all 
letters in the secret word, or fails by guesssing 6 letters incorrectly. The 6 letter limit aligns with the head, body, 2 arms, and 2 legs of
a traditional game of hangman on paper. An example output of this program for the word elephant is as follows:

```
Word [elephant]
Remaining possible words: 58187

Turn 1 guessed [e] Hit! e _ e _ _ _ _ _ 
Remaining possible words: 233

Turn 2 guessed [r] Miss! Total Misses[1] e _ e _ _ _ _ _ 
Remaining possible words: 233

Turn 3 guessed [t] Hit! e _ e _ _ _ _ t 
Remaining possible words: 14

Turn 4 guessed [n] Hit! e _ e _ _ _ n t 
Remaining possible words: 6

Turn 5 guessed [a] Hit! e _ e _ _ a n t 
Remaining possible words: 3

Turn 6 guessed [c] Miss! Total Misses[2] e _ e _ _ a n t 
Remaining possible words: 3

Turn 7 guessed [l] Hit! e l e _ _ a n t 
Remaining possible words: 2

Turn 8 guessed [p] Hit! e l e p _ a n t 
Remaining possible words: 1

Turn 9 guessed [h] Hit! e l e p h a n t 
Winner!
Word [elephant]
Guessed characters [[p, a, r, c, t, e, h, l, n]]
Misses [2]
Final board [e l e p h a n t ]
```

The current program uses a reductionist algorithm that keeps track of all possible words based on the current board state and index of
correct and hidden characters.

The character that is guessed is based on the subset of remaining possible words and the occurences of each character in this subset.
If 'c' appears 2000 times and 'p' appears 1000 times, the letter c will be selected next for example.
