#Will it Launch?!

![Will it Launch?!](http://i.imgur.com/EROGSUs.png)

Will it launch is our attempt at a flight control style multiplayer game. The object of the game is to successfully work as a team to monitor the flight systems of a rocket just before launch and decide if the rocket is safe to launch. Time is critical but you can't sacrifice the safety of the astronauts!

The flight controller views include: 

FIDO
Launch angle (L/R/U/D)
Compensate that for wind speed/direction to ensure it goes up?

SURGEON
Heart rate - tranquillise/steroids

EECOM
Cryogenic levels (up/down)
Cabin pressure (up/down)
Generator/battery power (?)

GNC
Engine temperature
Fuel flow - make sure it doesn't 'stall'

CONTROL
Bird scare (beep)
Countdown? (trigger/reset etc?)

##Team 
Our team consists of 5 members, three of which have come to previous space apps competitions and two newbies!
* Ben Oxley @b3noxley
* Adam Grieg @randomskk
* Tom Dalby
* Andrew Cowan
* Marcello Seri

##How we did it
For a go no-go game, the temptation to program it in Google's Go language was too strong (at least for Adam). The backend server is written in Go and provides a websockets interface for our frontend GUI which is written in Java and JavaFX. 

We have heavily used the jave-websockets library, jfxtras and the excellent Enzo controls library.
