public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	/* G == 6.67 * 10 ^ -11 */
	private static final double G = (6.67 * Math.pow(10, -11)); 

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;

		double rSquared = (dx * dx) + (dy * dy);
		double r = Math.sqrt(rSquared);
		return r;
	}

	public double calcForceExertedBy(Planet p) {
		double r = calcDistance(p);

		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;

		double force = (G * this.mass * p.mass / (r * r));
		return force;
	}

	public double calcForceExertedByX(Planet p) {
		double r = calcDistance(p);

		double dx = p.xxPos - this.xxPos;

		double force = calcForceExertedBy(p);
		double forceAlongX = force * dx / r;

		return forceAlongX;

	}

	public double calcForceExertedByY(Planet p) {
		double r = calcDistance(p);

		double dy = p.yyPos - this.yyPos;

		double force = calcForceExertedBy(p);
		double forceAlongY = force * dy / r;

		return forceAlongY;
	}

	public double calcNetForceExertedByX(Planet[] pa) {
		double netForce = 0;
		for (Planet p : pa) {
			if ( ! this.equals(p)) {
				netForce += this.calcForceExertedByX(p);
			}
		}
		return netForce;
	}

	public double calcNetForceExertedByY(Planet[] pa) {
		double netForce = 0;
		for (Planet p : pa) {
			if ( ! this.equals(p)) {
				netForce += this.calcForceExertedByY(p);
			}
		}
		return netForce;
	}

	public void update(double time, double xForce, double yForce) {
		/* Find acceleration. */
		double accX = xForce / mass;
		double accY = yForce / mass;

		/* Calculate and update new velocities. */
		xxVel = xxVel + (time * accX);
		yyVel = yyVel + (time * accY);

		/* Calculate and update new positions. */
		xxPos = xxPos + (time * xxVel);
		yyPos = yyPos + (time * yyVel);
	}

	public void draw() {
		/* Draw the planet on its position. */
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}
