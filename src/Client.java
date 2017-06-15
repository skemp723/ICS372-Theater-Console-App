import javax.security.auth.login.AccountException;

/**
 * Created by teche on 6/14/2017.
 */
public class Client {

    private int id;
    private String name;
    private String address;
    private int phoneNumber;
    private double balance;

    public void Client(String name, String address, int phoneNumber)
    {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        setBalance(0);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
