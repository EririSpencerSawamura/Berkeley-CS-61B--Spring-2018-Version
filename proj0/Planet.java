public class Planet {
    //The position in the x-coordinate.
    public double xxPos;
    // The position in the y-coordinate.
    public double yyPos;
    // The velocity in the x-coordinate.
    public double xxVel;
    // The velocity in the y-coordinate.
    public double yyVel;
    // The mass of the planet.
    public double mass;
    // The name of the file that corresponds to the image that depicts the planet.
    public String imgFileName;
    // The gravitational constant.
    private static final double G = 6.67e-11;

    /**
     * The default constructor.
     */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    /**
     * The constructor that copies a planet.
     */
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    /**
     * The method that computes the distance from the current planet to the planet p.
     */
    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        double r2 = dx * dx + dy * dy;

        return Math.sqrt(r2);
    }

    /**
     * The method that computes the force exerted on the current planet from the planet p.
     */

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);

        return G * this.mass * p.mass / (r * r);
    }

    /**
     * The method that computes the force exerted on the current planet from the planet p on the x-axis.
     */
    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double r = this.calcDistance(p);

        return this.calcForceExertedBy(p) * dx / r;
    }

    /**
     * The method that computes the force exerted on the current planet from the planet p on the y-axis.
     */
    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        double r = this.calcDistance(p);

        return this.calcForceExertedBy(p) * dy / r;
    }

    /**
     * The method that takes in an array of Planets and calculate the net X force
     * exerted by all planets in that array upon the current Planet.
     */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0;
        for (Planet p: allPlanets) {
            if (!this.equals(p)) {
                netForceX += this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    /**
     * The method that takes in an array of Planets and calculate the net Y force
     * exerted by all planets in that array upon the current Planet.
     */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0;
        for (Planet p: allPlanets) {
            if (!this.equals(p)) {
                netForceY += this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    /**
     * The method that determines how much the forces exerted on the planet will cause that planet to accelerate
     */
    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += aX * dt;
        this.yyVel += aY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    /**
     * The method that draws a planet.
     */
    public void draw() {
        String filename = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, filename);
    }
}
