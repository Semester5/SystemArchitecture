package indsys;

import pmp.filter.AbstractFilter;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class CircularShiftFilter  extends AbstractFilter<SimplePipe<ArrayList<String>>, SimplePipe<ArrayList<String>>> {
    public CircularShiftFilter(Writeable<SimplePipe<ArrayList<String>>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    public SimplePipe<ArrayList<String>> read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void write(SimplePipe<ArrayList<String>> value) throws StreamCorruptedException {

    }
}
