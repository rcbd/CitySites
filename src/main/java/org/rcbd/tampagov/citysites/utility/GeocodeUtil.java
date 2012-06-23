/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.utility;

import java.io.*;
import java.net.URLEncoder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
public enum GeocodeUtil {
    INSTANCE;
    
    String GOOGLE_GEOCODE_SERVICE_URL = "http://maps.googleapis.com/maps/api/geocode/xml";
    
    
    public GeographicPoint geocodeAddress(String address, String zip) {
        if (StringUtils.isBlank(address) || StringUtils.isBlank(zip)) {
            return null;
        }

        StringBuilder sb = new StringBuilder(GOOGLE_GEOCODE_SERVICE_URL);
        sb.append("?sensor=false");
        sb.append("&address=");
        try {
            sb.append(URLEncoder.encode(address + " " + zip, "utf-8"));
        } catch (UnsupportedEncodingException encodingEx) {
            Logger.getLogger(GeocodeUtil.class).error("Unable to create encoding address");
            throw new RuntimeException("Unable to encode address");
        }

        Logger.getLogger(GeocodeUtil.class).info("Geocoding using URL : " + sb.toString());
        
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(sb.toString());
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

            // Read the response body.
            BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            
            return convertGoogleXmlResponseToPoint(response.toString());

        } catch (HttpException e) {
            Logger.getLogger(GeocodeUtil.class).error("", e);
            throw new RuntimeException("HTTP Error");
        } catch (IOException e) {
            Logger.getLogger(GeocodeUtil.class).error("", e);
            throw new RuntimeException("Transport Error");
        } finally {
            method.releaseConnection();
        }
    }    
    
    private GeographicPoint convertGoogleXmlResponseToPoint(String xml) {
        String lat = findByXpathExpr(xml, "//GeocodeResponse/result/geometry/location/lat");
        String lon = findByXpathExpr(xml, "//GeocodeResponse/result/geometry/location/lng");
        
        GeographicPoint point = new GeographicPoint();
        point.setLatitude(Double.parseDouble(lat));
        point.setLongitude(Double.parseDouble(lon));

        return point;
    }
    
    private String findByXpathExpr(String xml, String expr) {
        InputSource source = new InputSource(new StringReader(xml));
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList list = null;
        try {
            return xPath.evaluate(expr, source);
        } catch (XPathExpressionException xpathEx) {
            Logger.getLogger(GeocodeUtil.class).error("", xpathEx);
            throw new RuntimeException("XPath Error");
        }
    }
}
