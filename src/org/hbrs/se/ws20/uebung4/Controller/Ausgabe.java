package org.hbrs.se.ws20.uebung4.Controller;

import org.hbrs.se.ws20.uebung4.Model.Userstory;

import java.util.Comparator;
import java.util.List;

public class Ausgabe {
    public void dump(List<Userstory> liste){
        System.out.println("1)Prio 2)ID 3)Text 4) Kriterien 5)Mehrwert 6)Strafe 7)Aufwand 8) Risiko");
        liste.stream()
                .sorted(Comparator.comparing(Userstory::getPrio))
                .filter(element -> element.getAufwand()>4)
                .forEach(element -> System.out.println(" 1)" + element.getPrio()+" 2)"+element.getId()+" 3)"+element.getText()+" 4)"+element.getKriterien()+
                        " 5)"+element.getMehrwert() + " 6)" + element.getStrafe()+ " 7)" +element.getAufwand()+ " 8)" +element.getRisk() ));
    }
}
