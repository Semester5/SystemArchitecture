package indsys.Filter;

import indsys.Models.Sequence;
import pmp.filter.AbstractFilter;
import pmp.interfaces.Writeable;

import java.io.StreamCorruptedException;
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
            Object line = lines.get(i);
            Sequence sequence = new Sequence(i+1, (String) line);
            if(sequence.getSequenceWords().size() > 0) {
                sequences.add(sequence);
            }
        }
        writeOutput(sequences);
    }
}
