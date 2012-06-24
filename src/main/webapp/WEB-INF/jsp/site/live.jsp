<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="${pageContext.servletContext.contextPath}/static/mobile-header.jsp" />
<div id="mobile-main">
    <div class="mobile-content-well mobile-content-well">
        <div class="mobile-entity-title">${citySite.name}</div>
        <div class="mobile-entity-desc">${citySite.description}</div>
    </div>
    <div id="mobile-live-feed" class="mobile-content-well">
        <div class="mobile-entity-title">The latest from ${citySite.name}</div>
        <div class="mobile-entity-desc">
            <div class="mobile-feed-subtitle">${liveFeed.title}</div>
            <div class="mobile-feed-entries">
                <c:forEach var="entry" items="${liveFeed.entries}" begin="0" end="4">
                    <div class="mobile-feed-entry">
                        <div class="mobile-feed-title"><a href="<c:url value="${entry.link}" />">${entry.title}</a></div>
                        <div class="mobile-feed-date">${entry.publishedDate}</div>
                        <div class="mobile-feed-desc">${entry.description}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <c:import url="/WEB-INF/jsp/site/services/mobile-sites-near.jsp" />
</div>
<c:import url="${pageContext.servletContext.contextPath}/static/mobile-footer.jsp" />