<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="${pageContext.servletContext.contextPath}/static/mobile-header.jsp" />
<div id="mobile-main">
    <div class="mobile-content-well">
        <div class="mobile-entity-title">${citySite.name}</div>
        <div class="mobile-service-form">
            <p>Congratulations! Your pavilion has been reserved on <fmt:formatDate pattern="MMMM d, yyyy" value="${reservation.reservationDate}" /></p>
            <p>Should this have been an actual reservation, you would have paid online and would be getting a confirmation e-mail right now.</p>
            <p>Thank you for support the City of Tampa Parks and Recreation</p>
            <p><input type="button" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/site/${citySite.id}/${fn:toLowerCase(citySite.type)}';" value="Go back to ${citySite.name}" />
        </div>
    </div>
</div>
<c:import url="${pageContext.servletContext.contextPath}/static/mobile-footer.jsp" />
