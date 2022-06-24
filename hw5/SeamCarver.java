import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver {
    private Picture picture;

    public SeamCarver(Picture picture) {
        this.picture = picture;
    }

    public Picture picture() {
        return new Picture(this.picture);
    }

    public int width() {
        return picture.width();
    }

    public int height() {
        return picture.height();
    }

    public double energy(int x, int y) {
        if (x < 0 || x >= width() || y < 0|| y >= height()) {
            throw new IndexOutOfBoundsException();
        }

        return energyX(x,y) + energyY(x, y);
    }

    private double energyX(int x, int y) {
        int leftX = nextX(x, -1);
        int rightX = nextX(x, 1);

        Color leftColor = picture.get(leftX, y);
        Color rightColor = picture.get(rightX, y);

        double R_x = Math.abs(leftColor.getRed() - rightColor.getRed());
        double G_x = Math.abs(leftColor.getGreen() - rightColor.getGreen());
        double B_x = Math.abs(leftColor.getBlue() - rightColor.getBlue());

        return R_x * R_x + G_x * G_x + B_x * B_x;
    }

    private double energyY(int x, int y) {
        int upY = nextY(y, -1);
        int downY = nextY(y, 1);

        Color upColor = picture.get(x, upY);
        Color downColor = picture.get(x, downY);

        double R_y = Math.abs(upColor.getRed() - downColor.getRed());
        double G_y = Math.abs(upColor.getGreen() - downColor.getGreen());
        double B_y = Math.abs(upColor.getBlue() - downColor.getBlue());

        return R_y * R_y + G_y * G_y + B_y * B_y;
    }

    private int nextX(int x, int diff) {
        if (x + diff == width()) {
            return 0;
        } else if (x + diff < 0) {
            return width() - 1;
        }

        return x + diff;
    }

    private int nextY(int y, int diff) {
        if (y + diff == height()) {
            return 0;
        } else if (y + diff < 0) {
            return height() - 1;
        }

        return y + diff;
    }

    public int[] findVerticalSeam() {
        double[][] M = getM();
        double minEnergy = Double.MAX_VALUE;
        int startX = 0;
        for (int x = 0; x < width(); x++) {
            if (M[x][height() - 1] < minEnergy) {
                minEnergy = M[x][height() - 1];
                startX = x;
            }
        }

        return seamFinder(M, startX);
    }

    private double[][] getM() {
        double[][] e = new double[width()][height()];
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                e[x][y] = energy(x, y);
            }
        }

        double[][] M = new double[width()][height()];
        for (int x = 0; x < width(); x++) {
            M[x][0] = e[x][0];
        }

/*        // Special case: width = 1
        if (width() == 1) {
            return M;
        }*/

        for (int y = 1; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (x == 0) {
                    M[x][y] = e[x][y] + Math.min(M[x][y - 1], M[x + 1][y - 1]);
                } else if (x == width() - 1) {
                    M[x][y] = e[x][y] + Math.min(M[x][y - 1], M[x - 1][y - 1]);
                } else {
                    M[x][y] = e[x][y] +
                            Math.min(Math.min(M[x][y - 1], M[x - 1][y - 1]), M[x + 1][y - 1]);
                }
            }
        }

        return M;
    }

    private int[] seamFinder(double[][] M, int x) {
        double next;
        int[] seam = new int[height()];
        seam[height() - 1] = x;

        if (width() == 1) {
            for (int y = height() - 1; y >= 1; y--) {
                seam[y - 1] = x;
            }
            return seam;
        }

        for (int y = height() - 1; y >= 1; y--) {
            if (x == 0) {
                next = Math.min(M[x][y - 1], M[x + 1][y - 1]);
                if (next == M[x][y - 1]) {
                    seam[y - 1] = x;
                } else {
                    seam[y - 1] = x + 1;
                }
            } else if (x == width() - 1) {
                next = Math.min(M[x][y - 1], M[x - 1][y - 1]);
                if (next == M[x][y - 1]) {
                    seam[y - 1] = x;
                } else {
                    seam[y - 1] = x - 1;
                }
            } else {
                next = Math.min(Math.min(M[x][y - 1], M[x - 1][y - 1]), M[x + 1][y - 1]);
                if (next == M[x][y - 1]) {
                    seam[y - 1] = x;
                } else if (next == M[x - 1][y - 1]) {
                    seam[y - 1] = x - 1;
                } else {
                    seam[y - 1] = x + 1;
                }
            }

            x = seam[y - 1];
        }

        return seam;
    }


    public int[] findHorizontalSeam() {
        transpose();
        int[] seam = findVerticalSeam();
        transpose();
        return seam;
    }

    private void transpose() {
        Picture temp = new Picture(height(), width());
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                temp.set(row, col, picture.get(col, row));
            }
        }

        picture = temp;
    }

    public void removeHorizontalSeam(int[] seam) {
        if (checkSeam(seam)) {
            picture = SeamRemover.removeHorizontalSeam(picture, seam);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void removeVerticalSeam(int[] seam) {
        if (checkSeam(seam)) {
            picture = SeamRemover.removeVerticalSeam(picture, seam);
        }
    }

    private boolean checkSeam(int[] seam) {
        for (int i = 0; i < seam.length - 1; i++) {
            if (Math.abs(seam[i] - seam[i + 1]) > 1) {
                return false;
            }
        }

        return true;
    }
}
