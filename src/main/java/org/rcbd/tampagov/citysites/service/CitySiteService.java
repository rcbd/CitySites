/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.service;

import java.util.List;
import org.rcbd.tampagov.citysites.model.CitySite;
import org.rcbd.tampagov.citysites.model.LiveFeed;
import org.rcbd.tampagov.citysites.utility.GeographicPoint;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
public interface CitySiteService {

    void geocodeCitySite(CitySite citySite);    
    String generateQRCode(CitySite citySite);
    
    List<CitySite> findSitesNearMe(GeographicPoint point, double radiusInMiles);
    
    LiveFeed loadLiveFeed(CitySite citySite);
}
