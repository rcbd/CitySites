/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.service;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
public interface ReservationService {

    List<String> findAvailableReservation(String citySiteId);
    
}
