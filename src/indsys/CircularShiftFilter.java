package indsys;

import pmp.filter.AbstractFilter;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class CircularShiftFilter extends AbstractFilter<ArrayList<Sequence>, ArrayList<Sequence>> {

    public CircularShiftFilter(Writeable<ArrayList<Sequence>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    public ArrayList<Sequence> read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void write(ArrayList<Sequence> sequences) throws StreamCorruptedException {
        ArrayList<Sequence> shiftedSequences = new ArrayList<Sequence>();
        for(Sequence sequence : sequences) {
            Sequence newSeq = sequence;
            for(int i = 0; i< sequence.getSequenceWords().size(); i++) {
                newSeq = newSeq.shift();
                shiftedSequences.add(newSeq);
            }
        }
        sequences.addAll(shiftedSequences);
        writeOutput(sequences);
    }
}