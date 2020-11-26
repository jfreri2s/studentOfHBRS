package org.hbrs.se.ws20.solutions.uebung3;

import org.hbrs.se.ws20.solutions.uebung3.persistence.*;

import java.util.List;

public class Client {

    public void startClient() {
        Container container = Container.getInstance();

        try {
            container.addMember(new MemberKonkret(1));
        } catch (ContainerException e) {
            e.printStackTrace();
        }

        try {
            container.store();
        } catch (PersistenceException e) {
           e.printStackTrace();
        }

        List<Member> liste = container.getCurrentList();

        MemberView view = new MemberView();
        view.dump( liste );

    }


}
