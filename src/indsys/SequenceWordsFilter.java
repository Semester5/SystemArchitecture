package indsys;

import pmp.filter.AbstractFilter;
import pmp.filter.Source;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import java.io.StreamCorruptedException;
import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class SequenceWordsFilter extends AbstractFilter<ArrayList<String>, ArrayList<Sequence>> {

    public SequenceWordsFilter(Writeable<ArrayList<Sequence>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    public ArrayList<Sequence> read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void write(ArrayList<String> lines) throws StreamCorruptedException {
        ArrayList<Sequence> sequences = new ArrayList<Sequence>();

        for(int i = 0; i < lines.size(); i++) {
            Sequence sequence = new Sequence(i+1, lines.get(i));
            sequences.add(sequence);
        }
        writeOutput(sequences);
    }
}
