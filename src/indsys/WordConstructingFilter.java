package indsys;

import pmp.filter.AbstractFilter;
import pmp.interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class WordConstructingFilter extends AbstractFilter<ArrayList<Character>, ArrayList<String>> {

    public WordConstructingFilter(Writeable<ArrayList<String>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    public ArrayList<String> read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void write(ArrayList<Character> charactersInputStream) throws StreamCorruptedException {
        ArrayList<String> words = new ArrayList<String>();
        String word = "";

        for(Character c : charactersInputStream) {
            if(c != ' ') {
                word += c;
            } else if(!"".equals(word)){
                words.add(word);
                word = "";
            }
        }
        writeOutput(words);
    }
}
