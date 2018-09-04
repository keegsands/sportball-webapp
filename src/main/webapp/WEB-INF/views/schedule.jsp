<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<title><fmt:message key="fullScheduleTitle" /></title>
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css">
</head>
<body class="center">
    <header>
        <c:if test="${empty team}">
            <h3>
                <fmt:message key="fullScheduleTitle" />
            </h3>
        </c:if>
    </header>
    <nav>
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="homeNavigation" /></a>&nbsp;<a href="${pageContext.request.contextPath}/standings"><fmt:message
                key="standingsNavigation"
            /></a>
    </nav>

    <section>
        <c:if test="${!empty listGames}">
            <table class="tg">
                <tr>
                    <th width="75"><fmt:message key="fullScheduleDate" /></th>
                    <th width="75"><fmt:message key="fullScheduleTime" /></th>
                    <th width="60"><fmt:message key="fullScheduleField" /></th>
                    <th width="120"><fmt:message key="fullScheduleAway" /></th>
                    <th width="60"><fmt:message key="fullScheduleRuns" /></th>
                    <th width="120"><fmt:message key="fullScheduleHome" /></th>
                    <th width="60"><fmt:message key="fullScheduleRuns" /></th>
                    <th width="60"><fmt:message key="fullScheduleStatus" /></th>
                </tr>
                <c:forEach items="${listGames}" var="game">
                    
                    <c:if test="${!game.fullScheduledDate.equals(theDate)}">
                        <c:set var="theDate" value="${game.fullScheduledDate}" scope="page"></c:set>
                        <tr>
                            <td colspan="8">${theDate}</td>
                        </tr>
                    </c:if>
                    <tr>
                        <td colspan="2" class="rightAlignTD">${game.gameTime.formattedTime}</td>
                        <td>${game.field.name}</td>
                        <c:if test="${game.complete && game.awayTeamRuns > game.homeTeamRuns}">
                            <td><b>${game.awayTeam.name}</b></td>
                            <td><b>${game.awayTeamRuns}</b></td>
                        </c:if>
                        <c:if test="${!game.complete || game.awayTeamRuns <= game.homeTeamRuns}">
                            <td>${game.awayTeam.name}</td>
                            <td>${game.awayTeamRuns}</td>
                        </c:if>
                        <c:if test="${game.complete && game.homeTeamRuns > game.awayTeamRuns}">
                            <td><b>${game.homeTeam.name}</b></td>
                            <td><b>${game.homeTeamRuns}</b></td>
                        </c:if>
                        <c:if test="${!game.complete || game.homeTeamRuns <= game.awayTeamRuns}">
                            <td>${game.homeTeam.name}</td>
                            <td>${game.homeTeamRuns}</td>
                        </c:if>
                        <td width="60">${game.shortStatus}</td>
                    </tr>

                </c:forEach>
            </table>
        </c:if>
    </section>

</body>
</html>