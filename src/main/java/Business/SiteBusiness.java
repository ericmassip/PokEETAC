package Business;

import org.apache.log4j.Logger;

import java.util.Calendar;

/**
 * Created by ericmassip on 8/12/16.
 */
public class SiteBusiness {
    private Logger log = Logger.getLogger(SiteBusiness.class);

    public Calendar getFormattedDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        log.info("Date formatted: " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR));
        return calendar;
    }
}
