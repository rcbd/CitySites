/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import junit.framework.Assert;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rcbd.tampagov.citysites.dao.CitySiteDao;
import org.rcbd.tampagov.citysites.model.CitySite;
import org.rcbd.tampagov.citysites.model.LiveFeed;
import org.rcbd.tampagov.citysites.model.LiveFeedEntry;
import org.rcbd.tampagov.citysites.service.CitySiteService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:web-context.xml"})
public class FeedReaderTest {

    private Logger log = Logger.getLogger(TBCSCitySiteDaoTest.class);
    
    @Inject
    private CitySiteDao citySiteDao;
    
    @Inject
    private CitySiteService citySiteService;    
    
    @Test
    public void testReadingOfLiveFeed() {
        CitySite meta = new CitySite();
        meta.setType("Live");
        List<CitySite> sites = citySiteDao.listCitySites(meta);
        Assert.assertNotNull(sites);
        Assert.assertTrue(sites.size() > 0);
        
        CitySite liveSite = sites.get(1);
        Assert.assertNotNull(liveSite);
        Assert.assertEquals(liveSite.getType().toLowerCase(), meta.getType().toLowerCase());
        
        LiveFeed feed = citySiteService.loadLiveFeed(liveSite);
        Assert.assertNotNull(feed);
        
        for (LiveFeedEntry entry : feed.getEntries()) {
            log.info(entry.getTitle());
            log.info(entry.getDescription());
//            log.info(ToStringBuilder.reflectionToString(entry, ToStringStyle.MULTI_LINE_STYLE));
        }
    }
    
//    @Test
//    public void testTruncation() {
//        int truncateLen = 7;
//        String foo = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam luctus lectus in massa euismod interdum. Nullam in elit tortor, a dictum nisl. Proin at diam metus. Mauris mollis porta suscipit. Curabitur pellentesque consequat felis in viverra. In porta congue gravida. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Maecenas ut porta nibh. Vestibulum rhoncus, tellus sit amet dictum hendrerit, felis quam cursus odio, et faucibus est ipsum eget dolor. Maecenas posuere scelerisque congue. Duis felis dui, posuere quis placerat non, tristique ut dui. Curabitur molestie magna ut massa aliquam sed vulputate nulla mattis. Morbi mollis dui id sapien elementum vel aliquet nisl aliquet.";
//        String bar = "";
//        String regexp = "^(.{" + truncateLen + "}\\w+).*$";
//        System.out.println("RE: " + regexp);
//        Pattern p = Pattern.compile(regexp);
//        Matcher m = p.matcher(foo);
//        if (m.find()) {
//            bar = m.group(1) + "...";
//        } else {
//            bar = "didn't find shit";
//        }
//        
//        System.out.println(foo);
//        System.out.println(bar);
//    }
    
}
