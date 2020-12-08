package org.hbrs.se.ws20.uebung4.Model;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<Userstory> implements PersistenceStrategy<Userstory> {
    public static String datei = "Userstory.txt";
    private ObjectOutputStream oos;
    private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectInputStream ois;
    @Override
    public void openConnection() throws PersistenceException {
        try {
            fos = new FileOutputStream(datei);
            //fis = new FileInputStream(datei);
        } catch (FileNotFoundException e) {
            throw new PersistenceException( PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "Error in opening the connection, File could not be found");
        }
        try {
            oos = new ObjectOutputStream(fos);
            //ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            throw new PersistenceException( PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "Error in opening the connection, problems with the stream");
        }
    }

    @Override
    public void closeConnection() throws PersistenceException {

        try {
            if (oos != null) oos.close();
            if (fos != null) fos.close();
            if (ois != null) ois.close();
            if (fis != null) fis.close();
        } catch( IOException e ) {
            throw new PersistenceException(PersistenceException.ExceptionType.ClosingFailure , "error while closing connections");
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Userstory> list) throws PersistenceException {
        this.openConnection();
        try {
            System.out.println(list.size() + " User Stories wurden erfolgreich gespeichert!");
            oos.writeObject(list);
        }
        catch (Exception e) {
            throw new PersistenceException( PersistenceException.ExceptionType.LoadFailure , "Fehler beim Speichern der Datei!");
        }
        finally {
            this.closeConnection();
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     */
    public List<Userstory> load() throws PersistenceException {
        FileInputStream fileIn;
        ObjectInputStream objIn;
        List<Userstory> neuListe = null;
        Object obj;
        try {
            fileIn = new FileInputStream(datei);
            objIn = new ObjectInputStream(fileIn);
            obj = objIn.readObject();
            if (obj instanceof List<?>) {
                neuListe = (List) obj;
            }
            objIn.close();
            return neuListe;
        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "LoadFailure");
        }
    }
}

