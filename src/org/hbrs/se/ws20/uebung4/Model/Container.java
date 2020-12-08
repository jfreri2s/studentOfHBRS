package org.hbrs.se.ws20.uebung4.Model;

import org.hbrs.se.ws20.uebung4.Model.PersistenceException;
import org.hbrs.se.ws20.uebung4.View.Eingabe;

import java.util.LinkedList;
import java.util.List;

public class Container {

    private PersistenceStrategy<Userstory> pSS;
    private static LinkedList<Userstory> list;
    private static Container instance =null;
    private static final Object lock = new Object();


    private Container(){
         list = new LinkedList<>();
    }

    //synchronized verhindert mehrere Clients die zugreifen
    public static /*synchronized*/ Container getInstance(){
        synchronized(lock) {
            if (instance == null) {
                instance = new Container();
            }
        }
        return instance;
    }

    public void addUserStory (Userstory r ) throws ContainerException {
        if ( contains(r) == true ) {
            throw new ContainerException("ID bereits vorhanden!");
        }
        list.add(r);

    }
    private boolean contains(Userstory u) {
        for ( Userstory a : list) {
            if (a.getId().equals(u.getId())) {
                return true;
            }
        }
        return false;
    }
    public int size(){
        return list.size();
    }

    public void store() throws PersistenceException {
        if(pSS == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"NoStrategyIsSet");
        }
        pSS.save(list);
    }

    public void loadMerge() throws PersistenceException, ContainerException {
        if(pSS == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"NoStrategyIsSet");
        }
        LinkedList<Userstory> tmp = (LinkedList<Userstory>) pSS.load();

            for (Userstory u : tmp) {
                Container.getInstance().addUserStory(u);
            }
    }
    public void loadForce() throws PersistenceException{
        if(pSS == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"NoStrategyIsSet");
        }
        list = (LinkedList<Userstory>) pSS.load();
    }
    public List<Userstory> getCurrentList(){
        return list;
    }

    public void setStrategy(PersistenceStrategy<Userstory> pSS) {
        this.pSS = pSS;
    }
}
