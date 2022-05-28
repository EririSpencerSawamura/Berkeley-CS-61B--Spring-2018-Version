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

        /* Draw the background.
        /* Set the scale so that it comes from -radius to radius. */
        StdDraw.setScale(-radius, radius);
        /* Draw the image "starfield.jpg" as the background. */
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /* Draw all the planets in the array. */
        for (Planet p: planets) {
            p.draw();
        }
    }
}
