/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.model;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.persistence.*;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@Entity
@Table(name="citysites")
public class CitySite extends AbstractCitySiteEntity implements Serializable {

    private transient ResourceBundle csBundle = ResourceBundle.getBundle("CitySites");
    
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;    
    @Column(name="site_name")
    private String name;
    @Column(name="url")
    private String url;
    @Column(name="latitude")
    private double latitude;
    @Column(name="longitude")
    private double longitude;
    @Column(name="site_type")
    private String type;
    @Column(name="live_feed")
    private String feedUrl; 
    @Column(name="address")
    private String address;
    @Column(name="address2")
    private String address2;
    @Column(name="city")
    private String city = "Tampa";
    @Column(name="state")
    private String state = "FL";
    @Column(name="zip")
    private String zip;
    
    public String getQRCodeFileName() {
        return csBundle.getString("qrcode.storage.dir") + "/QR" + getId() + ".png";
    }

    public String getCitySiteDestination() {
        return (getType().equalsIgnoreCase("external")) ? getUrl() : "http://www.tampagov.net/citysites/" + getId() + "/" + getType().toLowerCase(); 
    }    
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the feedUrl
     */
    public String getFeedUrl() {
        return feedUrl;
    }

    /**
     * @param feedUrl the feedUrl to set
     */
    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

}


