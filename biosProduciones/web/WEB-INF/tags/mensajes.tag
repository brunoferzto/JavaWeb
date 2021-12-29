<%-- 
    Document   : mensaje
    Created on : 03-jul-2020, 16:13:24
    Author     : Bruno
--%>

<%@tag description="Párrafo de mensaje." pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<%-- any content can be specified here e.g.: --%>
<c:if test="${not empty mensaje}">
    <p class="<c:if  test="${fn:contains(mensaje, '¡ERROR!')}">error</c:if>">${mensaje}</p>
</c:if>