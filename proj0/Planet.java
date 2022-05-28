public class Planet {
    /** The position in the x-coordinate. */
    public double xxPos;
    /** The position in the y-coordinate. */
    public double yyPos;
    /** The velocity in the x-coordinate. */
    public double xxVel;
    /** The velocity in the y-coordinate. */
    public double yyVel;
    /** The mass of the planet. */
    public double mass;
    /** The name of the file that corresponds to the image that depicts the planet. */
    public String imgFileName;
    /** The gravitational constant. */
    private static final double G = 6.67e-11;

    /** The default constructor. */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** The constructor that copies a planet. */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /** The method that computes the distance from the current planet to the planet p. */
    public double calcDistance(Planet p) {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        double r2 = dx * dx + dy * dy;
        return Math.sqrt(r2);
    }

    /** The method that computes the force exerted on the current planet from the planet p. */
    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        return G * mass * p.mass / (r * r);
    }

    /** The method that computes the force exerted on the current planet from the planet p on the x-axis. */
    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        double r = calcDistance(p);
        return calcForceExertedBy(p) * dx / r;
    }

    /** The method that computes the force exerted on the current planet from the planet p on the y-axis. */
    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        double r = calcDistance(p);
        return calcForceExertedBy(p) * dy / r;
    }

    /** The method that takes in an array of Planets and calculate the net X force
     * exerted by all planets in that array upon the current Planet. */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0;
        for (Planet p: allPlanets) {
            if (!this.equals(p)) {
                netForceX += calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    /** The method that takes in an array of Planets and calculate the net Y force
     * exerted by all planets in that array upon the current Planet. */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0;
        for (Planet p: allPlanets) {
            if (!this.equals(p)) {
                netForceY += calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    /** The method that determines how much the forces exerted on the planet will cause that planet to accelerate */
    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    
}
