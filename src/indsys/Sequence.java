package indsys;

import java.util.ArrayList;
import java.util.LinkedList;

public class Sequence {
    private int lineNumber;
    private LinkedList<String> sequenceWords;

    public Sequence(int lineNumber, String line) {
        this.lineNumber = lineNumber;
        this.sequenceWords = new LinkedList<String>();

        changeLineToSequenceWords(line);
    }

    public Sequence(int lineNumber, LinkedList<String> sequenceWords) {
        this.lineNumber = lineNumber;
        this.sequenceWords = sequenceWords;
    }

    private void changeLineToSequenceWords(String line) {
        String word = "";
        for(char c : line.toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c)) {
                word += c;
            } else {
                sequenceWords.add(word);
                word = "";
            }
        }
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public LinkedList<String> getSequenceWords() {
        return sequenceWords;
    }

    public void setSequenceWords(LinkedList<String> sequenceWords) {
        this.sequenceWords = sequenceWords;
    }

    public Sequence shift() {
        LinkedList<String> shiftedLine = new LinkedList<>(sequenceWords);
        String firstWord = shiftedLine.removeFirst();
        shiftedLine.addLast((firstWord));
        return new Sequence(this.lineNumber, shiftedLine);
    }

    public int compareSeq(Sequence o2) {
        String line1 = this.getSequenceWords().toString();
        String line2 = o2.getSequenceWords().toString();
        return line1.compareTo(line2);
    }
}
