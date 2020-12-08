package org.hbrs.se.ws20.uebung4;

import org.hbrs.se.ws20.uebung4.Model.Container;
import org.hbrs.se.ws20.uebung4.Model.PersistenceStrategyStream;
import org.hbrs.se.ws20.uebung4.Model.Userstory;
import org.hbrs.se.ws20.uebung4.View.Eingabe;

public class Main {

    public Main(){

    }

    public static void main(String[] args) {
        Container c = Container.getInstance();
        Eingabe eingabe = new Eingabe();
        c.setStrategy(new PersistenceStrategyStream<Userstory>());
        eingabe.startEingabe();
    }

}
