# assortedprojects

## Overview
As the title suggests, this is an assortment of personal coding projects ranging from small exercises such as those in the 'Codility & Hackajob Practice Exercises' directory, to more large/complex programs such as the Ratchet & Clank Weapon Game. These are mostly in Java, with a ReactJS version of the Ratchet & Clank Weapon Game and a small core JavaScript Breakout game (in the 'Other Projects' directory) included also.

## Ratchet & Clank Weapon Game
Inspired by games like 'Wordle' & 'Who Are Ya?', this is a guessing game based around my favourite video game franchise, Insomniac's 'Ratchet & Clank' series and the weapons you can unlock in them. The aim is to, within 6 guesses, find the randomly selected 'target' weapon, with each guess showing in how many of 6 chosen categories (Console, Game, Planet/Level, Range, Rate of Fire & Cost (Bolts)) your guess correctly matches the target weapon. I created the Java version first before, more recently, trying to create a more visually appealing version in ReactJS (the Java version uses basic 'swing' package GUIs). Screenshots of example playthroughs on both versions shown below:-

![Screenshot of ReactJS version of Ratchet & Clank Weapon Game example playthrough.](./Ratchet%20&%20Clank%20Weapon%20Game/RCWG-Pics/Screenshot%202023-03-24%20185502.jpg)

![Screenshot of Java version of Ratchet & Clank Weapon Game example playthrough.](/Ratchet%20&%20Clank%20Weapon%20Game/RCWG-Pics/Screenshot%202023-03-24%20185126.jpg)

### UPDATE re: ReactJS Version (25/03/23):-
More weapons added and, more notably, game now playable on mobile with some layout changes made to allow the panels, inputs, info etc. to be shown as clearly as possible (only tested on personal mobile device). Screenshots & further details shown below:-
- Guesses/rows changed to 3X2 grids with guess number clearly labelled & category shown within each panel to fit on narrower screens
- '?' panel changed to image list showing all possible weapon names & matching pictures with target weapon shown in win/loss pop-up since '?' panel likely to be off-screen after 2-3 guesses

<p align="center">
  <img src="/Ratchet%20&%20Clank%20Weapon%20Game/RCWG-Pics/Screenshot_20230325_172434_Chrome.jpg">
  <img src="/Ratchet%20&%20Clank%20Weapon%20Game/RCWG-Pics/Screenshot_20230325_172615_Chrome.jpg">
</p>

### UPDATE re: ReactJS Version (31/03/23):-
Fixed jarring background image transitions, smoother fade in/out effect implemented via css improvement. More weapons added again and small border added to image list on mobile version for neater display of weapon images regardless of number of images (i.e. outline of image list still clearly defined when no. of images dosn't divide evenly into 3).

Possible future improvements/augmentations:-
- Java Version
  - More polished image display
  - Improve overall aesthetics

- ReactJS Version (mobile & browser)
  - Add more games/weapons like on the Java version
  - More polished image display potentially possible here also

## Other Projects
More interesting than the exercise solutions, but not as large/complex as the Ratchet & Clank Weapon Game, these are a mix of small-to-medium games/programs including: Hangman, Tic-Tac-Toe & an Anagram Game all made in Java, as well as the aforementioned Breakout game made in JavaScript. Screenshot of an example (partial) playthrough of the Anagram Game shown below:-

![Screenshot of Anagram Game partial example playthrough.](/Other%20Projects/OP-Pics/Screenshot%202023-03-24%20185931.jpg)

## Codility & Hackajob Practice Exercises
Small programs, solutions to an assortment of programming practice exercises found on the Codility & Hackajob platforms. All of these were done in Java.
