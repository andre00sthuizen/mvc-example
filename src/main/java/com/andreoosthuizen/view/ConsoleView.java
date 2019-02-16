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
        for (int i=0; i<raster.getPixels().length; i++) {
            String row = new String(raster.getPixels()[i]);
            PrintWriter printWriter = new PrintWriter(outputStream, true);
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
