<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="${pageContext.servletContext.contextPath}/static/mobile-header.jsp" />
<div id="mobile-main">
    <div class="mobile-content-well mobile-content-well">
        <div class="mobile-entity-title">${citySite.name}</div>
        <div class="mobile-entity-desc">${citySite.description}</div>
    </div>
    <div id="mobile-reservations" class="mobile-content-well">
        <div class="mobile-entity-title">Reserve this pavilion</div>
        <ul>
            <c:forEach var="available" items="${availableDates}">
                <li><a href="<c:url value="${pageContext.servletContext.contextPath}/reservations/create/${citySite.id}/${available}" />">${available} is available</a></li>
            </c:forEach>
        </ul>
    </div>
    <c:import url="/WEB-INF/jsp/site/services/mobile-sites-near.jsp" />
</div>
<c:import url="${pageContext.servletContext.contextPath}/static/mobile-footer.jsp" />