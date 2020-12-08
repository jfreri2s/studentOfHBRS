package org.hbrs.se.ws20.uebung4.View;

import org.hbrs.se.ws20.uebung4.Model.ContainerException;
import org.hbrs.se.ws20.uebung4.Model.PersistenceException;
import org.hbrs.se.ws20.uebung4.Controller.Ausgabe;
import org.hbrs.se.ws20.uebung4.Model.Container;
import org.hbrs.se.ws20.uebung4.Model.Userstory;

import java.util.Scanner;

public class Eingabe {
    Ausgabe a = new Ausgabe();
    public void startEingabe() {
        System.out.println("Prio-Tool V1.0 by Jan F.");
        Scanner sc = new Scanner(System.in);
        Container c = Container.getInstance();

        try {
            System.out.print("> ");
            while (sc.hasNext()) {
                String s = sc.next();
                s.toLowerCase();
                switch (s) {
                    case "enter":
                        System.out.println("Geben sie eine ID an");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Geben sie einen Text an");
                        String text = sc.nextLine();
                        System.out.println("Geben sie Kriterien an");
                        String krit = sc.nextLine();
                        System.out.println("Geben sie einen mehrwert");
                        int mehrwert = sc.nextInt();
                        System.out.println("Geben sie einen strafe");
                        int strafe = sc.nextInt();
                        System.out.println("Geben sie einen aufwand");
                        int aufwand = sc.nextInt();
                        System.out.println("Geben sie einen risiko");
                        int risko = sc.nextInt();
                        Userstory bs = new Userstory(id, text, krit, mehrwert, strafe, aufwand, risko);
                        c.addUserStory(bs);
                        break;
                    case "store":
                        c.store();
                        break;
                    case "load":
                        String x = sc.next();
                        switch (x) {
                            case "merge":
                                c.loadMerge();
                                break;
                            case "force":
                                c.loadForce();
                                break;
                            default:
                                throw new IllegalArgumentException("Falsche Eingabe");
                        }
                        break;
                    case "dump":
                        a.dump(c.getCurrentList());
                        break;
                    case "help":
                        System.out.println("enter " + "store " + "load (merge) oder load (force) " + "dump " + "help "+ "exit");
                        break;
                    case "exit":
                        sc.close();
                        break;
                    default:
                        throw new IllegalArgumentException("Falsche Eingabe");
                }
            }
        } catch (ContainerException | PersistenceException e) {
            e.printStackTrace();
        }finally {
            sc.close();
        }
    }
}
