package org.hbrs.se1.ws23.uebung4.prototype.control;

import org.hbrs.se1.ws23.uebung2.Member;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung4.prototype.model.UserStory;

import java.util.List;

public class PSStream implements PersistanceStrategy {

    @Override
    public void openConnection() throws PersistenceException {

    }

    @Override
    public void closeConnection() throws PersistenceException {

    }

    @Override
    public List<UserStory> load() throws PersistenceException {
        return null;
    }

    @Override
    public void save(List list) throws PersistenceException {

    }
}
