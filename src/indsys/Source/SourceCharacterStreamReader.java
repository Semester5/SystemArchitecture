package indsys.Source;

import pmp.filter.Source;
import pmp.interfaces.Writeable;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SourceCharacterStreamReader extends Source<ArrayList<Character>> {

    public static final String INPUTFILE = "Inputfiles\\aliceInWonderland.txt";

    public SourceCharacterStreamReader(Writeable<ArrayList<Character>> output) {
        super(output);
    }


    @Override
    public ArrayList<Character> read() throws StreamCorruptedException {
        if(ENDING_SIGNAL != null && ENDING_SIGNAL.equals(1)) {
            return null;
        }

        File file = new File(INPUTFILE);
        if (!file.canRead() || !file.isFile()) {
            System.exit(-1);
        }

        ArrayList<Character> allCharactersOfSource = new ArrayList<Character>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            int c = 0;
            while((c = bufferedReader.read()) != -1) {
                allCharactersOfSource.add((char) c);
            }

            ENDING_SIGNAL = 1;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return allCharactersOfSource;
    }
}
