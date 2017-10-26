package indsys;

import pmp.filter.AbstractFilter;
import pmp.interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class CircularShiftFilter extends AbstractFilter<ArrayList<Sequence>, ArrayList<Sequence>> {

    public CircularShiftFilter(Writeable<ArrayList<Sequence>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    public ArrayList<Sequence> read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void write(ArrayList<Sequence> sequences) throws StreamCorruptedException {
        ArrayList<Sequence> shiftedSequences = new ArrayList<Sequence>();

        for(Sequence sequence : sequences) {
            Sequence newSeq = sequence;
            for(int i = 0; i < sequence.getSequenceWords().size(); i++) {
                newSeq = newSeq.shiftForBookIndex();
                if(isFirstWordValid(newSeq)) {
                    shiftedSequences.add(newSeq);
                }
            }
        }
        writeOutput(shiftedSequences);
    }

    /**
     *
     * @param seq
     * @return true if the word is valid
     *         false if the word is not valid
     */
    private boolean isFirstWordValid(Sequence seq) {
        //TODO Julia: muss überarbeitet werden, im Moment stimmt das Regex noch nicht ganz :-(...
        String word = seq.getSequenceWords().peek();

        if(word.matches("[A-Za-z]+")) {
            //word with no especially char
            return true;
        }
        if(word.matches("[^A-Za-z]+")) {
            //word with just especially char(s)
            return false;
        }
        seq.getSequenceWords().removeFirst();
        String newWord = word.replaceAll("[^A-Za-z]+", "");
        seq.getSequenceWords().addFirst(newWord);
        return true;
    }
}