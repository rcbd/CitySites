<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="mobile-sites-near" class="mobile-content-well">
    <div class="mobile-entity-title">
        Find other places within 
        <select id="near-radius">
            <option value="1">1 mile</option>
            <option value="2">2 miles</option>
            <option value="5">5 miles</option>
            <option value="10" selected="selected">10 miles</option>
            <option value="15">15 miles</option>
            <option value="25">25 miles</option>
        </select>
        of 
        <select id="near-me-type"><option value="me">Me</option><option selected="selected" value="site">${citySite.name}</option></select>
        <button id="near-btn">Go</button>
    </div>
    <div id="near-me-content" style="display:none;"></div>
    
    <script>
        
        var personal_lat = 0;
        var personal_lon = 0;
        var site_lat = ${citySite.latitude};
        var site_lon = ${citySite.longitude};
        
        function getCoordinates() {
            var coordOption = $(':selected', $('#near-me-type')).val();
            if (coordOption == 'me' && personal_lat < 1) {
                if (navigator.geolocation) {
                    var timeoutVal = 10 * 1000 * 1000;
                    navigator.geolocation.getCurrentPosition(
                        getMyPosition, 
                        displayError,
                        { enableHighAccuracy: true, timeout: timeoutVal, maximumAge: 0 }
                    );
                }                
            }
        }
        
        function getMyPosition(position) {
            personal_lat = position.coords.latitude;
            personal_lon = position.coords.longitude;
            getNearbySites();
        }
        
        function displayError(error) {
            var errors = { 
                1: 'Permission denied',
                2: 'Position unavailable',
                3: 'Request timeout'
            };
            alert("Error: " + errors[error.code]);
        }

        $("#near-btn").bind('click', function() {
            var coordOption = $(':selected', $('#near-me-type')).val();
            if (coordOption == 'me' && personal_lat < 1) {
                getCoordinates();
            } else {
                getNearbySites();
            }
        });
        
        function getNearbySites() {
            var coordOption = $(':selected', $('#near-me-type')).val();
            var distanceOption = $(':selected', $('#near-radius')).val();
            var lat = 0, lon = 0;
            if (coordOption == 'me') {
                lat = personal_lat;
                lon = personal_lon;
            } else {
                lat = site_lat;
                lon = site_lon;
            }
            var nearJsonUrl = "${pageContext.servletContext.contextPath}/site/near/" + lat + "/" + lon + "/" + distanceOption;
            $('#near-me-content').html('<img src="${pageContext.servletContext.contextPath}/static/img/loading.gif" style="vertical-align:middle;" /> Contacting the Mayor\'s office').show();
            $.getJSON(nearJsonUrl, function(resp) {
                var _content = $('<ul/>');
                for (var i = 0; i < resp.length; i++) {
                    if (resp[i].id != "${citySite.id}") {
                        _content.append(
                            $('<li/>').html('<a href="${pageContext.servletContext.contextPath}/site/' + resp[i].id + '">' + resp[i].name + '</a> ' + resp[i].distanceAway + ' miles away')
                        );
                    }
                }
                $('#near-me-content').html(_content);
            });             
        }
        
        $(document).ready(function() {
            getNearbySites();
        });
    </script>
    
</div>