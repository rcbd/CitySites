/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.dao;

import java.util.List;
import org.rcbd.tampagov.citysites.model.CitySite;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
public interface CitySiteDao {

    public CitySite saveCitySite(CitySite citySite);
    public List<CitySite> listCitySites(CitySite meta);
    public CitySite getCitySite(String id);
    
}
