/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.service;

import org.rcbd.tampagov.citysites.model.CitySite;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
public interface CitySiteService {

    void geocodeCitySite(CitySite citySite);    
    String generateQRCode(CitySite citySite);
    
}
