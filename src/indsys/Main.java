package indsys;

import pmp.interfaces.Writeable;
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
                                                                                                new SinkWriter()))))))))));
                sourceReader.run();
                return;

            } else if("B".equals(mode.toUpperCase())) {
                int characterlength = 0;
                String formatmode = "";

                System.out.print("Bitte geben Sie eine gewünschte Character-Länge ein: ");
                try{
                    characterlength = Integer.parseInt(br.readLine());

                    System.out.print("Bitte geben Sie eine Formatierung an: [l]...Linksbündig, [z]...zentriert, [r]...rechtsbündig: ");
                    br = new BufferedReader(new InputStreamReader(System.in));
                    formatmode = br.readLine();

                    if("l".equals(formatmode) || "z".equals(formatmode) || "r".equals(formatmode)) {




                        return;
                    }
                } catch(Exception e){
                }
            }
        }
    }
}