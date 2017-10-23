package indsys;

import pmp.interfaces.Writeable;

import java.io.*;
import java.util.ArrayList;

public class SourceReader extends pmp.filter.Source<ArrayList<String>> {

    public static final String INPUTFILE = "Inputfiles\\aliceInWonderland.txt";

    public SourceReader(Writeable<ArrayList<String>> output) {
        super(output);
    }

    @Override
    public ArrayList<String> read() throws StreamCorruptedException {
        return readSourceFile(INPUTFILE);
    }

    public ArrayList<String> readSourceFile(String filePath) {

        File file = new File(filePath);

        if (!file.canRead() || !file.isFile()) {
            System.exit(-1);
        }

        ArrayList<String> allLines = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                allLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                }
        }
        return allLines;
    }
}
