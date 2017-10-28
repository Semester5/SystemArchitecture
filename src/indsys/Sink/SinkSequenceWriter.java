package indsys.Sink;

import indsys.Models.Sequence;
import pmp.filter.Sink;
import pmp.interfaces.Writeable;

import java.io.*;
import java.util.ArrayList;

public class SinkSequenceWriter extends Sink<ArrayList<Sequence>> implements Writeable<ArrayList<Sequence>> {

    public static final String OUTPUTFILE = "Outputfiles\\textWithIndex.txt";

    public SinkSequenceWriter() {
        super();
    }

    @Override
    public void write(ArrayList<Sequence> sequences) throws StreamCorruptedException {
        Writer sinkWriter = null;

        try {
            sinkWriter = new BufferedWriter(
                    new OutputStreamWriter(
                    new FileOutputStream(OUTPUTFILE), "utf-8"));

            for(Sequence sequence : sequences) {
                for(String word : sequence.getSequenceWords()) {
                    sinkWriter.write(word + " ");
                }
                sinkWriter.write("\t\t" + sequence.getLineNumber()  + System.lineSeparator());
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
