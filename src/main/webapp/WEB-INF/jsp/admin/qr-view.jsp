<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="${pageContext.servletContext.contextPath}/static/header.jsp" />
<div class="content">
    <div class="admin-entity">
        <div class="entity-title">QR Code for ${citySite.name}</div>
        <div align="center">
            <img src="<c:url value="/qrpreview?qr=${qrFile}" />" border="0" height="500" width="500" />
        </div>
    </div>
    <p>
        <input type="button" value="Edit ${citySite.name}" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/edit/${citySite.id}';" />
        <input type="button" value="View map for  ${citySite.name}" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/map/${citySite.id}';" />  
        <input type="button" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/list';" value="Back to list" />
    </p>         
</div>
<c:import url="${pageContext.servletContext.contextPath}/static/footer.jsp" />