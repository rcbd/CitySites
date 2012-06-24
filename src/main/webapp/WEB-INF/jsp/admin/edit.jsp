<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="${pageContext.servletContext.contextPath}/static/header.jsp" />
<div class="content">
    <div class="admin-entity">
        <div class="entity-title">Editing City Site - ${citySite.name}</div>
        <form:form modelAttribute="citySite" action="${pageContext.servletContext.contextPath}/admin/save" method="POST">
            <p><form:errors path="*" /></p>
			<p>
				<form:label path="name" for="name">Site Name</form:label>
				<form:input path="name" id="name"/>
			</p>        
			<p>
				<form:label path="url" for="url">Site URL</form:label>
				<form:input path="url" id="url"/>
			</p>      
			<p>
				<form:label path="description" for="description">Description</form:label>
				<form:textarea path="description" id="description"/>
			</p>                  
			<p>
				<form:label path="type" for="type">Site Type</form:label>
                <form:select items="${csTypes}" path="type" id="type" />
			</p>            
            <p id="feed-url-container" <c:if test="${citySite.type != 'Live'}">style="display:none;"</c:if>>
				<form:label path="feedUrl" for="feedUrl">Live Feed URL</form:label>
                <form:input path="feedUrl" id="feedUrl" />
			</p>
			<p>
				<form:label path="address" for="address">Address Line 1</form:label>
                <form:input path="address" id="address"/>
			</p>          
			<p>
				<form:label path="address2" for="address2">Address Line 2</form:label>
                <form:input path="address2" id="address2"/>
			</p>    
			<p>
				<form:label path="city" for="city">City</form:label>
                <form:input path="city" id="city"/>
			</p>  
			<p>
                <label>State</label>
                ${citySite.state}
                <form:hidden path="state" id="state" />
			</p>  
			<p>
				<form:label path="zip" for="zip">Zip Code</form:label>
                <form:input path="zip" id="zip"/>
			</p>            
			<p>
                <input type="submit" value="Save City Site" class="btn" />
                <c:if test="${not empty citySite.id}">
                    <input type="button" value="View map for  ${citySite.name}" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/map/${citySite.id}';" />  
                    <input type="button" value="View QR code" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/qr/${citySite.id}';" />                
                </c:if>
                <input type="button" class="btn" onClick="document.location.href='${pageContext.servletContext.contextPath}/admin/list';" value="Back to list" />
			</p>    
            <c:if test="${not empty citySite.id}">
                <form:hidden path="id" id="id" />   
                <form:hidden path="latitude" id="latitude" />
                <form:hidden path="longitude" id="longitude" />
            </c:if>
        </form:form>
        <script>
            $('#type').change(function() {
                var val = $(this).find('option:selected').text();
                if (val == "Live")
                    $('#feed-url-container').show();
                else
                    $('#feed-url-container').hide();

            });
        </script>
    </div>
</div>
<c:import url="${pageContext.servletContext.contextPath}/static/footer.jsp" />