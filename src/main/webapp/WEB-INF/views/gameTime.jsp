<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<title>GameTime Page</title>
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css">
</head>
<body>

    <header><h3>Game Times</h3></header>
    <nav>
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="homeNavigation"/></a>&nbsp;<a href="${pageContext.request.contextPath}/schedule"><fmt:message key="fullScheduleNavigation"/></a>&nbsp;<a href="${pageContext.request.contextPath}/standings"><fmt:message key="standingsNavigation"/></a>
    </nav>

    <section>
        <c:if test="${!empty listGameTimes}">
            <table class="tg">
                <tr>
                    <th width="120">Game Time</th>
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </tr>
                <c:forEach items="${listGameTimes}" var="gameTime">
                    <tr>
                        <td>${gameTime.timeSlot}</td>
                        <td><a href="<c:url value='/admin/gameTime/edit/${gameTime.id}' />">Edit</a></td>
                        <td><a href="<c:url value='/admin/gameTime/remove/${gameTime.id}' />">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <br />
        <c:url var="addAction" value="/admin/gameTime/add"></c:url>

        <form:form action="${addAction}" commandName="gameTime">
            <form:hidden path="id" />
            <table>
                <tr>
                    <td><form:label path="timeSlot">
                            <spring:message text="Time" />
                        </form:label></td>
                    <td><form:input path="timeSlot" /></td>
                </tr>
                <tr>
                    <td colspan="2"><c:if test="${!empty gameTime.timeSlot}">
                            <input type="submit" value="<spring:message text="Edit Game Time"/>" />
                        </c:if> <c:if test="${empty gameTime.timeSlot}">
                            <input type="submit" value="<spring:message text="Add Game Time"/>" />
                        </c:if></td>
                </tr>
            </table>
        </form:form>
    </section>

</body>
</html>