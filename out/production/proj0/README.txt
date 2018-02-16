This project is a crash course in Java. The NBody program calculates the net
force of gravity on each of N bodies in space to simulate the motion of orbit
around the Sun. By default, there are four bodies (Mercury, Venus, Earth, and
Mars) orbiting the Sun, but modifying the text file data/planets.txt produces
a different number of planets, whose images can be found in the /images
directory.

To run the simulation, run the following commands:
$ javac NBody.java
$ java NBody 157788000.0 25000.0 data/planets.txt

A window will pop up and start the animation. Upon completion, the program
will print the final status of the bodies to standard output, which is used
to track correctness.