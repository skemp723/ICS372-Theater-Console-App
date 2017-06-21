import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Created by z077391 on 6/20/2017.
 */
public class Theater implements Serializable{

    private ClientList clientList;
    private static Theater theater;
    /**
     * Private for the singleton pattern
     * Creates the catalog and member collection objects
     */
    private Theater() {

        clientList = ClientList.instance();
    }
    /**
     * Supports the singleton pattern
     *
     * @return the singleton object
     */
    public static Theater instance() {
        if (theater == null) {
            ClientIdServer.instance(); // instantiate all singletons
            return (theater = new Theater());
        } else {
            return theater;
        }
    }

    /**
     * Organizes the operations for adding a member
     * @param name member name
     * @param address member address
     * @param phone member phone
     * @return the Member object created
     */
    public Client addClient(String name, String address, String phone) {
        Client client = new Client(name, address, phone);
        if (clientList.insertMember(client)) {
            return (client);
        }
        return null;
    }

    /**
     * Retrieves a deserialized version of the theater from disk
     * @return a Theater object
     */
    public static Theater retrieve() {
        try {
            FileInputStream file = new FileInputStream("LibraryData");
            ObjectInputStream input = new ObjectInputStream(file);
            input.readObject();
            ClientIdServer.retrieve(input);
            return theater;
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }
}
