/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.test;

import java.io.File;
import java.util.ResourceBundle;
import javax.inject.Inject;
import junit.framework.Assert;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rcbd.tampagov.citysites.dao.CitySiteDao;
import org.rcbd.tampagov.citysites.model.CitySite;
import org.rcbd.tampagov.citysites.service.CitySiteService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:web-context.xml"})
public class TBCSCitySiteDaoTest {
    
    private Logger log = Logger.getLogger(TBCSCitySiteDaoTest.class);
    
    @Inject
    private CitySiteDao citySiteDao;
    
    @Inject
    private CitySiteService citySiteService;
    
    ResourceBundle csBundle = ResourceBundle.getBundle("CitySites");
    
//	@Autowired
//	public void setCitySiteDao(CitySiteDao dao) {
//		this.citySiteDao = dao;
//	}

    @Test
    public void testDaoInjection() {
        Assert.assertNotNull(citySiteDao);
        log.info("City Site DAO injected: " + ToStringBuilder.reflectionToString(citySiteDao));
    }
    
//	@Test
	public void testCreateData() {
        Assert.assertNotNull(citySiteDao);
        CitySite cs = new CitySite();
        cs.setAddress("211 North Tampa Street");
        cs.setAddress2(null); 
        cs.setCity("Tampa");
        cs.setZip("33602");
        cs.setLatitude(0d); //27.946663394703556d);
        cs.setLongitude(0d); //-82.45803594589233d);
        cs.setFeedUrl("https://search.twitter.com/search.atom?q=%23mayorshackathon");
        cs.setName("Mayor's Hackathon 2012");
        cs.setType("Live");
        cs.setUrl("http://www.tampagov.net/information_resources/hackathon/index.asp");
        
        citySiteService.geocodeCitySite(cs);
        
		cs = citySiteDao.saveCitySite(cs);
        String expectedResult = cs.getId();        
        CitySite persistedCs = citySiteDao.getCitySite(cs.getId());
        Assert.assertEquals(expectedResult, persistedCs.getId());
        

        String absQRFile = citySiteService.generateQRCode(cs);
        
        Assert.assertNotNull(absQRFile);
        try {
            File f = new File(absQRFile);
            Assert.assertTrue(f.exists());
        } catch (Exception e) {}
        log.info("Created QR File: " + absQRFile);     
	}

//	@Test
//	public void testRetrieveData() {
//		List<User> userList = dao.getAllUser(new User());
//		Assert.assertEquals(1, userList.size());
//		User userExpected = userList.get(0);
//		User userResult = dao.selectUserById(userExpected.getUserId());
//		Assert.assertEquals(userExpected.getUserId(), userResult.getUserId());
//	}
//
//	@Test
//	public void testUpdateData() {
//		List<User> userList = dao.getAllUser(new User());
//		Assert.assertEquals(1, userList.size());
//		User userExpected = userList.get(0);
//		userExpected.setUserName("Singgih");
//		dao.saveUser(userExpected);
//		User userResult = dao.selectUserById(userExpected.getUserId());
//		Assert.assertEquals(userExpected.getUserName(), userResult
//				.getUserName());
//	}
//
//	@Test
//	public void testDeleteData() {
//		List<User> userList = dao.getAllUser(new User());
//		Assert.assertEquals(1, userList.size());
//		User userExpected = userList.get(0);
//		dao.deleteUser(userExpected);
//		User userResult = dao.selectUserById(userExpected.getUserId());
//		Assert.assertEquals(userResult, null);
//	}
    
}
