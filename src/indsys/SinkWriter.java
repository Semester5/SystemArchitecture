package indsys;

import pmp.filter.Sink;
import pmp.interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class SinkWriter extends Sink<ArrayList<Sequence>> implements Writeable<ArrayList<Sequence>> {

    public SinkWriter() {

    }

    @Override
    public void write(ArrayList<Sequence> sequences) throws StreamCorruptedException {
        for(int i = 0; i < 20; i++) {
            System.out.println("Linenumber: " + sequences.get(i).getLineNumber() + " Words: " + sequences.get(i).getSequenceWords().getFirst());
        }
    }
}
