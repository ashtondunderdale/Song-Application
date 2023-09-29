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

## Major Methods

### The ```main()``` method

The program entry point. When this is called, 10 pre set song objects will be added to an ```ArrayList``` called ```songs```. Then the first method is called: ```processMenuChoice()```.


### The ```viewSong()``` method

Upon calling the ```viewSong()``` method, another method: ```returnEmptySongStatement()``` will be called. This is to check whether the ```ArrayList``` that stores the songs is empty or not, if it is then the code will alert the user that to use the features, you must first have atleast one song to modify or view. The code will then run a for each loop: ```for (Song song : songs)``` that will iterate through each item in ```songs``` until all have been listed.


### The ```viewTopSongs()``` method

Upon calling the ```viewTopSong()``` method, a new ```ArrayList``` will be initialised, this array will store the top ten songs in terms of ```playCount``` in descending order. 


### The ```addSong()``` method

Upon calling the ```addsong()``` method the user will be prompted to enter the name of the song: ```songName``` and the name of the artist: ```artistName``` - these are not validated as names of things can technically use any characters and be ( within limits ) any length.

The final attributes of the class are playCount and duration - it would be a strange and impractical design decision to make the user input the duration and playCount of the song themselves, as this would usually be looked up in a database by the program or have a pre set list of songs to choose from. Because of this, I decided to make those randomly generated as a section of placeholder logic.


### The ```removeSong()``` method

Upon calling the ```removeSong()``` method the user will be prompted to enter the name of the song that would like to remove. A new ```ArrayList``` is created called ```songsToRemove```. This will hold the name of the songs that the user would like to remove. A loop will be initialised that will iterate through each item of the songs and check it against what was just added to ```songsToRemove```. If a match is found, then it will be added to ```songsToRemove```, then the array will be emptied, removing the song.


## Minor Methods

### ```processMenuChoice()```

Calls a method that starts an infinite menu loop that runs ```getUserMenuChoice()```. Once the menu choice has been aquired it will go back to the ```processMenuChoice()``` method and execute a switch statement which will lead to the major methods.

### ```getUserMenuChoice()```

Gets the user menu choice from input using and validates it as one of the menu choices within a while loop. Outputs error message if not valid menu choice.

### ```displayMenuChoice()```

Displays a menu to the user in the form of a block string. 

### ```exitApplication()```

Is called when the user enters "0" in the menu ( the exit option ).

### ```returnToMenuStatement()```

Is called when the user needs to be prompted to "go back to the menu" - this is to prevent writing this out a lot of times within the program.

### ```returnEmptySongStatement()```

Outputs a message to the user informing them that there are no songs stored. Is called when the user tries to pick a menu option that involves the altering of a song when there are no songs to alter.







