<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:import url="${pageContext.servletContext.contextPath}/static/header.jsp" />
<div class="content">
    <div style="background-color: WhiteSmoke; margin-bottom: 15px;">
        <div class="entity-title" style="float:left;">All City Sites</div>
        <input type="button" value="Create New City Site" class="btn" style="float:right;" onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/new';"/>
    </div>
    <table class="admin-list">
        <c:forEach var="cs" items="${citySites}">
            <tr class="admin-list-row">
                <td>
                    <a class="entity-list-link" href="<c:url value="/admin/edit/${cs.id}" />">${cs.name}</a> - ${cs.type}                    
                </td>
                <td valign="top" style="text-align:right;">
                    <input type="button" value="Edit" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/edit/${cs.id}';" />
                    <input type="button" value="View Map" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/map/${cs.id}';" />
                    <input type="button" value="View QR" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/qr/${cs.id}';" />       
                </td>
            </tr>
            <tr class="admin-list-row"">
                <td colspan="2" style="padding-bottom: 15px;">
                    <c:choose>
                        <c:when test="${cs.type eq 'External'}">
                            <a href="<c:url value="${cs.url}" />">${cs.url}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value="${pageContext.servletContext.contextPath}/site/${cs.id}/${fn:toLowerCase(cs.type)}" />">View Mobile Site</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            
        </c:forEach>
    </table>
</div>
<c:import url="${pageContext.servletContext.contextPath}/static/footer.jsp" />