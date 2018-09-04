<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<title>Schedule</title>
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <h3>${team.name}&nbsp;Schedule</h3>
    </header>
    <nav>
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="homeNavigation"/></a>&nbsp;<a href="${pageContext.request.contextPath}/schedule"><fmt:message key="fullScheduleNavigation"/></a>&nbsp;<a href="${pageContext.request.contextPath}/standings"><fmt:message key="standingsNavigation"/></a>
    </nav>

    <section>
        <c:if test="${!empty listGames}">
            <table class="tg">
                <tr>
                    <th width="140"><fmt:message key="teamScheduleDate" /></th>
                    <th width="75"><fmt:message key="teamScheduleTime" /></th>
                    <th width="60"><fmt:message key="teamScheduleField" /></th>
                    <th width="120"><fmt:message key="teamScheduleOpponent" /></th>
                    <th width="60"><fmt:message key="teamScheduleResult" /></th>
                    <th width="60"><fmt:message key="teamScheduleStatus" /></th>
                </tr>
                <c:forEach items="${listGames}" var="game">
                    <tr>
                        <td>${game.fullScheduledDate}&nbsp;</td>
                        <td>${game.gameTime.formattedTime}</td>
                        <td>${game.field.name}</td>
                        <c:if test="${game.homeTeam.id == team.id}">
                            <td>${game.awayTeam.name}</td>
                        </c:if>
                        <c:if test="${game.awayTeam.id == team.id}">
                            <td>@${game.homeTeam.name}</td>
                        </c:if>
                        <c:if
                            test="${game.complete && 
                            ((game.awayTeam.id == team.id && game.awayTeamRuns > game.homeTeamRuns) 
                            || (game.homeTeam.id == team.id && game.homeTeamRuns > game.awayTeamRuns))}">
                            <td><fmt:message key="teamScheduleWin" />&nbsp;${game.hyphenScore}</td>
                        </c:if>
                        <c:if
                            test="${game.complete && 
                            game.awayTeamRuns == game.homeTeamRuns}">
                            <td><fmt:message key="teamScheduleTie" />&nbsp;${game.hyphenScore}</td>
                        </c:if>
                        <c:if
                            test="${game.complete && 
                            ((game.awayTeam.id == team.id && game.awayTeamRuns < game.homeTeamRuns) 
                            || (game.homeTeam.id == team.id && game.homeTeamRuns < game.awayTeamRuns))}">
                            <td><fmt:message key="teamScheduleLoss" />&nbsp;${game.hyphenScore}</td>
                        </c:if>
                        <c:if test="${!game.complete}">
                            <td></td>
                        </c:if>
                        <td>${game.status}</td>
                    </tr>

                </c:forEach>
            </table>
        </c:if>
    </section>

</body>
</html>