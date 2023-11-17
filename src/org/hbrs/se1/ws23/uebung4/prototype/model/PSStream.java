package org.hbrs.se1.ws23.uebung4.prototype.model;

import org.hbrs.se1.ws23.uebung4.prototype.control.UserStory;
import org.hbrs.se1.ws23.uebung4.prototype.model.exception.ContainerException;

import java.io.*;
import java.util.List;

public class PSStream implements PersistanceStrategy {

    private List<UserStory> userStoryList = Container.getInstance().getCurrentList();

    private ObjectOutputStream oos = null;
    private FileOutputStream fos = null;
    private ObjectInputStream ois = null;
    private FileInputStream fis = null;

    @Override
    public void openConnection() throws IOException {
        try {

            fos = new FileOutputStream(InputDialog.getLocation());
            oos = new ObjectOutputStream(fos);

            fis = new FileInputStream(InputDialog.getLocation());
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {

        }
    }

    @Override
    public void closeConnection() throws IOException {
        try {
            if (oos != null) {
                oos.close();
                }
            if (ois != null) {
                ois.close();
                }
        } catch (IOException e) {

        }
    }

    @Override
    public List<UserStory> load() throws IOException {
        try {
            // Auslesen der Liste
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                userStoryList = (List) obj;
            }
            System.out.println("Es wurden " + Container.getInstance().size() + " UserStory erfolgreich reingeladen!");
        }
        catch (IOException e) {
            System.out.println("LOG (für Admin): Datei konnte nicht gefunden werden!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("LOG (für Admin): Liste konnte nicht extrahiert werden (ClassNotFound)!");
        }
        finally {
            if (ois != null) try { ois.close(); } catch (IOException e) {}
            if (fis != null) try { fis.close(); } catch (IOException e) {}
        }
        return userStoryList;
    }

    /**
     * Methode zum Hinzufügen eines Mitarbeiters unter Wahrung der Schlüsseleigenschaft
     * @param: userStory
     * @throws ContainerException
     */


    @Override
    public void save(List list) throws IOException, ContainerException {

        try {
            oos.writeObject(userStoryList);
            System.out.println( userStoryList.size() + " UserStory wurden erfolgreich gespeichert!");
        }
        catch (IOException e) {
            e.printStackTrace();
            //  Delegation in den aufrufendem Context
            // (Anwendung Pattern "Chain Of Responsibility)
            throw new ContainerException("Fehler beim Abspeichern");
        }
    }
}
