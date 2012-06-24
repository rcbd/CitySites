/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */
package org.rcbd.tampagov.citysites.service;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.sql.DataSource;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.apache.commons.lang.StringUtils;
import org.rcbd.tampagov.citysites.model.CitySite;
import org.apache.log4j.Logger;
import org.rcbd.tampagov.citysites.model.LiveFeed;
import org.rcbd.tampagov.citysites.model.LiveFeedEntry;
import org.rcbd.tampagov.citysites.utility.GeocodeUtil;
import org.rcbd.tampagov.citysites.utility.GeographicPoint;
import org.springframework.stereotype.Service;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@Service
public class CitySiteServiceImpl implements CitySiteService {

    private static Logger log = Logger.getLogger(CitySiteServiceImpl.class);
    private ResourceBundle csBundle = ResourceBundle.getBundle("CitySites");
    
    @Inject
    private DataSource datasource;
    
    @Override
    public void geocodeCitySite(CitySite cs) {
        String address = cs.getAddress();
        if (StringUtils.isNotBlank(cs.getAddress2())) {
            address += " " + cs.getAddress2();
        }
        GeographicPoint p = GeocodeUtil.INSTANCE.geocodeAddress(address, cs.getZip());
        
        cs.setLatitude(p.getLatitude());
        cs.setLongitude(p.getLongitude());
    }

    @Override
    public synchronized String generateQRCode(CitySite citySite) {
        String _qrUrl = citySite.getCitySiteDestination();
        
        ByteArrayOutputStream os = QRCode.from(_qrUrl).withSize(250,250).to(ImageType.PNG).stream();
        try {
            String _qrFile = citySite.getQRCodeFileName();
            
            File f = new File(_qrFile);
            
            FileOutputStream out = new FileOutputStream(f);
            out.write(os.toByteArray());
            out.flush();
            out.close();
            return f.getAbsolutePath();
        } catch (Exception e) {
            Logger.getLogger(CitySiteServiceImpl.class).error("", e);
            throw new RuntimeException("Unable to generate QR code");
        }
    }

    @Override
    public List<CitySite> findSitesNearMe(GeographicPoint point, double radiusInMiles) {
        String sql = "SELECT id, site_name, site_type, (3959 * acos(cos(radians(?)) * cos(radians(latitude)) * cos(radians(longitude) - radians(?)) + sin(radians(?)) * sin(radians(latitude))))  AS distance "
                   + "  FROM citysites "
                   + "HAVING distance < ? " 
                   + " ORDER BY distance "  
                   + " LIMIT 0, 20 ";
        // 1: lat
        // 2: lon
        // 3: lat
        // 4: radius in miles
        
        Connection conn = null;
        try {
            conn = datasource.getConnection();
            List<CitySite> sites = new ArrayList<CitySite>();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, point.getLatitude());
            pstmt.setDouble(2, point.getLongitude());
            pstmt.setDouble(3, point.getLatitude());
            pstmt.setDouble(4, radiusInMiles);
            
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                CitySite cs = new CitySite();
                cs.setId(result.getString("id"));
                cs.setName(result.getString("site_name"));
                cs.setType(result.getString("site_type"));
                
                double _distance = result.getDouble("distance");
                // round it to a precision of 2
                int tmp = (int)((_distance * Math.pow(10,2)));
                cs.setDistanceAway((double)tmp/Math.pow(10,2));
                
                sites.add(cs);
            }
            result.close();
            pstmt.close();
            
            return sites;
        } catch (SQLException sqle) {
            Logger.getLogger(CitySiteServiceImpl.class).error("", sqle);
            throw new RuntimeException("Unable to find nearby sites", sqle);
        } finally {
            try { conn.close(); } catch (Exception e) {}
            conn = null;
        }
    }

    @Override
    public LiveFeed loadLiveFeed(CitySite citySite) {
        try {
            URL feedUrl = new URL(citySite.getFeedUrl());

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed syndFeed = input.build(new XmlReader(feedUrl));
            
            LiveFeed feed = new LiveFeed();
            feed.setLastUpdated(syndFeed.getPublishedDate());
            feed.setTitle(syndFeed.getTitle());
            
            for (SyndEntry syndEntry : (List<SyndEntry>)syndFeed.getEntries()) {
                LiveFeedEntry entry = new LiveFeedEntry();
                if (syndEntry.getDescription() != null)
                    entry.setDescription(syndEntry.getDescription().getValue());
                entry.setLink(syndEntry.getLink());
                entry.setPublishedDate(syndEntry.getPublishedDate());
                entry.setTitle(syndEntry.getTitle());
                feed.getEntries().add(entry);
            }
            
            
            return feed;   
            
        } catch (Exception e) {
            Logger.getLogger(CitySiteServiceImpl.class).error("", e);
            throw new RuntimeException("Unable to load live feed", e);
        }
    }
    
}
