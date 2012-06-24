/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
public class LiveFeedEntry {

    private String title;
    private String link;
    private String description;
    private Date publishedDate;
    private boolean truncate = true;
    private int truncateLen  = 500;

    
    
    private String truncateDescription() {
        if (StringUtils.isBlank(description)) {
            return "";
        }
        
        if (description.length() <= truncateLen)
            return description;
        
        String regexp = "^(.{" + truncateLen + "}\\w+).*$";
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(description);
        if (m.find()) {
            return m.group(1) + "...";
        }
        return description;
    }

    
    
    
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
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        if (truncate)
            return truncateDescription();
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the publishedDate
     */
    public Date getPublishedDate() {
        return publishedDate;
    }

    /**
     * @param publishedDate the publishedDate to set
     */
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
    
    /**
     * @return the truncate
     */
    public boolean isTruncate() {
        return truncate;
    }

    /**
     * @param truncate the truncate to set
     */
    public void setTruncate(boolean truncate) {
        this.truncate = truncate;
    }

    /**
     * @return the truncateLen
     */
    public int getTruncateLen() {
        return truncateLen;
    }

    /**
     * @param truncateLen the truncateLen to set
     */
    public void setTruncateLen(int truncateLen) {
        this.truncateLen = truncateLen;
    }
}
