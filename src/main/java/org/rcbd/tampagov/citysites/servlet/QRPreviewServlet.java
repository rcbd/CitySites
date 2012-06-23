/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
public class QRPreviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        String qrtext = request.getParameter("qr");
 
        ByteArrayOutputStream out = QRCode.from(qrtext).withSize(500,500).to(ImageType.PNG).stream();
         
        response.setContentType("image/png");
        response.setContentLength(out.size());
         
        OutputStream outStream = response.getOutputStream();
 
        outStream.write(out.toByteArray());
 
        outStream.flush();
        outStream.close();
    }
    
}
