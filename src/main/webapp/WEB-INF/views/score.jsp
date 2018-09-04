<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<title><fmt:message key="scoresTitle" /></title>
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <h3>
            <fmt:message key="scoresTitle" />
        </h3>
    </header>
    <nav>
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="homeNavigation" /></a>&nbsp;<a href="${pageContext.request.contextPath}/schedule"><fmt:message
                key="fullScheduleNavigation"
            /></a>&nbsp;<a href="${pageContext.request.contextPath}/standings"><fmt:message key="standingsNavigation" /></a>
    </nav>
    <!-- <c:if test="${pageContext.request.userPrincipal.name != null}">
       <h2>Welcome : ${pageContext.request.userPrincipal.name} 
           | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a></h2>  
    </c:if> -->
    <section>
        <c:if test="${!empty listGames}">
            <table class="noBorder">
                <c:forEach items="${listGames}" var="game">
                    <tr>
                        <td>
                            <table class="tg">
                                <tr>
                                    <td width="60">${game.formattedScheduledDate}</td>
                                    <td width="120">${game.awayTeam.name}</td>
                                    <td width="40">${game.awayTeamRuns}</td>
                                    <td width="60">${game.shortStatus}</td>
                                </tr>
                                <tr>
                                    <td width="40">${game.gameTime.formattedTime}</td>
                                    <td width="120">${game.homeTeam.name}</td>
                                    <td width="40">${game.homeTeamRuns}</td>
                                    <td width="60"><a href="<c:url value='/scores/edit/${game.id}' />"><fmt:message key="scoresUpdateLink" /></a></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <br />
        <c:url var="updateAction" value="/scores/edit"></c:url>

        <c:if test="${empty listGames}">
            <form:form action="${updateAction}" commandName="game">
                <form:hidden path="id" />
                <table>
                    <tr>
                        <td>${game.formattedScheduledDate}</td>
                        <td>${game.gameTime.formattedTime}</td>
                    </tr>
                    <tr>
                        <td>${game.awayTeam.name}</td>
                        <td><form:input path="awayTeamRuns" type="number" /></td>
                    </tr>
                    <tr>
                        <td>${game.homeTeam.name}</td>
                        <td><form:input path="homeTeamRuns" type="number" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="status">
                                <fmt:message key="scoresStatus" />
                            </form:label></td>
                        <td><form:select path="status">
                                <form:option value="" label="--Select a Status--" />
                                <form:options items="${listStatus}" itemValue="value" itemLabel="value" />
                            </form:select></td>
                    </tr>
                    <tr>
                        <td><form:label path="complete">
                                <fmt:message key="scoresGameComplete" />
                            </form:label></td>
                        <td><form:checkbox path="complete" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="<fmt:message key="scoresUpdateScoreButton"/>" /></td>
                    </tr>
                </table>
            </form:form>
        </c:if>
    </section>
</body>
</html>