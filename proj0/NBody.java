public class NBody {
    /** The method that returns a double corresponding to the radius of the universe in that file. */
    public static double readRadius(String filePath) {
        In in = new In(filePath);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /** The method that returns an array of Planets corresponding to the planets in the file. */
    public static Planet[] readPlanets(String filePath) {
        In in = new In(filePath);
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


}
