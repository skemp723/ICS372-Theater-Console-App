import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by teche on 6/21/2017.
 */
public class Customer implements Serializable, Iterable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String phone;
    private String id;
    private List<CreditCard> creditCards = new LinkedList<CreditCard>();
    private static final String CUSTOMER_STRING = "CU";

    /**
     * Represents a single customer
     * @param name name of the customer
     * @param address address of the customer
     * @param phone phone number of the customer
     * @param creditCard the customers credit card
     */
    public  Customer (String name, String address, String phone,CreditCard creditCard)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
        creditCards.add(creditCard);
        id = CUSTOMER_STRING + (ClientIdServer.instance()).getId();
    }

    /**
     * Getter method for the customer name
     * @return  the customer name
     * */
    public String getName() {
        return name;
    }

    /**Getter method for the customer address
     * @return the customer address
     * */
    public String getAddress() {
        return address;
    }

    /**
     * Getter method for the customer phone
     * @return the customer phone
     * */
    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }

    public String toString()
    {
        String result = String.format("CustomerID: %s , Name: %s , Address: %s , Phone Number: %s" +
                " , creditCards: %s",this.id,this.name,this.address,this.phone,this.creditCards);
        return result;
    }

    public void customerAddCard(CreditCard c){
        creditCards.add(c);
    }

    public void customerRemoveCard(CreditCard c){
        creditCards.remove(c);

    }
    public int customerCCListSize(){
        return creditCards.size();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return creditCards.listIterator();
    }
}
