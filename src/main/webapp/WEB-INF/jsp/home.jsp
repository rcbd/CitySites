<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>City Sites</title>
        <style type="text/css">
            html { height: 100% }
            body { height: 100%; margin: 0; padding: 0 }              
            #home-btn-panel { height:100%; border-radius: 5px; -moz-border-radius: 5px; border: 1px inset #999; margin: 10px; padding: 15px 5px; background-color: WhiteSmoke; text-align: center; }
            #home-btn-panel button { width: 175px; height: 50px; margin-bottom: 10px; font-weight: bold; font-size: 24px; }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAkgLLHkTQvRsZbnipeeP_F5sNNdHek1o4&sensor=false"></script>
    </head>
    <body>
        <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
            <tr><td style="width:250px;padding:10px;background-color:#0F5985;" valign="top">
                    <div id="home-btn-panel">
                        <button onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/'">Admin</button>
                        <button onClick="document.location.href='http://tinyurl.com/hackathoncitysitesdemo';">Demo</button>
                    </div>
                </td>
                <td id="map-container"><div id="map_canvas" style="height:100%;"></div></td>
            </tr>
        </table>
<!--        <div id="home_buttons"style="background-color:Blue; width: 150px; height: 100%; float:left;">
            btns go here
        </div>
        <div id="map_canvas" style="width: 90%; height: 100%; background-color: WhiteSmoke; float:left;">Loading Map...</div>-->
        <script>
            var myOptions = {
                center: new google.maps.LatLng(27.950575, -82.4571776),
                zoom: 12,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };        
            
            $(document).ready(function() {
                $('#map_canvas').css('width',$('#map-container').width() + 'px').css('height',$('#map-container').height() + 'px');
                var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);            
                
                <c:forEach var="cs" items="${sites}">
                    var mrk${cs.id} = new google.maps.Marker({ 
                        position: new google.maps.LatLng(${cs.latitude},${cs.longitude}), 
                        map: map, 
                        title: "${cs.name}" 
                    });
                    var iw${cs.id} = new google.maps.InfoWindow({
                        content: "${cs.name}<br /><a href=\"${pageContext.servletContext.contextPath}/site/${cs.id}/${fn:toLowerCase(cs.type)}\">View City Site</a><br /><a href=\"${pageContext.servletContext.contextPath}/admin/edit/${cs.id}\">Edit City Site</a>"
                    });
                    google.maps.event.addListener(mrk${cs.id}, 'click', function() {
                        iw${cs.id}.open(map,mrk${cs.id});
                    });
                </c:forEach>
            });
            
        </script>        
    </body>

</html>
