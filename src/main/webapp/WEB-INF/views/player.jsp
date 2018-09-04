<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<title>Player Page</title>
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css">
</head>
<body>

    <header><h3><fmt:message key="playerTeam" /></h3></header>
    <nav>
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="homeNavigation"/></a>&nbsp;<a href="${pageContext.request.contextPath}/schedule"><fmt:message key="fullScheduleNavigation"/></a>&nbsp;<a href="${pageContext.request.contextPath}/standings"><fmt:message key="standingsNavigation"/></a>
    </nav>

    <section>
        <c:if test="${!empty listPlayers}">
            <table class="tg">
                <tr>
                	<th width="120"><fmt:message key="playerTeam" /></th>
                    <th width="120"><fmt:message key="playerFirstName" /></th>
                    <th width="120"><fmt:message key="playerLastName" /></th>
                    <th width="75"><fmt:message key="playerGender" /></th>
                    <th width="120"><fmt:message key="playerStatus" /></th>
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </tr>
                <c:forEach items="${listPlayers}" var="player">
                    <tr>
                        <td>${player.team.name}</td>
                        <td>${player.firstName}</td>
                        <td>${player.lastName}</td>
                        <td>${player.gender}</td>
                        <td>${player.status}</td>
                        <td><a href="<c:url value='/admin/player/edit/${player.id}' />">Edit</a></td>
                        <td><a href="<c:url value='/admin/player/remove/${player.id}' />">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <br />
        <c:url var="addAction" value="/admin/player/add"></c:url>

        <form:form action="${addAction}" commandName="player">
            <form:hidden path="id" />
            <table>

                <tr>
                    <td><form:label path="team.name">
                            <fmt:message key="playerTeam" />
                        </form:label></td>
                    <td><form:select path="team">
                            <form:option value="-1" label="--Select a Team--" />
                            <form:options items="${listTeams}" itemValue="id" itemLabel="name" />
                        </form:select></td>
                </tr>
                <tr>
                	<td><form:label path="firstName">
                            <fmt:message key="playerFirstName" />
                        </form:label></td>
                    <td><form:input path="firstName" /></td>
                </tr>
                <tr>
                    <td><form:label path="lastName">
                            <fmt:message key="playerLastName" />
                        </form:label></td>
                    <td><form:input path="lastName" /></td>
                </tr>
                <tr>                    
                    <td><form:label path="gender">
                            <fmt:message key="playerGender" />
                        </form:label></td>
                    <td><form:input path="gender" /></td>
                </tr>
                <tr>
                    <td colspan="2"><c:if test="${!empty player.firstName}">
                            <input type="submit" value="<spring:message text="Edit Player"/>" />
                        </c:if> <c:if test="${empty player.firstName}">
                            <input type="submit" value="<spring:message text="Add Player"/>" />
                        </c:if></td>
                </tr>
            </table>
        </form:form>
    </section>

</body>
</html>