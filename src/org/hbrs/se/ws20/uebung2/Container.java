package org.hbrs.se.ws20.uebung2;


import org.hbrs.se.ws20.uebung3.persistence.PersistenceException;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategyStream;

import java.util.LinkedList;
import java.util.List;

public class Container {

    private PersistenceStrategyStream<Member> pSS;
    private static LinkedList<Member> list;
    private static final Container OBJ = new Container();

    private Container(){
         list = new LinkedList<>();
    }

    public static Container getInstance(){
        return OBJ;
    }

    public void addMember(Member member) throws ContainerException {
        for (Member a : list) {
            if (a.getID().equals(member.getID()))
                throw new ContainerException("Das Member-Objekt mit der ID " + a.getID() + " ist bereits vorhanden!");
        }
        list.add(member);
    }

    public String deleteMember(Integer id){
        for (Member a : list) {
            if (a.getID().equals(id)) {
                String erg = "" + a.toString();
                list.remove(a);
                return erg;
            }
        }
        return "Die Zahl mit der ID " + id + " ist nicht vorhanden"; // Bei Exception kann man Stacktrace nachvollziehen. Das ist bei einem String nicht möglich
    }

    public int size(){
        return list.size();
    }

    public void store() throws PersistenceException{
        if(pSS == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"NoStrategyIsSet");
        }
        pSS.save(list);
    }

    public void load() throws PersistenceException{
        if(pSS == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"NoStrategyIsSet");
        }
       list = (LinkedList<Member>) pSS.load();
    }
    public List<Member> getCurrentList(){
        return list;
    }

    public void setpSS(PersistenceStrategyStream<Member> pSS) {
        this.pSS = pSS;
    }
}
