/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.dao;

import java.util.List;
import org.rcbd.tampagov.citysites.model.CitySite;
import org.rcbd.tampagov.citysites.model.Reservation;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
public interface ReservationDao {

    Reservation getReservation(String id);
    List<Reservation> getReservationsByCitySite(CitySite citySite);
    List<Reservation> getReservationsByCitySite(String id);
    void saveReservation(Reservation reservation);
    
    
}
