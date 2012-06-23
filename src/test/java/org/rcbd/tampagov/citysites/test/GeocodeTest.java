/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.test;

import junit.framework.Assert;
import org.junit.Test;
import org.rcbd.tampagov.citysites.utility.GeocodeUtil;
import org.rcbd.tampagov.citysites.utility.GeographicPoint;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
public class GeocodeTest {

    @Test
    public void testGoogleGeocodeService() throws Exception {
        String addr = "3605 W Royal Palm Circle";
        String zip  = "33629";
        
        GeographicPoint p = GeocodeUtil.INSTANCE.geocodeAddress(addr, zip);
        Assert.assertNotNull(p);
        System.out.println(p.toString());
                
    }
    
}
