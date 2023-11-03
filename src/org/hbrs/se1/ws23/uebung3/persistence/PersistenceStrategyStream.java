package org.hbrs.se1.ws23.uebung3.persistence;

import org.hbrs.se1.ws23.uebung2.*;

import java.util.ArrayList;
import java.util.List;

import java.io.*;

public class PersistenceStrategyStream<E extends Member> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";
    private ObjectInputStream ois;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private FileOutputStream fos;
    private List<Member> newList = new ArrayList<>();



    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save.
     */
    public void openConnection() throws PersistenceException {
        try {
            fis = new FileInputStream(location);
            ois = new ObjectInputStream(fis);
            fos = new FileOutputStream(location);
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Fehler beim Öffnen der Dateiverbindung.");
        }
    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {
        try {
            if (oos != null) {
                oos.close();
            }
            if (ois != null) {
                ois.close();
            }
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Fehler beim Schließen der Dateiverbindung.");
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException  {
        try {
            if (oos == null) {
                throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Dateiverbindung nicht geöffnet.");
            }
            oos.writeObject(member);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,"Fehler beim Speichern der Daten.");
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<Member> load() throws PersistenceException  {
        try {
            if (ois == null) {
                throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Dateiverbindung nicht geöffnet.");
            }
            return (List<Member>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Fehler beim Laden der Daten.");
        }
    }
}
