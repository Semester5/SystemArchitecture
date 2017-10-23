package indsys;

import pmp.pipes.SimplePipe;

import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        SimplePipe<ArrayList<String>> simplePipe = new SimplePipe(new SequenceWordsFilter(new SimplePipe<SimplePipe<ArrayList<String>>>(new CircularShiftFilter())));
        SourceReader sourceReader = new SourceReader(simplePipe);
        sourceReader.run();


    }
}
