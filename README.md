# Object Oriented Song Application

## A fully funcitonal music management project written in Java, providing a user-friendly CLI interface for managing and interacting with a collection of songs.

This primary purpose of this code is to provide an interface to the user, allowing them to add and remove songs, as well as view the songs within the program. The code was written with the intention of
utilising the following programming techniques and styles: 

- Object Oriented Programming (a paradigm that involves the use of objects to contain data and code)
- Modular Programming (the separation of functionality into independant modules or methods)

#### Tecnologies and tools used

- Java, strong oop capabilities
- GitHub, for version control and code organisation

### Primary Features

- Add songs
- Remove songs
- View all songs
- View top ten most played songs

### Additional Features

- x
- x
- x

# Development
## Initial Concepts

Since the program was expected to be re-used within the same runtime (the user may want to add or remove songs multiple times) I assumed the best way to structure the program was with the use of methods
each with a set purpose and function. This way, the methods could be called repeatedly, therefore avoiding duplicate code (following the principles of DRY(Don't repeat yourself)). With the idea of
modularity in mind, I laid out a method for each major function in the program and then some additional smaller methods.

#### Major Methods

- ```addSong()```
- ```removeSong()```
- ```viewSongs()```
- ```viewTopSongs()```

#### Minor Methods

- ```ProcessMenuChoice()``` -> calls ```getUserMenuChoice()```
- ```getUserMenuChoice(```) -> calls ```displayMenu()```, gets user input for menu choice, returns to ```processMenuChoice()```
- ```displayMenu()``` -> displays menu
- ```exitApplication()``` -> exits upon user input

- ```returnToMenuStatement()``` -> for a recursive message telling the user to "press enter to go back to menu"
- ```returnEmptySongStatement()``` -> for a recursive message telling the user to "add songs before accessing features"


#### Program Flow
