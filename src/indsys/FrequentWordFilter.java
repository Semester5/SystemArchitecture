package indsys;

import pmp.filter.AbstractFilter;
import pmp.interfaces.Writeable;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;

public class FrequentWordFilter extends AbstractFilter<ArrayList<Sequence>, ArrayList<Sequence>> {

    public static final String INPUTFILE = "Inputfiles\\frequentEnglishWords.txt";
    public static final int FREQUENT_WORDS_LINENUMBER = 60;

    private ArrayList<String> frequentWords = new ArrayList<String>();

    public FrequentWordFilter(Writeable<ArrayList<Sequence>> output) throws InvalidParameterException {
        super(output);
        getAllFrequentWords(INPUTFILE);
    }

    private void getAllFrequentWords(String inputfile) {
        File file = new File(inputfile);

        if (!file.canRead() || !file.isFile()) {
            System.exit(-1);
        }

        ArrayList<String> allLines = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputfile));
            String line = null;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null && i < FREQUENT_WORDS_LINENUMBER) {
                allLines.add(line);
                i++;
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

        filterFrequentWords(allLines);
    }

    private void filterFrequentWords(ArrayList<String> allLines) {
        ArrayList<String> freqWords = new ArrayList<>();
        for(String line : allLines) {
            String[] split = line.split("\t");
            freqWords.add(split[1]);
        }
        this.frequentWords = freqWords;
    }

    @Override
    public ArrayList<Sequence> read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void write(ArrayList<Sequence> sequences) throws StreamCorruptedException {
        Iterator<Sequence> iter = sequences.iterator();
        while (iter.hasNext()) {
            String word = iter.next().getSequenceWords().getFirst();
            if(frequentWords.contains(word)) {
                iter.remove();
            }
        }
        writeOutput(sequences);
    }
}
