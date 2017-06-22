import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by z077391 on 6/20/2017.
 */
public class ClientList implements Serializable{
    private static final long serialVersionUIS =1L;
    private List clients =new LinkedList();
    private static ClientList clientList;
    /**
     * Private constructor for singleton pattern
     *
     */
    private ClientList() {
    }
    public static ClientList instance() {
        if (clientList == null) {
            return (clientList = new ClientList());
        } else {
            return clientList;
        }
    }
    /**
     * Checks whether a member with a given member id exists.
     * @param memberId the id of the member
     * @return true iff member exists
     *
     */
    public Client search(String memberId) {
        for (Iterator iterator = clients.iterator(); iterator.hasNext(); ) {
            Client client = (Client) iterator.next();
            if (client.getId().equals(memberId)) {
                return client;
            }
        }
        return null;
    }
    /**
     * Inserts a member into the collection
     * @param client the member to be inserted
     * @return true iff the member could be inserted. Currently always true
     */
    public boolean insertClient(Client client) {
        clients.add(client);
        return true;
    }
    /**
     * Removes a client from the catalog
     * @param clientId client id
     * @return true iff client could be removed
     * */
    public boolean removeClient(String clientId)
    {
        Client client = search(clientId);
        if(client == null)
        {
            return false;
        }else{
            return clients.remove(client);
        }
    }
    /**
     * Supports serialization
     * @param output the stream to be written to
     */
    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(clientList);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * Supports serialization
     *  @param input the stream to be read from
     */
    private void readObject(java.io.ObjectInputStream input) {
        try {
            if (clientList != null) {
                return;
            } else {
                input.defaultReadObject();
                if (clientList == null) {
                    clientList = (ClientList) input.readObject();
                } else {
                    input.readObject();
                }
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
    /** String form of the collection
     *
     */
    @Override
    public String toString() {
        return clients.toString();
    }

    public Iterator getClients()
    {
        return clients.iterator();
    }
}
