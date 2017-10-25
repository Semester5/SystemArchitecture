package indsys;

import pmp.filter.Sink;
import pmp.interfaces.Writeable;

import javax.sound.sampled.Line;
import java.io.*;
import java.util.ArrayList;

public class SinkWriter extends Sink<ArrayList<Sequence>> implements Writeable<ArrayList<Sequence>> {

    public SinkWriter() {
        super();
    }

    @Override
    public void write(ArrayList<Sequence> sequences) throws StreamCorruptedException {
        Writer sinkWriter = null;

        try {
            sinkWriter = new BufferedWriter(
                    new OutputStreamWriter(
                    new FileOutputStream("Outputfiles\\filteredIndex.txt"), "utf-8"));

            for(Sequence sequence : sequences) {
                for(String word : sequence.getSequenceWords()) {
                    sinkWriter.write(word + " ");
                }
                sinkWriter.write("\tLinenumber: " + sequence.getLineNumber() + System.lineSeparator());
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
