import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by teche on 6/21/2017.
 */
public class CreditCardList implements Serializable{
    private static final long serialVersionUIS =1L;
    private List creditCards =new LinkedList();
    private static CreditCardList creditCardList;
    /**
     * Private constructor for singleton pattern
     *
     */
    private CreditCardList() {
    }
    public static CreditCardList instance() {
        if (creditCardList == null) {
            return (creditCardList = new CreditCardList());
        } else {
            return creditCardList;
        }
    }


    /**
     * Checks whether a member with a given member id exists.
     * @param creditCardNumber the id of the member
     * @return true iff member exists
     *
     */
    public CreditCard search(String creditCardNumber) {
        for (Iterator iterator = creditCards.iterator(); iterator.hasNext(); ) {
            CreditCard creditCard = (CreditCard) iterator.next();
            if (creditCard.getCreditCardNumber().equals(creditCardNumber)) {
                return creditCard;
            }
        }
        return null;
    }

    /**
     * Inserts a member into the collection
     * @param creditCard the member to be inserted
     * @return true iff the member could be inserted. Currently always true
     */
    public boolean insertCreditCard(CreditCard creditCard) {
        creditCards.add(creditCard);
        return true;
    }

    /**
     * Removes a creditcard from the list
     * @param creditCardNumber creditcard number
     * @return true iff creditcard could be removed
     * */
    public boolean removeCreditCard(String creditCardNumber)
    {
        CreditCard creditCard = search(creditCardNumber);
        if(creditCard == null)
        {
            return false;
        }else{
            return creditCards.remove(creditCard);
        }
    }

    /**
     * Supports serialization
     * @param output the stream to be written to
     */
    private void writeObject(ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(creditCardList);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Supports serialization
     *  @param input the stream to be read from
     */
    private void readObject(ObjectInputStream input) {
        try {
            if (creditCardList != null) {
                return;
            } else {
                input.defaultReadObject();
                if (creditCardList == null) {
                    creditCardList = (CreditCardList) input.readObject();
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
        return creditCards.toString();
    }

    /**
     * Returns and Iterator of the exsisting clients
     * @return iterator of the collection
     * */
    public Iterator getCreditCards()
    {
        return creditCards.iterator();
    }
}
