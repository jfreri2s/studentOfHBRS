package org.hbrs.se.ws20.uebung1.control;
import org.hbrs.se.ws20.uebung1.view.Client;
import java.io.*;
import java.util.Scanner;


public class Blackboxtest{


    public static void main(String[] args) throws FileNotFoundException {
        Client c = new Client();
        String erg = "Das Ergebnis der Berechnung: fünf";
        PrintStream a = new PrintStream("test.txt");
        System.setOut(a);
        c.display(5);

        PrintStream originalPrintStream = new PrintStream(new FileOutputStream(FileDescriptor.out));
        System.setOut(originalPrintStream);

        Scanner sc = new Scanner(new File("test.txt"));
        String scan = sc.nextLine();
        System.out.println((erg.equals(scan)));
    }
}
