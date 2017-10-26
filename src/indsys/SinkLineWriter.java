package indsys;

import pmp.filter.Sink;
import pmp.interfaces.Writeable;

import java.io.*;
import java.util.ArrayList;

public class SinkLineWriter extends Sink<ArrayList<String>> implements Writeable<ArrayList<String>> {

    public static final String OUTPUTFILE = "Outputfiles\\textWithIndexAndDefinedLength.txt";

    public SinkLineWriter() {
        super();
    }

    @Override
    public void write(ArrayList<String> lines) throws StreamCorruptedException {
        Writer sinkWriter = null;

        try {
            sinkWriter = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(OUTPUTFILE), "utf-8"));

            for(String line : lines) {
                sinkWriter.write(line + System.lineSeparator());
            }
        } catch (IOException ex) {
            // report
        } finally {
            try {
                sinkWriter.close();
            } catch (Exception ex) {
            }
        }
    }
}
