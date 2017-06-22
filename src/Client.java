import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by z077391 on 6/20/2017.
 */
public class Client implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String phone;
    private String id;
    private List<Show> shows = new LinkedList();
    private static final String CLIENT_STRING = "CL";

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


    /**
     * Getter method for the id
     * @return returns the clientId*/
    public String getId() {
        return id;
    }

    /**
     * Adds show to the currnet list
     * @param show the new show to be added
     * */
    public void addShow(Show show)
    {
        shows.add(show);
    }

    /**
     * Checks whether there is a show scheduled after the current date
     * @return false iif there is a show
     * */
    public boolean hasShow() {
        ListIterator iterator = shows.listIterator();
        int i = 0;
        DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        Calendar date = Calendar.getInstance();
        if(iterator.hasNext())
        {
            i++;
            if(shows.get(i).getEndDate().compareTo(dateFormat.format(date)) >0 )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Return string of the Client
     * String contains id, name, address and phone number
     * */
    public String toString()
    {
        String result = String.format("ClientID: %s , Name: %s , Address: %s , Phone Number: %s",this.id,this.name,this.address,this.phone);
        return result;
    }
}
