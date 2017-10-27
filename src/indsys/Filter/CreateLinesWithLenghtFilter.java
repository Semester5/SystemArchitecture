package indsys.Filter;

import pmp.filter.AbstractFilter;
import pmp.interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreateLinesWithLenghtFilter extends AbstractFilter<ArrayList<String>, ArrayList<String>> {

    private int lineLenghtInCharacters;

    public CreateLinesWithLenghtFilter(Writeable<ArrayList<String>> output, int lineLenghtInCharacters) {
        super(output);
        this.lineLenghtInCharacters = lineLenghtInCharacters;
    }

    @Override
    public ArrayList<String> read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void write(ArrayList<String> words) throws StreamCorruptedException {
        ArrayList<String> lines = new ArrayList<String>();

        int actualLengthCounter = 0;
        String actualLine = "";
        for(String word : words) {
            if(actualLengthCounter - word.length() > 0) {
                actualLine += word + " ";
            } else {
                lines.add(actualLine);
                actualLine = "";
            }
        }
        writeOutput(lines);
    }
}