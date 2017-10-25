package indsys;

import pmp.filter.Source;
import pmp.interfaces.Writeable;

import java.io.*;
import java.util.ArrayList;

public class SourceReader extends Source<ArrayList<String>> {

    public static final String INPUTFILE = "Inputfiles\\aliceInWonderland.txt";

    public SourceReader(Writeable<ArrayList<String>> output) {
        super(output);
    }

    @Override
    public ArrayList<String> read() throws StreamCorruptedException {
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
