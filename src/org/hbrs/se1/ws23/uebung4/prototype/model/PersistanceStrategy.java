package org.hbrs.se1.ws23.uebung4.prototype.model;

import org.hbrs.se1.ws23.uebung4.prototype.model.exception.ContainerException;

import java.io.IOException;
import java.util.List;

public interface PersistanceStrategy<UserStory> {
        public void openConnection() throws IOException;
        public void closeConnection() throws IOException;
        public void save(List<UserStory> userStories) throws IOException, ContainerException;
        public List<UserStory> load() throws  IOException, ClassNotFoundException;
}
