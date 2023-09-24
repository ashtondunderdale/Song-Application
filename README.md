# Object Oriented Song Application

## A fully funcitonal music management project written in Java, providing a user-friendly CLI interface for managing and interacting with a collection of songs.

This primary purpose of this code is to provide an interface to the user, allowing them to add and remove songs, as well as view the songs within the program. The code was written with the intention of
utilising the following programming techniques and styles: 

- Object Oriented Programming (a paradigm that involves the use of objects to contain data and code)
- Modular Programming (the separation of functionality into independant modules or methods)

#### Tecnologies and tools used

- **Java**, strong oop capabilities
- **GitHub**, for version control and code organisation

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

Since the program was expected to be re-used within the same runtime (the user may want to add or remove songs multiple times) it was clear the best way to structure the program was with the use of methods
each with a set purpose and function. This way, the methods could be called repeatedly, therefore avoiding duplicate code (following the principles of DRY(Don't repeat yourself)). With the idea of
modularity in mind, a method was laid out for each major function in the program and then some additional smaller methods.

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

After laying out the major foundational methods the ```Song``` class was ready to be initialised and then tested. The attributes ```songName```, ```artistName```, ```durationString```, and ```playCount``` were added to the ```Song``` class. The design decision to make ```durationString``` a ```String``` type rather than ```int``` was because otherwise the user would have to enter the duration in seconds, which would then be converted into minutes ```(x /= 60)``` and the remainder of seconds added on to form the final string ```(y %= 60)```. This was unnessecary logic as it is purely for visual purposes since the song is not being played.

### The ```viewSong()``` Method

Upon calling the ```viewSong()``` method, another method: ```returnEmptySongStatement()``` will be called. This is to check whether the ```ArrayList``` that stores the songs is empty or not, if it is then the code will alert the user that to use the features, you must first have atleast one song to modify or view. The code will then run a for each loop: ```for (Song song : songs)``` that will iterate through each item in ```songs``` until all have been listed.

### The ```viewTopSongs()``` Method

Upon calling the ```viewTopSong()``` method, a new ```ArrayList``` will be initialised, this array will store the top ten songs in terms of ```playCount``` in descending order.











