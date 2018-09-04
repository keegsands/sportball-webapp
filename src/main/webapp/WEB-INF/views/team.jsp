<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<title>Team Page</title>
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css">
</head>
<body>

    <header><h3>Teams</h3></header>
    <nav>
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="homeNavigation"/></a>&nbsp;<a href="${pageContext.request.contextPath}/schedule"><fmt:message key="fullScheduleNavigation"/></a>&nbsp;<a href="${pageContext.request.contextPath}/standings"><fmt:message key="standingsNavigation"/></a>
    </nav>

    <section>
        <c:if test="${!empty listTeams}">
            <table class="tg">
                <tr>
                    <th width="120">Team Name</th>
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </tr>
                <c:forEach items="${listTeams}" var="team">
                    <tr>
                        <td>${team.name}</td>
                        <td><a href="<c:url value='/admin/team/edit/${team.id}' />">Edit</a></td>
                        <td><a href="<c:url value='/admin/team/remove/${team.id}' />">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <br />
        <c:url var="addAction" value="/admin/team/add"></c:url>

        <form:form action="${addAction}" commandName="team">
            <form:hidden path="id" />
            <table>
                <tr>
                    <td><form:label path="name">
                            <spring:message text="Name" />
                        </form:label></td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td colspan="2"><c:if test="${!empty team.name}">
                            <input type="submit" value="<spring:message text="Edit Team"/>" />
                        </c:if> <c:if test="${empty team.name}">
                            <input type="submit" value="<spring:message text="Add Team"/>" />
                        </c:if></td>
                </tr>
            </table>
        </form:form>
    </section>
</body>
</html>