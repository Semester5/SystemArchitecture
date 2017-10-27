package indsys.Source;

import pmp.filter.Source;
import pmp.interfaces.Writeable;

import java.io.*;
import java.util.ArrayList;

public class SourceLineStreamReader extends Source<ArrayList<String>> {

    public static final String INPUTFILE = "Inputfiles\\aliceInWonderland.txt";

    public SourceLineStreamReader(Writeable<ArrayList<String>> output) {
        super(output);
    }

    @Override
    //Reads all full lines of an Inputfile
    public ArrayList<String> read() throws StreamCorruptedException {
        if(ENDING_SIGNAL != null && ENDING_SIGNAL.equals(1)) {
            return null;
        }

        File file = new File(INPUTFILE);

        if (!file.canRead() || !file.isFile()) {
            System.exit(-1);
        }

        ArrayList<String> allLinesOfSource = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(INPUTFILE));
            String lineOfSource = null;

            while ((lineOfSource = bufferedReader.readLine()) != null) {
                allLinesOfSource.add(lineOfSource);
            }
            ENDING_SIGNAL = 1;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return allLinesOfSource;
    }
}
