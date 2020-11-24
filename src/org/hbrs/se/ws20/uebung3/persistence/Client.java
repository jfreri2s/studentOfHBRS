package org.hbrs.se.ws20.uebung3.persistence;

import org.hbrs.se.ws20.solutions.uebung2.GenericContainer;
import org.hbrs.se.ws20.uebung2.Container;
import org.hbrs.se.ws20.uebung2.ContainerException;
import org.hbrs.se.ws20.uebung2.Create;

public class Client {

   public static void main(String[] args) {
       Container obj = Container.getInstance();
       obj.setpSS(new PersistenceStrategyStream());
       MemberView mw = new MemberView();
        /*try {
            obj.addMember(new Create(1));
            obj.addMember(new Create(2));
            obj.addMember(new Create(15));

        } catch (ContainerException e) {
            e.printStackTrace();
        }*/

            try {
                //obj.store();
                obj.load();
                mw.dump(obj.getCurrentList());
            } catch (PersistenceException e) {
                e.printStackTrace();
            }

        }
    }

