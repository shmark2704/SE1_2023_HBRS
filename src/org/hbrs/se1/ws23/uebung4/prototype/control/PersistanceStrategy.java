package org.hbrs.se1.ws23.uebung4.prototype.control;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung4.prototype.model.UserStory;

import java.util.List;

public interface PersistanceStrategy<UserStory> {
        public void openConnection() throws PersistenceException;
        public void closeConnection() throws PersistenceException;
        public void save(List<UserStory> userStories) throws PersistenceException;
        public List<UserStory> load() throws PersistenceException;
}
