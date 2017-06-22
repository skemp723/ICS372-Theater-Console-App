import java.io.Serializable;

/**
 * Created by teche on 6/21/2017.
 */
public class CreditCard implements Serializable{
    private String creditCardNumber;
    private String creditCardExp;

    /**
     * Represents a single credit card
     * @param creditCard the card number of the card used
     * @param creditCardExp the experation date of the credit card*/
    public CreditCard(String creditCard, String creditCardExp)
    {
        this.creditCardNumber = creditCard;
        this.creditCardExp = creditCardExp;
    }
    /**
     * Getter for the credit card number
     * @return the cards number
     * */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    /**
     * Getter for the credit card experation date
     * @return the cards exp
     * */
    public String getCreditCardExp() {
        return creditCardExp;
    }
}
