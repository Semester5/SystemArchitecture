package indsys;

import pmp.filter.Sink;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        SourceReader sourceReader = new SourceReader(
                new SimplePipe(
                        (Writeable) new SequenceWordsFilter(
                                new SimplePipe(
                                        (Writeable) new CircularShiftFilter(
                                                new SimplePipe(
                                                        (Writeable) new FrequentWordFilter(
                                                                new SimplePipe(
                                                                        (Writeable) new SortAlphabeticalFilter(
                                                                                new SimplePipe(
                                                                                    new SinkWriter()))))))))));
        sourceReader.run();
    }
}