<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<title>Game Page</title>
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css">
</head>
<body>

    <header>
        <h3>Games</h3>
    </header>
    <nav>
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="homeNavigation" /></a>&nbsp;<a href="${pageContext.request.contextPath}/schedule"><fmt:message
                key="fullScheduleNavigation"
            /></a>&nbsp;<a href="${pageContext.request.contextPath}/standings"><fmt:message key="standingsNavigation" /></a>
    </nav>

    <section>
        <c:if test="${!empty listGames}">
            <table class="tg">
                <tr>
                    <th width="120"><fmt:message key="gamesDateTime" /></th>
                    <th width="60"><fmt:message key="gamesField" /></th>
                    <th width="120"><fmt:message key="gamesAwayTeam" /></th>
                    <th width="60"><fmt:message key="gamesRuns" /></th>
                    <th width="120"><fmt:message key="gamesHomeTeam" /></th>
                    <th width="60"><fmt:message key="gamesRuns" /></th>
                    <th width="60"><fmt:message key="gamesStatus" /></th>
                    <th width="60"><fmt:message key="gamesEdit" /></th>
                    <th width="60"><fmt:message key="gamesDelete" /></th>
                </tr>
                <c:forEach items="${listGames}" var="game">
                    <tr>
                        <td>${game.formattedScheduledDate}&nbsp;${game.gameTime.formattedTime}</td>
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
                        <td><a href="<c:url value='/admin/game/edit/${game.id}' />">Edit</a></td>
                        <td><a href="<c:url value='/admin/game/remove/${game.id}' />">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <br />
        <c:url var="addAction" value="/admin/game/add"></c:url>

        <form:form action="${addAction}" commandName="game">
            <form:hidden path="id" />
            <table>
                <tr>
                    <td><form:label path="season.name">
                            <fmt:message key="gamesSeason" />
                        </form:label></td>
                    <td><form:select path="season">
                            <form:option value="-1" label="--Select a Season--" />
                            <form:options items="${listSeasons}" itemValue="id" itemLabel="name" />
                        </form:select></td>
                </tr>
                <tr>
                    <td><form:label path="scheduledDate">
                            <fmt:message key="gamesScheduledDate" />
                        </form:label></td>
                    <td><form:input path="scheduledDate" type="date" /></td>
                </tr>
                <tr>
                    <td><form:label path="originalDate">
                            <fmt:message key="gamesOriginalDate" />
                        </form:label></td>
                    <td><form:input path="originalDate" type="date" /></td>
                </tr>
                <tr>
                    <td><form:label path="gameTime.timeSlot">
                            <fmt:message key="gamesGameTime" />
                        </form:label></td>
                    <td><form:select path="gameTime">
                            <form:option value="-1" label="--Select a Time--" />
                            <form:options items="${listTimes}" itemValue="id" itemLabel="formattedTime" />
                        </form:select></td>
                </tr>
                <tr>
                    <td><form:label path="awayTeam.name">
                            <fmt:message key="gamesAwayTeam" />
                        </form:label></td>
                    <td><form:select path="awayTeam">
                            <form:option value="-1" label="--Select a Team--" />
                            <form:options items="${listTeams}" itemValue="id" itemLabel="name" />
                        </form:select></td>
                </tr>
                <tr>
                    <td><form:label path="homeTeam.name">
                            <fmt:message key="gamesHomeTeam" />
                        </form:label></td>
                    <td><form:select path="homeTeam">
                            <form:option value="-1" label="--Select a Team--" />
                            <form:options items="${listTeams}" itemValue="id" itemLabel="name" />
                        </form:select></td>
                </tr>
                <tr>
                    <td><form:label path="field.name">
                            <fmt:message key="gamesField" />
                        </form:label></td>
                    <td><form:select path="field">
                            <form:option value="-1" label="--Select a Field--" />
                            <form:options items="${listFields}" itemValue="id" itemLabel="name" />
                        </form:select></td>
                </tr>
                <tr>
                    <td><form:label path="awayTeamRuns">
                            <fmt:message key="gamesAwayTeamRuns" />
                        </form:label></td>
                    <td><form:input path="awayTeamRuns" type="number" /></td>
                </tr>
                <tr>
                    <td><form:label path="homeTeamRuns">
                            <fmt:message key="gamesHomeTeamRuns" />
                        </form:label></td>
                    <td><form:input path="homeTeamRuns" type="number" /></td>
                </tr>
                <tr>
                    <td><form:label path="status">
                            <fmt:message key="gamesStatus" />
                        </form:label></td>
                    <td><form:select path="status">
                            <form:option value="" label="--Select a Status--" />
                            <form:options items="${listStatus}" itemValue="value" itemLabel="value" />
                        </form:select></td>
                </tr>
                <tr>
                    <td><form:label path="complete">
                            <fmt:message key="gamesGameComplete" />
                        </form:label></td>
                    <td><form:checkbox path="complete" /></td>
                </tr>
                <tr>
                    <td colspan="2"><c:if test="${!empty id}">
                            <input type="submit" value="Submit Edits" />
                        </c:if> <c:if test="${empty id}">
                            <input type="submit" value="Add Game" />
                        </c:if></td>
                </tr>
            </table>
        </form:form>
    </section>
</body>
</html>