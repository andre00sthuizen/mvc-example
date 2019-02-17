package com.andreoosthuizen.model;

import java.util.Objects;

/**
 * Draws a line starting from (x1,y1) to (x2,y2) using the drawCharacter
 * Only vertical and horizontal lines are supported
 * More formally, either x1 == x2 or y1 == y2 must be true otherwise an InvalidArgumentException will be thrown
 *
 * @author Andre Oosthuizen
 */
public class Line implements Drawable {

    private int x1, y1, x2, y2;
    private char drawCharacter;

    public Line(int x1, int y1, int x2, int y2, char drawCharacter) {
        if (x1 != x2 && y1 != y2) {
            throw new IllegalArgumentException("Only vertical or horizontal lines are supported.");
        }
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.drawCharacter = drawCharacter;
    }

    @Override
    public void paint(Raster raster) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return x1 == line.x1 &&
                y1 == line.y1 &&
                x2 == line.x2 &&
                y2 == line.y2 &&
                drawCharacter == line.drawCharacter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, drawCharacter);
    }

    @Override
    public String toString() {
        return "L "+x1+" "+y1+" "+x2+" "+y2+" "+drawCharacter;
    }

}
