package indsys.Filter;

import indsys.Enum.Alignment;
import pmp.filter.AbstractFilter;
import pmp.interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class AlignmentFilter extends AbstractFilter<ArrayList<String>, ArrayList<String>> {
    private Alignment alignment;
    private int lineLenghtInCharacters;

    public AlignmentFilter(Writeable<ArrayList<String>> output, int lineLenghtInCharacters, String alignment) {
        super(output);

        this.lineLenghtInCharacters = lineLenghtInCharacters;

        if("l".equals(alignment)) {
            this.alignment = Alignment.L;
        } else if("z".equals(alignment)) {
            this.alignment = Alignment.C;
        }else if("r".equals(alignment)) {
            this.alignment = Alignment.R;
        }
    }

    @Override
    public ArrayList<String> read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void write(ArrayList<String> lines) throws StreamCorruptedException {

        ArrayList<String> linesWithAlignment = new ArrayList<String>();
        for(String line : lines) {
            if(Alignment.C.equals(alignment)) {
                int diff = (lineLenghtInCharacters - line.length()) / 2;
                while(diff > 0) {
                    line = " " + line;
                    diff--;
                }
            } else if(Alignment.R.equals(alignment)) {
                int diff = (lineLenghtInCharacters - line.length());
                while(diff > 0) {
                    line = " " + line;
                    diff--;
                }
            }
            linesWithAlignment.add((line));
        }
        writeOutput(linesWithAlignment);
    }
}