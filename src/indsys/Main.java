package indsys;

import pmp.filter.Sink;
import pmp.pipes.SimplePipe;

import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        SourceReader sourceReader = new SourceReader(
                new SimplePipe(
                        new SequenceWordsFilter(
                                new SimplePipe(
                                        new CircularShiftFilter(
                                                new SimplePipe(new SinkWriter()))))));
        sourceReader.run();
    }
}