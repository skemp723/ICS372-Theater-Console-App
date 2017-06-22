import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by z077391 on 6/20/2017.
 */
public class Theater implements Serializable{

    private static final long serialVersionUID = 1L;
    public static final int CLIENT_NOT_FOUND = 1;
    public static final int ACTIVE_SHOW = 3;
    public static final int OPERATION_COMPLETED = 7;
    public static final int OPERATION_FAILED = 8;

    private ClientList clientList;
    private CreditCardList creditCardList;
    private CustomerList customerList;
    private static Theater theater;
    /**
     * Private for the singleton pattern
     * Creates the catalog and member collection objects
     */
    private Theater() {

        clientList = ClientList.instance();
        customerList = CustomerList.instance();
        creditCardList = CreditCardList.instance();
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
     * Retrieves a deserialized version of the theater from disk
     * @return a Theater object
     */
    public static Theater retrieve()
    {
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

    /**
     * Organizes the operations for adding a client
     * @param name client name
     * @param address client address
     * @param phone client phone
     * @return the Client object created
     */
    public Client addClient(String name, String address, String phone)
    {
        Client client = new Client(name, address, phone);
        if (clientList.insertClient(client)) {
            return (client);
        }
        return null;
    }

    /**
     * Removes a specific book from the catalog
     * @param clientId id of the book
     * @return a code representing the outcome
     */
    public int removeClient(String clientId) {
        Client client = clientList.search(clientId);
        if (client == null) {
            return(CLIENT_NOT_FOUND);
        }
        if (client.hasShow()) {
            return(ACTIVE_SHOW);
        }
        if (clientList.removeClient(clientId)) {
            return (OPERATION_COMPLETED);
        }
        return (OPERATION_FAILED);
    }

    /**
     * Method called to retreive the iterator client list
     * @return a list iterator of the ClientList
     * */
    public Iterator getClients()
    {
        return clientList.getClients();
    }

    public Customer addCustomer(String name, String address, String phone, String creditCardNumber, String creditCardExp)
    {
        if(creditCardList.search(creditCardNumber)!=null){
            return null;
        }else{
            CreditCard creditCard = addCreditCard(creditCardNumber,creditCardExp);
            Customer customer = new Customer(name,address,phone,creditCard);
            if (customerList.insertCustomer(customer)) {
                return (customer);
            }
            return null;
        }
    }

    private CreditCard addCreditCard(String creditCardNumber, String creditCardExp) {
        CreditCard creditCard = new CreditCard(creditCardNumber,creditCardExp);
        if(creditCardList.insertCreditCard(creditCard))
        {
            return (creditCard);
        }
        return null;
    }

}
