/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
public class LiveFeed {

    private String title;
    private List<LiveFeedEntry> entries = new ArrayList<LiveFeedEntry>();
    private Date lastUpdated;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the entries
     */
    public List<LiveFeedEntry> getEntries() {
        return entries;
    }

    /**
     * @param entries the entries to set
     */
    public void setEntries(List<LiveFeedEntry> entries) {
        this.entries = entries;
    }

    /**
     * @return the lastUpdated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
}
