/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    @Inject
    private DataSource datasource;
    
    @Override
    public List<String> findAvailableReservation(String citySiteId) {
        List<String> reserved = getReservedDates(citySiteId);
        List<String> availableDates = new ArrayList<String>();
        FastDateFormat fmt = FastDateFormat.getInstance("yyyy-MM-dd");
        FastDateFormat readableFmt = FastDateFormat.getInstance("MMMM d, yyyy");
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < 14; i++) {
            cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1);
            String availableDate = fmt.format(cal.getTime());
            if (!reserved.contains(availableDate))
                availableDates.add(readableFmt.format(cal.getTime()));
        }
        
        return availableDates;
    }
    
    private List<String> getReservedDates(String citySiteId) {
       Connection conn = null;
        
        try {
            FastDateFormat fmt = FastDateFormat.getInstance("yyyy-MM-dd");
            conn = datasource.getConnection();
            Calendar cal = Calendar.getInstance();            
            String searchStartDay = fmt.format(cal.getTime());
            cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 14);
            String searchEndDay = fmt.format(cal.getTime());
            
            String _sql = "SELECT id, reservation_date FROM reservations WHERE reservation_date between ? and ? and citySite_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(_sql);
            pstmt.setString(1, searchStartDay);
            pstmt.setString(2, searchEndDay);
            pstmt.setString(3, citySiteId);
            
            Logger.getLogger(ReservationServiceImpl.class).info("SQL for finding reservations");
            Logger.getLogger(ReservationServiceImpl.class).info(_sql);
            Logger.getLogger(ReservationServiceImpl.class).info(searchStartDay);
            Logger.getLogger(ReservationServiceImpl.class).info(searchEndDay);
            Logger.getLogger(ReservationServiceImpl.class).info(citySiteId);
            
            ResultSet result = pstmt.executeQuery();
            List<String> reservedDates = new ArrayList<String>();
            while (result.next()) {
                reservedDates.add(fmt.format(result.getDate("reservation_date")));
            }
            result.close();
            pstmt.close();
            
            return reservedDates;
        } catch (SQLException sqle) {
            Logger.getLogger(CitySiteServiceImpl.class).error("", sqle);
            throw new RuntimeException("Unable to find nearby sites", sqle);
        } finally {
            try { conn.close(); } catch (Exception e) {}
            conn = null;
        }                    
    }

}
