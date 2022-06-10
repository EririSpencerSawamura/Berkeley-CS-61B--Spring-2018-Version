package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    /**
     * Computes the width of the rowNum's row of tiles
     * in a hexagon of a certain side length.
     * @param sideLength The side length of the hexagon.
     * @param rowNum     The number of the row where rowNum = 0 is the top row.
     * @return The width of the row.
     */
    public static int rowWidth(int sideLength, int rowNum) {
        if (rowNum < sideLength) {
            return sideLength + 2 * rowNum;
        } else {
            return 5 * sideLength - 2 * rowNum - 2;
        }
    }

    /**
     * Computes the difference of the positions of left end tile in the next
     * row according to the current row's, in a hexagon of a certain side length.
     * @param sideLength The side length of the hexagon.
     * @param rowNum     The number of the row where rowNum = 0 is the top row.
     * @return The difference of positions of the left end tiles.
     */
    public static int leftEndDiff(int sideLength, int rowNum) {
        if (rowNum < sideLength) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Adds a row of the same tile.
     * @param world    The world to draw on.
     * @param leftPosX The leftmost x position of the row.
     * @param PosY     The y position of the row.
     * @param width    The width of the row.
     * @param tile     The tile to draw.
     */
    public static void addRow(TETile[][] world, int leftPosX, int PosY, int width, TETile tile) {
        for (int x = 0; x < width; x++) {
            int PosX = leftPosX + x;
            //world[PosX][PosY] = TETile.colorVariant(tile, 32, 32, 32);
        }
    }

    /** adds a hexagon of a certain side length to a given position in the world. */
    public static void addHexagon(int sideLength) {

    }


}
