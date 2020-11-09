package org.hbrs.se.ws20.uebung1.control;
import org.hbrs.se.ws20.uebung1.view.Client;
import java.io.*;
import java.util.List;
import java.util.Scanner;
public class Blackboxtest{
    public static void main(String[] args) throws FileNotFoundException {
        Client c = new Client();
        List<Integer> testZahl = List.of(5,-5,0);
        List<String> testString = List.of("Das Ergebnis der Berechnung: fünf",
                                          "Das Ergebnis der Berechnung: Uebersetzung der Zahl " + -5 + " nicht möglich " + Translator.version,
                                          "Das Ergebnis der Berechnung: Uebersetzung der Zahl " + 0 + " nicht möglich " + Translator.version );
        PrintStream originalPrintStream = new PrintStream(new FileOutputStream(FileDescriptor.out));
        for(int i = 0; i <= 2; i++){
            PrintStream a = new PrintStream("test.txt");
            System.setOut(a);
            c.display(testZahl.get(i));
            System.setOut(originalPrintStream);
            Scanner sc = new Scanner(new File("test.txt"));
            String scan = sc.nextLine();
            System.out.println((testString.get(i).equals(scan)));
        }
    }
}
