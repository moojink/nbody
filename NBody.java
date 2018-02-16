/**
 * 	Draws an animation of four bodies (Mercury, Venus, Earth, and Mars)
 *	orbiting the Sun while tugging on each other with gravity. The orbits are
 *	circular for simplicity. Also prints the final state of the bodies to
 *	stdout when the animation completes.
 *  @author Moo Jin Kim
 */
public class NBody {
	public static void main(String[] args) {
		/* Collect all needed input. */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double universeRadius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		/* Set universe radius. */
		StdDraw.setScale(-universeRadius, universeRadius);

		/* Clear screen to white. */
		StdDraw.clear();

		/* Set background picture. */
		StdDraw.picture(0, 0, "images/starfield.jpg", universeRadius*2,
						universeRadius*2);

		/* Draw all planets. */
		for (Planet p : planets) {
			p.draw();
		}

		/* Animate the universe. */
		double time = 0;	// The universe time will run from 0 to T.
		while (time < T) {
			double[] xForces = new double[5];
			double[] yForces = new double[5];

			/* Calculate net forces for each planet. */
			for (int i = 0; i < 5; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for (int i = 0; i < 5; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			/* Reset canvas and redraw pictures. */
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg", universeRadius*2,
							universeRadius*2);
			for (Planet p : planets) {
				p.draw();
			}
			StdDraw.show(10);
			time += dt;
		}

		/* Print final output. */
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", universeRadius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
	   					  planets[i].xxPos, planets[i].yyPos,
	   					  planets[i].xxVel,planets[i].yyVel, planets[i].mass,
                          planets[i].imgFileName);
		}		
	}

	public static double readRadius(String filename) {
		In filereader = new In(filename);
		if (filereader.exists()) {
			try {

				/* Throw out the first double in the text file. */
				filereader.readDouble();

				/* The second double is the radius. */
				return filereader.readDouble();

			} catch (Exception ex) {
				System.out.println("Exception thrown while reading radius.");
				ex.printStackTrace();
				return 0;
			}
		}
		else return 0;
	}

	public static Planet[] readPlanets(String filename) {
		In filereader = new In(filename);
		int numberOfPlanets = filereader.readInt();
		Planet[] pa = new Planet[numberOfPlanets];

        /* Throw out newlines until we get to planet info. */
        filereader.readLine();
        filereader.readLine();

		/* Read the planets. */
		for (int i = 0; i < numberOfPlanets; i++) {
			/* Collect data per line. */
			double xPos = filereader.readDouble();
			double yPos = filereader.readDouble();
			double xVel = filereader.readDouble();
			double yVel = filereader.readDouble();
			double mass = filereader.readDouble();
			String image = filereader.readString();

			/* Construct new Planet. */
			pa[i] = new Planet(xPos, yPos, xVel, yVel, mass, image);
		}

		return pa;
	}
}
