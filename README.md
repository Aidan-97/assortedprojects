# assortedprojects

## Overview
As the title suggests, this is an assortment of personal coding projects ranging from small exercises such as those in the 'Codility & Hackajob Practice Exercises' directory, to more large/complex programs such as the Ratchet & Clank Weapon Game. These are mostly in Java, with a ReactJS version of the Ratchet & Clank Weapon Game and a small core JavaScript Breakout game (in the 'Other Projects' directory) included also.

## Ratchet & Clank Weapon Game
Inspired by games like 'Wordle' & 'Who Are Ya?', this is a guessing game based around my favourite video game franchise, Insomniac's 'Ratchet & Clank' series and the weapons you can unlock in them. The aim is to, within 6 guesses, find the randomly selected 'target' weapon, with each guess showing in how many of 6 chosen categories (Console, Game, Planet/Level, Range, Rate of Fire & Cost (Bolts)) your guess correctly matches the target weapon. I created the Java version first before, more recently, trying to create a more visually appealing version in ReactJS (the Java version uses basic 'swing' package GUIs). Screenshots of example playthroughs on both mobile & browser for most recently updated (15/07/2025) ReactJS version shown below:-

<p align="center">
  <img src="/Ratchet%20&%20Clank%20Weapon%20Game/RCWG-Pics/Screenshot%2025-07-15%000505.png">
  <img src="/Ratchet%20&%20Clank%20Weapon%20Game/RCWG-Pics/Screenshot%2025-07-15%003834.png">
</p>

### UPDATE re: ReactJS Version (15/07/25):-
- Browser display changed to include image list (with small visual adjustments/improvements) that was originally just shown on mobile
- Horizontal flipping animation added to panels for guesses, with a small delay added before the 'game won' pop-up so user can first see full animation revealing 6 green panels for improved UX
- Improved scalability & readability in code. Previously, only the 'Guess' component (i.e. one row of 6 panels) was imported into 'App.js' from a separate file, now the ImageList component and the large arrays containing weapon names, stats, and images are separated also
- More detailed, better formatted information in the Help pop-up
- More weapons added

## Other Projects
More interesting than the exercise solutions, but not as large/complex as the Ratchet & Clank Weapon Game, these are a mix of small-to-medium games/programs including: Hangman, Tic-Tac-Toe & an Anagram Game all made in Java, as well as the aforementioned Breakout game made in JavaScript. Screenshot of an example (partial) playthrough of the Anagram Game shown below:-

![Screenshot of Anagram Game partial example playthrough.](/Other%20Projects/OP-Pics/Screenshot%202023-03-24%20185931.jpg)

### Update (10/04/23):-
New ReactJS mini-project added: 'Mock PL Transfer Window Info'. A list of tables displaying mock Premier League transfer window ins & outs, with this mock info fetched from a json file created with 'json-server'. Done as a small personal challenge in rendering objects via iterating through json data, so currently visually somewhat basic. Possible future updates would mostly include improved visuals (e.g. more clear, visually appealing version of net spend chart).

## Codility & Hackajob Practice Exercises
Small programs, solutions to an assortment of programming practice exercises found on the Codility & Hackajob platforms. All of these were done in Java.
