package indsys;

import indsys.Filter.*;
import indsys.Sink.SinkLineWriter;
import indsys.Sink.SinkSequenceWriter;
import indsys.Source.SourceCharacterStreamReader;
import indsys.Source.SourceLineStreamReader;
import pmp.interfaces.Writeable;
import pmp.pipes.DoubleExitPushPipe;
import pmp.pipes.SimplePipe;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        String mode = "";
        while(true) {

            System.out.print("Möchten Sie INDSYS im Modus A (Indexgenerierung) oder B (Indexgenerierung und Formatierung eines Textes) starten? ");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                mode = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if("A".equals(mode.toUpperCase())) {
                SourceLineStreamReader sourceReader = new SourceLineStreamReader(
                        new SimplePipe(
                                (Writeable) new SequenceWordsFilter(
                                        new SimplePipe(
                                                (Writeable) new CircularShiftFilter(
                                                        new SimplePipe(
                                                                (Writeable) new FrequentWordFilter(
                                                                        new SimplePipe(
                                                                                (Writeable) new SortAlphabeticalFilter(
                                                                                        new SimplePipe(
                                                                                                new SinkSequenceWriter()))))))))));
                sourceReader.run();
                return;

            } else if("B".equals(mode.toUpperCase())) {
                int characterlength = 0;
                String formatmode = "";

                System.out.print("Bitte geben Sie eine gewünschte Character-Länge ein: ");
                try{
                    characterlength = Integer.parseInt(br.readLine());

                    System.out.print("Bitte geben Sie eine Formatierung an: [l]...Linksbündig, [z]...zentriert, [r]...rechtsbündig: ");
                    formatmode = br.readLine().toLowerCase();
                } catch(Exception e){
                    System.err.println(e.toString());
                }

                if("l".equals(formatmode) || "z".equals(formatmode) || "r".equals(formatmode)) {

                    SourceCharacterStreamReader sourceReader = new SourceCharacterStreamReader(
                            new SimplePipe(
                                    (Writeable) new WordConstructingFilter(
                                            new SimplePipe(
                                                    (Writeable) new CreateLinesWithLenghtFilter(
                                                            new SimplePipe(
                                                                    (Writeable) new AlignmentFilter(
                                                                            new DoubleExitPushPipe(
                                                                                    (Writeable) new SinkLineWriter(),
                                                                                    (Writeable) new SequenceWordsFilter(
                                                                                            new SimplePipe(
                                                                                                    (Writeable) new CircularShiftFilter(
                                                                                                            new SimplePipe(
                                                                                                                    (Writeable) new FrequentWordFilter(
                                                                                                                            new SimplePipe(
                                                                                                                                    (Writeable) new SortAlphabeticalFilter(
                                                                                                                                            new SimplePipe(
                                                                                                                                                    new SinkSequenceWriter()))))))))), characterlength, formatmode)), characterlength)))));
                    sourceReader.run();
                    return;
                }
            }
        }
    }
}