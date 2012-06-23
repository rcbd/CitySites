<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="${pageContext.servletContext.contextPath}/static/header.jsp" />
<div class="content">
    <div class="admin-entity">
        <div class="entity-title">Location for ${citySite.name}</div>
        <div id="entity-gmap">Initializing...</div>
    </div>
    <p>
        <input type="button" value="Edit ${citySite.name}" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/edit/${citySite.id}';" />
        <input type="button" value="View QR code" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/qr/${citySite.id}';" />                
        <input type="button" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/list';" value="Back to list" />
    </p>            
    <script>
        $(document).ready(function() {
            var latLon = new google.maps.LatLng(${citySite.latitude}, ${citySite.longitude});
            var mapOpts = { center: latLon, zoom: 17, mapTypeId: google.maps.MapTypeId.ROADMAP };
            var map = new google.maps.Map(document.getElementById("entity-gmap"), mapOpts);
            var marker = new google.maps.Marker({ position: latLon, map: map, title: "${citySite.name}"});
            var infoWnd = new google.maps.InfoWindow({ content: "${citySite.name}" });
            google.maps.event.addListener(marker, 'click', function() {
                infoWnd.open(map,marker);
            });            
        });
    </script>
  
</div>
<c:import url="${pageContext.servletContext.contextPath}/static/footer.jsp" />