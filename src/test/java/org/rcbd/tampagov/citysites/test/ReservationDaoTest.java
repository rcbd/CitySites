/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.test;

import java.util.Calendar;
import javax.inject.Inject;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rcbd.tampagov.citysites.dao.CitySiteDao;
import org.rcbd.tampagov.citysites.dao.ReservationDao;
import org.rcbd.tampagov.citysites.model.CitySite;
import org.rcbd.tampagov.citysites.model.Reservation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:web-context.xml"})
public class ReservationDaoTest {
    private Logger log = Logger.getLogger(ReservationDaoTest.class);
    
    @Inject
    private ReservationDao reservationDao;
    
    @Inject
    private CitySiteDao citySiteDao;
    
    @Test
    public void testSave() {
        CitySite meta = new CitySite();
        meta.setType("Functional");
        CitySite cs = citySiteDao.listCitySites(meta).get(0);
        Assert.assertNotNull(cs);
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 6);
        
        Reservation r = new Reservation();
        r.setCitySite(cs);
        r.setEmail("foo@example.com");
        r.setFirstName("Foo");
        r.setLastName("Bar");
        r.setPhone("555-1212");
        r.setReservationDate(cal.getTime());
        
        reservationDao.saveReservation(r);
        Assert.assertNotNull(r.getId());
        
    }
}
