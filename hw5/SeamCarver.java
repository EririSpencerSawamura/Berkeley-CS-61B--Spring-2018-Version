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
        if (x < 0 || x >= width() || y < 0|| y >= width()) {
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
        int[] seam = new int[height()];
        double totalEnergy = Double.MAX_VALUE;

        for (int col = 0; col < width(); col++) {
            int y = 0;
            int x = col;
            int[] tempSeam = new int[height()];
            double tempEnergy = energy(x, y);
            tempSeam[y] = x;
            y++;

            double upEnergy = 0.0;
            double upLeftEnergy = 0.0;
            double upRightEnergy = 0.0;

            while (y < height()) {
                int upX = x;
                int leftX = x - 1;
                int rightX = x + 1;

                upEnergy = energy(upX, y);
                if (leftX >= 0) {
                    upLeftEnergy = energy(leftX, y);
                } else {
                    upLeftEnergy = Double.MAX_VALUE;
                }

                if (rightX < width()) {
                    upRightEnergy = energy(rightX, y);
                } else {
                    upRightEnergy = Double.MAX_VALUE;
                }

                if (upEnergy <= upLeftEnergy
                        && upEnergy <= upRightEnergy) {
                    tempEnergy += upEnergy;
                    tempSeam[y] = upX;
                } else if (upLeftEnergy <= upEnergy
                        && upLeftEnergy <= upRightEnergy) {
                    tempEnergy += upLeftEnergy;
                    tempSeam[y] = leftX;
                    x = leftX;
                } else {
                    tempEnergy += upRightEnergy;
                    tempSeam[y] = rightX;
                    x = rightX;
                }

                y++;
            }

            if (tempEnergy <= totalEnergy) {
                totalEnergy = tempEnergy;
                seam = tempSeam;
            }
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
