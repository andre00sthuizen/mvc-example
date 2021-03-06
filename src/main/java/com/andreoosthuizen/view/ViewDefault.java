package com.andreoosthuizen.view;

import com.andreoosthuizen.model.Raster;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * A view implementation that, by default, renders a Raster to System.out
 * Each row in the Raster is a String that is printed top to bottom
 *
 * @author Andre Oosthuizen
 */
public class ViewDefault implements View {

    private OutputStream outputStream = System.out;

    @Override
    public void render(Raster raster) {
        if (raster == null) {
            throw new IllegalArgumentException("Raster input must not be null");
        }
        PrintWriter printWriter = new PrintWriter(outputStream, true);
        for (int j=0; j<raster.getHeight(); j++) {
            StringBuilder row = new StringBuilder(raster.getHeight());
            for (int i=0; i<raster.getWidth(); i++) {
                row.append(raster.getPixel(i, j));
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
