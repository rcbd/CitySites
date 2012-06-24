/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.test;

import java.lang.String;
import java.util.List;
import javax.inject.Inject;
import junit.framework.Assert;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rcbd.tampagov.citysites.dao.CitySiteDao;
import org.rcbd.tampagov.citysites.model.CitySite;
import org.rcbd.tampagov.citysites.service.CitySiteService;
import org.rcbd.tampagov.citysites.utility.GeocodeUtil;
import org.rcbd.tampagov.citysites.utility.GeographicPoint;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:web-context.xml"})
public class SitesByDistanceTest {
    
    private Logger log = Logger.getLogger(SitesByDistanceTest.class);
    
    @Inject
    private CitySiteService citySiteService;    
    
    @Test
    public void testGoogleGeocodeService() throws Exception {
        GeographicPoint p = new GeographicPoint(27.916008214026558d, -82.51848220825195d);
        
        double radius = 20.0d;
        
        List<CitySite> sites = citySiteService.findSitesNearMe(p, radius);
        Assert.assertNotNull(sites);
        
        for (CitySite cs : sites) {
            Assert.assertNotNull(cs);
            Assert.assertNotNull(cs.getDistanceAway());
            Assert.assertTrue(cs.getDistanceAway() >= 0.0d);
            Assert.assertTrue(cs.getDistanceAway() <= radius);
            
            log.info(cs.getName() + " is " + cs.getDistanceAway() + " miles away");
        }
        
    }
}
