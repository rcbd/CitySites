/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.utility;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
public class GeographicPoint {

    private double latitude = 0.0d;
    private double longitude = 0.0d;

    public GeographicPoint() {}
    
    public GeographicPoint(double lat, double lon) {
        this.latitude = lat;
        this.longitude = lon;
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
    
    @Override
    public String toString() {
        return "[" + getLatitude() + "," + getLongitude() + "]";
    }
    
    
}
