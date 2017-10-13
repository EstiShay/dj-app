# Social Dance DJ Music Manager App

#### By Esti Shay
#### Android project for Epicodus, Oct. 2017
*Category selected:* Tracking and Collecting Objects from an API.

## Description

Android app for social dance DJs to manage the metadata of songs they own or want to acquire. The DJ will be able to search by artist name or song title and retrieve metadata from the MusixMatch API, then add that song to their personal music tracking database and add other metadata relevant to DJ usage:
* Type of dance event the song is appropriate for: Swing (Lindy Hop etc.), Balboa, Blues, Fusion, Salsa, Tango, WCS, Contra, House Party
* Purpose it can be used for (opt): birthday jam, jam circle, exhibition, competition
* Owned or Wish List
* Songs to play before it
* Songs to play after it
* Beats per minute (BPM)
* Personal notes
* Source notes

## Specifications
| Behavior      | Example Input      | Example Output       |
| ------------- | ------------- | ------------- |
| User searches by track name | The Lady is a Tramp | 1. The Lady is a Tramp by Ella Fitzgerald, The Very Best of Ella Fitzgerald, 2001<br>2. The Lady is a Tramp by Frank Sinatra, ... |
| User searches by track name and artist | Title: The Lady is a Tramp <br>Artist: Ella Fitzgerald | 1. The Lady is a Tramp by Ella Fitzgerald, The Very Best of Ella Fitzgerald, 2001<br>2. The Lady is a Tramp by Ella Fitzgerald, Night & Day, 1977 ... |
| User adds record to their collection with notes | Title: The Lady is a Tramp<br>Artist: Ella Fitzgerald<br>Album: The Very Best of Ella Fitzgerald<br>Year: 2001<br>Type of event: Swing dance<br>Purpose: Birthday jam<br>[x]Owned<br>Songs to play before it: TBD<br>Songs to play after it: TBD<br>BPM: 168<br>Personal notes: Has a slow intro | Your music: <br>1. The Lady is a Tramp by Ella Fitzgerald, The Very Best of Ella Fitzgerald, 2001<br><br> Dialogfragment: Title: The Lady is a Tramp<br>Artist: Ella Fitzgerald<br>Album: The Very Best of Ella Fitzgerald<br>Year: 2001<br>Type of event: Swing dance<br>Purpose: Birthday jam<br>[x]Owned<br>Songs to play before it: TBD<br>Songs to play after it: TBD<br>BPM: 168<br>Personal notes: Has a slow intro |

#### Future Development
In the initial version, the Add button will allow users to create a record from scratch and the Search button will lead to the API search. The next iteration of this app would allow for either of those tasks when the Add button is clicked, and the Search button would allow the user to search their database of music metadata.

Wide scale deployment of this app would require the ability to sync with iTunes, Winamp etc. so that an established DJ could import their current music collection without having to search each currently-owned track in MusixMatch.

## Design
Final screenshots go here.

### Wireframing
![Wireframing: start and search pages](/app/src/main/res/drawable/wireframing2.jpg "Diagram of Start and Search pages")
![Wireframing: search results and your notes pages](/app/src/main/res/drawable/wireframing1.jpg "Diagram of search results and form to add personal notes")

## Setup/Installation Requirements
* Clone the repo
* Sign up for a free API key from MusixMatch

### Technologies Used
* Java
* AndroidStudio
* Firebase

### Known Bugs
Wireframing images display in Markdown Preview in Atom but do not display in README on GitHub. If viewing this README on GitHub, please view the images in the drawable folder: https://github.com/EstiShay/dj-app/tree/master/app/src/main/res/drawable

### License

Licensed under GPL

Copyright &copy; 2017 Esti Shay
