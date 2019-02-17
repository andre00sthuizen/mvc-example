package com.andreoosthuizen.model;

import java.util.Objects;

/**
 * We make use of a recursive flood-fill algorithm to implement this functionality
 * 
 * @author Andre Oosthuizen
 */
public class Fill implements Drawable {

    private int x, y;
    private char fillCharacter;

    public Fill(int x, int y, char fillCharacter) {
        this.x = x;
        this.y = y;
        this.fillCharacter = fillCharacter;
    }

    @Override
    public void paint(Raster raster) {
        if (x >= 0 && x < raster.getWidth() && y >=0 && y < raster.getHeight()) {
            floodFill(raster, x, y, raster.getPixel(x, y), fillCharacter);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fill fill = (Fill) o;
        return x == fill.x &&
                y == fill.y &&
                fillCharacter == fill.fillCharacter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, fillCharacter);
    }

    private void floodFill(Raster raster, int x, int y, char targetCharacter, char replacementCharacter) {
        if (x >= 0 && x < raster.getWidth() && y >=0 && y < raster.getHeight()) {
            //1. If targetCharacter is equal to replacementCharacter, return.
            if (targetCharacter == replacementCharacter) {
                return;
            }
            //2. If the character of the pixel is not equal to targetCharacter, return.
            if (raster.getPixel(x, y) != targetCharacter) {
                return;
            }
            //3. Set the pixel to replacementCharacter.
            raster.setPixel(x, y, replacementCharacter);
            //4.1 Perform Flood-fill (one step to the south of pixel, targetCharacter, replacementCharacter).
            floodFill(raster, x, y -1, targetCharacter, replacementCharacter);
            //4.2 Perform Flood-fill (one step to the north of pixel, targetCharacter, replacementCharacter).
            floodFill(raster, x, y + 1, targetCharacter, replacementCharacter);
            //4.3 Perform Flood-fill (one step to the west of pixel, targetCharacter, replacementCharacter).
            floodFill(raster, x - 1, y, targetCharacter, replacementCharacter);
            //4.4 Perform Flood-fill (one step to the east of pixel, targetCharacter, replacementCharacter).
            floodFill(raster, x + 1, y, targetCharacter, replacementCharacter);
        }
        return;
    }

}
