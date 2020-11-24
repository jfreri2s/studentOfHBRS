package org.hbrs.se.ws20.uebung3.persistence;
import java.io.*;
import java.util.List;
public class PersistenceStrategyStream<Member> implements PersistenceStrategy<Member> {
    public static String datei = "Memberlist.txt";
    @Override
    public void openConnection() throws PersistenceException {

    }

    @Override
    public void closeConnection() throws PersistenceException {

    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException  {
        FileOutputStream file;
        ObjectOutputStream output;
        try{
            file = new FileOutputStream(datei);
            output = new ObjectOutputStream(file);
            output.writeObject(member);
            output.close();
        }catch (Exception e){
            throw new PersistenceException(PersistenceException.ExceptionType.SaveFailure,"SaveFailure");
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     */
    public List<Member> load() throws PersistenceException {
        FileInputStream fileIn;
        ObjectInputStream objIn;
        List<Member> neuListe = null;
        Object obj = null;
        try {
            fileIn = new FileInputStream(datei);
            objIn = new ObjectInputStream(fileIn);
            obj=  objIn.readObject();
            if(obj instanceof List<?>){
                neuListe = (List) obj;
            }
            objIn.close();
            return  neuListe;
        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "LoadFailure");
        }
    }
}

