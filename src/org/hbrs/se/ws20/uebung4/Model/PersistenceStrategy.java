package org.hbrs.se.ws20.uebung4.Model;

import org.hbrs.se.ws20.uebung3.persistence.PersistenceException;

import java.util.List;

/**
 * Interface for defining basic methods for a persistence mechanism
 * Each concrete algorithm (i.e. strategy) must implement this method
 * This interface corresponds to the abstract strategy w.r.t. to the
 * Strategy Design Pattern (GoF).
 *
 * @param <E>
 */
public interface PersistenceStrategy<E> {
    public void openConnection() throws org.hbrs.se.ws20.uebung4.Model.PersistenceException;
    public void closeConnection() throws org.hbrs.se.ws20.uebung4.Model.PersistenceException;
    public void save(List<E> member) throws org.hbrs.se.ws20.uebung4.Model.PersistenceException;
    public List<E> load() throws org.hbrs.se.ws20.uebung4.Model.PersistenceException;
}
