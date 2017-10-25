package indsys;

import pmp.filter.AbstractFilter;
import pmp.interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Comparator;

public class SortAlphabeticalFilter  extends AbstractFilter<ArrayList<Sequence>, ArrayList<Sequence>> {

    public SortAlphabeticalFilter(Writeable<ArrayList<Sequence>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    public ArrayList<Sequence> read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void write(ArrayList<Sequence> sequences) throws StreamCorruptedException {
        sequences.sort(new Comparator<Sequence>() {
            @Override
            public int compare(Sequence o1, Sequence o2) {
                return o1.compareSeq(o2);
            }
        });
        writeOutput(sequences);
    }
}
