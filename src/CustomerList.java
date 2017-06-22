import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by teche on 6/21/2017.
 */
public class CustomerList implements Serializable{
    private static final long serialVersionUIS =1L;
    private List customers = new LinkedList();
    private static CustomerList customerList;
    /**
     * Private constructor for singleton pattern
     *
     */
    private CustomerList() {
    }
    public static CustomerList instance() {
        if (customerList == null) {
            return (customerList = new CustomerList());
        } else {
            return customerList;
        }
    }

    /**
     * Checks whether a member with a given member id exists.
     * @param customerId the id of the member
     * @return true iff member exists
     *
     */
    public Customer search(String customerId) {
        for (Iterator iterator = customers.iterator(); iterator.hasNext(); ) {
            Customer customer = (Customer) iterator.next();
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Inserts a member into the collection
     * @param customer the member to be inserted
     * @return true iff the member could be inserted. Currently always true
     */
    public boolean insertCustomer(Customer customer) {
        customers.add(customer);
        return true;
    }

    /**
     * Removes a customer from the catalog
     * @param customerId customer id
     * @return true iff customer could be removed
     * */
    public boolean removeCustomer(String customerId)
    {
        Customer customer = search(customerId);
        if(customer == null)
        {
            return false;
        }else{
            return customers.remove(customer);
        }
    }

    /**
     * Supports serialization
     * @param output the stream to be written to
     */
    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(customerList);
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
            if (customerList != null) {
                return;
            } else {
                input.defaultReadObject();
                if (customerList == null) {
                    customerList = (CustomerList) input.readObject();
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
        return customers.toString();
    }

    /**
     * Returns and Iterator of the exsisting customers
     * @return iterator of the collection
     * */
    public Iterator getCustomers()
    {
        return customers.iterator();
    }

}
