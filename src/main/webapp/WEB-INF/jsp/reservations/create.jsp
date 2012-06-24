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
            <form:form modelAttribute="reservation" action="${pageContext.servletContext.contextPath}/reservations/save" method="POST">
                <p><form:errors path="*" /></p>
                <p>
                    <form:label path="reservationDate" for="reservationDate">Reservation Date</form:label>
                    <form:hidden path="reservationDate" id="reservationDate"/>
                    <fmt:formatDate pattern="MMMM d, yyyy" value="${reservation.reservationDate}" />
                </p> 
                <p>
                    <form:label path="firstName" for="firstName">First Name</form:label>
                    <form:input path="firstName" id="firstName"/>
                </p>     
                <p>
                    <form:label path="lastName" for="lastName">Last Name</form:label>
                    <form:input path="lastName" id="lastName"/>
                </p>  
                <p>
                    <form:label path="email" for="email">Email</form:label>
                    <form:input path="email" id="email"/>
                </p>  
                <p>
                    <form:label path="phone" for="phone">Phone</form:label>
                    <form:input path="phone" id="phone"/>
                </p>  
                <p>
                    <input type="submit" value="Save City Site" class="btn" />
                    <input type="button" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/site/${citySite.id}/${fn:toLowerCase(citySite.type)}';" value="Cancel" />
                </p>

                <input type="hidden" name="citySiteId" id="citySiteId" value="${citySite.id}" />
            </form:form>            
        </div>
    </div>
</div>
<c:import url="${pageContext.servletContext.contextPath}/static/mobile-footer.jsp" />
