import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by teche on 6/21/2017.
 */
public class Show implements Serializable{

    private String title;
    private String id;
    private Calendar date;
    private int period;
    /**
     * Represents a single member
     * @param date date the show will start
     * @param title name of the show
     * @param period the number of weeks the show will run
     * */
    public void Show(String title, Calendar date, int period)
    {
        this.title = title;
        this.date = date;
        this.period = period;
    }
    /**
     *returns the end date of the show
     * @return the end date of the show
     * */
    public String getEndDate()
    {
        DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        Calendar endDate = date;
        endDate.add(Calendar.DATE, period);
        return dateFormat.format(endDate);
    }
    /**
     * Getter for title
     * @return the title of the shoe
     * */
    public String getTitle() {
        return title;
    }
    /**
     * Getter for showId
     * @return the id of the requested show
     * */
    public String getId() {
        return id;
    }
    /**
     * Getter for the show date
     * @return the show start date
     * */
    public Calendar getDate() {
        return date;
    }
    /**
     * Getter for the show run period
     * @return the number of weeks the show will run
     * */
    public int getPeriod() {
        return period;
    }
    /**
     * Setter for the show period
     * If a period is provided below 0 the period will default to 0
     * @param period the number of weeks the show will run
     * */
    public void setPeriod(int period) {
        if(period<=0)
        {
            this.period = 0;
        }else {
            this.period = period;
        }
    }
}
