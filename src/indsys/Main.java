package indsys;

import pmp.pipes.SimplePipe;

import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        SimplePipe<ArrayList<String>> simplePipe = new SimplePipe(SequenceWordsFilter::new);
        SourceReader sourceReader = new SourceReader(simplePipe);
        sourceReader.run();


    }
}
