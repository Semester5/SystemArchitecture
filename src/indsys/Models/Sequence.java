package indsys.Models;
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
            if (c != ' ' && c != '\uFEFF') {
                word += c;
            } else {
                if(!word.equals("")) {
                    sequenceWords.add(word);
                }
                word = "";
            }
        }
        if(!word.equals("")) {
            sequenceWords.add(word);
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

    public Sequence shiftForBookIndex() {
        LinkedList<String> shiftedLine = new LinkedList<String>(sequenceWords);
        String firstWord = shiftedLine.removeFirst();
        shiftedLine.addLast(firstWord);
        return new Sequence(this.lineNumber, shiftedLine);
    }

    public int compareSeq(Sequence o2) {
        String line1 = getLineAsString(this.getSequenceWords());
        String line2 = getLineAsString(o2.getSequenceWords());
        return line1.compareToIgnoreCase(line2);
    }

    private String getLineAsString(LinkedList<String> sequenceWords) {
        StringBuilder sb = new StringBuilder();
        for(String word : sequenceWords) {
            sb.append(word);
        }
        return sb.toString();
    }
}