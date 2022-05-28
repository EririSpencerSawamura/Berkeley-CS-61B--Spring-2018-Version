public class NBody {
    /**
     * The method that returns a double corresponding to the radius of the universe in that file.
     */
    public static double readRadius(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();

        return radius;
    }

    /**
     * The method that returns an array of Planets corresponding to the planets in the file.
     */
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[num];

        for (int i = 0; i < num; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();

            planets[i] = new Planet(xP, yP, xV, yV, m, img);
        }

        return planets;
    }

    /**
     * The main method.
     */
    public static void main(String[] args) {
        /* Collect all needed input.
         * These two lines come from https://blog.csdn.net/DreamWeaver_zhou/article/details/74356000. */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        /* Create an animation.
         * Enable double buffering. */
        StdDraw.enableDoubleBuffering();

        double t = 0;
        int num = planets.length;
        while(t < T) {
            /* Initialize the arrays of forces exerted on each planet in the x- and y-axes. */
            double[] xForces = new double[num];
            double[] yForces = new double[num];

            /* Compute the forces exerted on each planet. */
            for(int i = 0; i < num; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            /* Update the conditions for each planet. */
            for(int i = 0; i < num; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            /* Draw the background.
            /* Set the scale so that it comes from -radius to radius. */
            StdDraw.setScale(-radius, radius);

            /* Draw the image "starfield.jpg" as the background. */
            StdDraw.picture(0, 0, "images/starfield.jpg");

            /* Draw all the planets in the array. */
            for (Planet p: planets) {
                p.draw();
            }

            /* Show the offscreen buffer. */
            StdDraw.show();

            /* Pause for 10 milliseconds. */
            StdDraw.pause(10);

            /* Increase the time variable by dt. */
            t += dt;
        }

        /* After the simulation, print out the final state of the universe. */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet planet : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }
    }
}
