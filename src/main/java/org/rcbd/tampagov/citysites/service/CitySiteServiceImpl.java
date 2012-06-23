/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */
package org.rcbd.tampagov.citysites.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ResourceBundle;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.apache.commons.lang.StringUtils;
import org.rcbd.tampagov.citysites.model.CitySite;
import org.apache.log4j.Logger;
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
}
