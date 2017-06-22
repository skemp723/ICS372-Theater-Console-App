import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by z077391 on 6/20/2017.
 */
public class UserInterface {
    private static UserInterface userInterface;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Theater theater;
    private static final int EXIT = 0;
    private static final int ADD_CLIENT = 1;
    private static final int REMOVE_CLIENT = 2;
    private static final int LIST_CLIENTS = 3;
    private static final int ADD_CUSTOMER = 4;
    private static final int REMOVE_CUSTOMER = 5;
    private static final int ADD_CARD = 6;
    private static final int REMOVE_CARD = 7;
    private static final int LIST_CUSTOMERS = 8;
    private static final int ADD_SHOW = 9;
    private static final int LIST_SHOWS = 10;
    private static final int STORE_DATA = 11;
    private static final int RETRIEVE_DATA = 12;
    private static final int HELP = 13;
    /**
     * The method to start the app. Simply calls process()
     * @param args not used
     * */
    public static void main(String[] args)
    {
        UserInterface.instance().process();
    }

    private void process()
    {
        int command;
        help();
        while((command = getCommand()) != EXIT)
        {
            switch(command)
            {
                case ADD_CLIENT: addClient();
                    break;
                case REMOVE_CLIENT: removeClient();
                    break;
                case LIST_CLIENTS: getClients();
                    break;
                case ADD_CUSTOMER: addCustomer();;
                    break;
                case REMOVE_CUSTOMER: removeCustomer();
                    break;
                case LIST_CUSTOMERS: listCustomers();
                    break;
                case ADD_SHOW: addShow();
                    break;
                case LIST_SHOWS: listShows();
            }
        }
    }

    /**
     * Made private for singleton pattern.
     * Conditionally looks for any saved data. Otherwise, it gets
     * a singleton Theater object.
     */
    private UserInterface()
    {
        if(yesOrNo("Look for saved data and user it?"))
        {
            retrieve();
        }else{
            theater = Theater.instance();
        }
    }

    /**
     * Supports the singleton pattern
     *
     * @return the singleton object
     */
    public static UserInterface instance()
    {
        if (userInterface == null) {
            return userInterface = new UserInterface();
        } else {
            return userInterface;
        }
    }

    /**
     * Method to be called for retrieving saved data.
     * Uses the appropriate Library method for retrieval.
     *
     */
    private void retrieve()
    {
        try {
            Theater tempLibrary = Theater.retrieve();
            if (tempLibrary != null) {
                System.out.println(" The library has been successfully retrieved from the file LibraryData \n" );
                theater = tempLibrary;
            } else {
                System.out.println("File doesnt exist; creating new library" );
                theater = Theater.instance();
            }
        } catch(Exception cnfe) {
            cnfe.printStackTrace();
        }
    }

    /**
     * Converts the string to a number
     * @param prompt the string for prompting
     * @return the integer corresponding to the string
     *
     */
    public int getNumber(String prompt)
    {
        do {
            try {
                String item = getToken(prompt);
                Integer number = Integer.valueOf(item);
                return number.intValue();
            } catch (NumberFormatException nfe) {
                System.out.println("Please input a number ");
            }
        } while (true);
    }

    /**
     * Prompts for a date and gets a date object
     * @param prompt the prompt
     * @return the data as a Calendar object
     */
    public Calendar getDate(String prompt)
    {
        do {
            try {
                Calendar date = new GregorianCalendar();
                String item = getToken(prompt);
                DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
                date.setTime(dateFormat.parse(item));
                return date;
            } catch (Exception fe) {
                System.out.println("Please input a date as mm/dd/yy");
            }
        } while (true);
    }

    /**
     * Prompts for a command from the keyboard
     *
     * @return a valid command
     *
     */
    public int getCommand()
    {
        do {
            try {
                int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
                if (value >= EXIT && value <= HELP) {
                    return value;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter a number");
            }
        } while (true);
    }

    /**
     * Queries for a yes or no and returns true for yes and false for no
     *
     * @param prompt The string to be prepended to the yes/no prompt
     * @return true for yes and false for no
     *
     */
    private boolean yesOrNo(String prompt)
    {
        String more = getToken(prompt + " (Y|y)[es] or anything else for no");
        if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
            return false;
        }
        return true;
    }

    /**
     * Gets a token after prompting
     *
     * @param prompt - whatever the user wants as prompt
     * @return - the token from the keyboard
     *
     */
    public String getToken(String prompt)
    {
        do {
            try {
                System.out.println(prompt);
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
                if (tokenizer.hasMoreTokens()) {
                    return tokenizer.nextToken();
                }
            } catch (IOException ioe) {
                System.exit(0);
            }
        } while (true);
    }

    /**
     * Displays the help screen
     *
     */
    public void help()
    {
        System.out.println("Enter a number between 0 and 12 as explained below:");
        System.out.println(EXIT + " to Exit\n");
        System.out.println(ADD_CLIENT + " to add client(s)");
        System.out.println(REMOVE_CLIENT + " to  remove client(s)");
        System.out.println(LIST_CLIENTS + " to  print clients");
        System.out.println(ADD_CUSTOMER + " to  add customer(s)");
        System.out.println(REMOVE_CUSTOMER + " to  remove customer(s)");
        System.out.println(ADD_CARD + " to  add cards");
        System.out.println(REMOVE_CARD + " to  remove card(s)");
        System.out.println(LIST_CUSTOMERS + " to  print customer(s)");
        System.out.println(ADD_SHOW + " to  add show/play(s)");
        System.out.println(LIST_SHOWS + " to  print shows");
        System.out.println(STORE_DATA + " to  save data");
        System.out.println(RETRIEVE_DATA + " to  retrieve data");
        System.out.println(HELP + " for help");
    }

    /**
     * Method to be called for adding a client.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for adding the client.
     *
     */
    public void addClient()
    {
        String name = getToken("Enter member name");
        String address = getToken("Enter address");
        String phone = getToken("Enter phone");
        Client result;
        result = theater.addClient(name, address, phone);
        if (result == null) {
            System.out.println("Could not add member");
        }
        System.out.println(result.toString());
    }

    /**
     * Method to be called for removing client.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for removing client.
     *
     */
    public void removeClient()
    {
        int result;
        do {
            String clientID = getToken("Enter client id");
            result = theater.removeClient(clientID);
            switch(result){
                case Theater.CLIENT_NOT_FOUND:
                    System.out.println("No such client in Theater");
                    break;
                case Theater.ACTIVE_SHOW:
                    System.out.println("Client has an active show(s)");
                    break;
                case Theater.OPERATION_FAILED:
                    System.out.println("Client could not be removed");
                    break;
                case Theater.OPERATION_COMPLETED:
                    System.out.println("Client has been removed");
                    break;
                default:
                    System.out.println("An error has occurred");
            }
            if (!yesOrNo("Remove more clients?")) {
                break;
            }
        } while (true);
    }

    /**
     * Method called to diaplay all Clients
     * */
    public void getClients()
    {
        Iterator result = theater.getClients();
        if(result ==null)
        {
            System.out.println("No clients available");
        }else {
            while(result.hasNext())
            {
                Client client = (Client) result.next();
                System.out.println(client.toString());
            }
        }
    }

    /**
     * Method to be called for adding a customer.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for adding the customer.
     *
     */
    public void addCustomer()
    {
        String name = getToken("Enter customer name");
        String address = getToken("Enter address");
        String phone = getToken("Enter phone");
        String creditCardNumber  = getToken("Enter Credit Card number");
        String creditCardExp = getToken("Enter Credit Card experatio date");
        Customer result;
        result = theater.addCustomer(name, address, phone,creditCardNumber,creditCardExp);
        if (result == null) {
            System.out.println("Could not add customer");
        }
        System.out.println(result.toString());
    }

    /**
     * Method to be called for removing customer.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for removing customer.
     *
     */
    public void removeCustomer()
    {
        int result;
        do {
            String customerID = getToken("Enter customer id");
            result = theater.removeCustomer(customerID);
            switch(result){
                case Theater.CUSTOMER_NOT_FOUND:
                    System.out.println("No such customer in Theater");
                    break;
                case Theater.OPERATION_FAILED:
                    System.out.println("Customer could not be removed");
                    break;
                case Theater.OPERATION_COMPLETED:
                    System.out.println("Customer has been removed");
                    break;
                default:
                    System.out.println("An error has occurred");
            }
            if (!yesOrNo("Remove more customers?")) {
                break;
            }
        } while (true);
    }


    /**
     * Method called to diaplay all Clients
     * */
    public void listCustomers()
    {
        Iterator result = theater.getCustomers();
        if(result ==null)
        {
            System.out.println("No customers available");
        }else {
            while(result.hasNext())
            {
                Customer customer = (Customer) result.next();
                System.out.println(customer.toString());
            }
        }
    }

    /**
     * Method to be called for adding a show.
     * Prompts the user for the appropriate values and
     * uses the appropriate Client method for adding a show.
     *
     */
    public void addShow() {
        String showTitle = getToken("Enter show/paly title");
        String clientId = getToken("Enter client id");
        String showDt  = getToken("Enter show start date in YYYY-MM-DD format");
        Integer showPeriod = Integer.parseInt(getToken("Enter show period in number of weeks"));

        Date date = null;
        String pattern = "yyyy-MM-dd";
        try {
            date = new SimpleDateFormat(pattern).parse(showDt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar showDate = Calendar.getInstance();
        showDate.setTime(date);


        Show show = new Show(showTitle,showDate,showPeriod);
        if (show == null) {
            System.out.println("Could not add show");
        } else {
            System.out.println(show.toString());
        }

        Client client = theater.getClient(clientId);
        if (client == null) {
            System.out.println("Specified client doesn't exist");
        } else {
            client.addShow(show);
            System.out.println("Show added for the client");
            System.out.println(client.toString());
        }

    }

    /**
     * Method to be called for listing all shows.
     **/
    public void listShows() {

        Iterator itr = theater.getClients();
        while (itr.hasNext()) {
            Client client = (Client) itr.next();
            List<Show> shows = client.getShows();
            for (Iterator iterator = shows.iterator(); iterator.hasNext(); ) {
                Show show = (Show) iterator.next();
                System.out.println(show.toString());
            }
        }
    }

}


