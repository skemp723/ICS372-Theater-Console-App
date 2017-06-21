import java.io.Serializable;

/**
 * Created by z077391 on 6/20/2017.
 */
public class Client implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String phone;
    private String id;
    private static final String CLIENT_STRING = "C";

    /**
     * Represents a single member
     * @param name name of the member
     * @param address address of the member
     * @param phone phone number of the member
     */
    public  Client (String name, String address, String phone)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
        id = CLIENT_STRING + (ClientIdServer.instance()).getId();
    }


    public String getId() {
        return id;
    }
}
