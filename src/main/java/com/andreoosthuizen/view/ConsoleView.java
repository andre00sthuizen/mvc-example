package com.andreoosthuizen.view;

import com.andreoosthuizen.model.Raster;

import java.io.*;

/**
 * A view implementation that, by default, renders a Raster to System.out
 * Each row in the Raster is a String that is printed top to bottom
 *
 * @author Andre Oosthuizen
 */
public class ConsoleView implements View {

    private OutputStream outputStream = System.out;

    @Override
    public void render(Raster raster) {
        if (raster == null) {
            throw new IllegalArgumentException("Raster input must not be null");
        }
        PrintWriter printWriter = new PrintWriter(outputStream, true);
        char[][] pixels = raster.getPixels();
        for (int j=0; j<pixels[0].length; j++) {
            StringBuilder row = new StringBuilder(pixels[0].length);
            for (int i=0; i<pixels.length; i++) {
                row.append(pixels[i][j]);
            }
            printWriter.println(row);
        }
    }

    OutputStream getOutputStream() {
        return outputStream;
    }

    void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
