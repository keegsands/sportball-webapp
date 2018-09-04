<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page session="false"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
	<title><fmt:message key="leagueName" />&nbsp;<fmt:message key="standingsHeader" /></title>
	<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">

   		function seasonSelectorChange(seasonId) {
    		window.location =   "${pageContext.request.contextPath}/standings/" + seasonId;
   		}
  	</script>
</head>
<body>
    <header>
        <h3>
            <fmt:message key="leagueName" />
            &nbsp;${season.name}&nbsp;
            <fmt:message key="standingsHeader" />
        </h3>
    </header>
    <nav>
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="homeNavigation" /></a> <a href="${pageContext.request.contextPath}/schedule"><fmt:message
                key="fullScheduleNavigation"
            /></a>
    </nav>
    <section>
    	
   		<form:select path="season.id" onchange="seasonSelectorChange(value);">
                           <form:option value="-1" label="--Select a Season--" />
                           <form:options items="${listSeasons}" itemValue="id" itemLabel="name" />
        </form:select>
     		
       	
        <c:if test="${!empty standings}">
            <table class="tg">
                <thead>
                    <tr>
                        <th width="120"><fmt:message key="standingsColumnTeam" /></th>
                        <th width="60"><fmt:message key="standingsColumnWins" /></th>
                        <th width="60"><fmt:message key="standingsColumnLosses" /></th>
                        <th width="60"><fmt:message key="standingsColumnTies" /></th>
                        <th width="60"><fmt:message key="standingsColumnWinPct" /></th>
                        <th width="60"><fmt:message key="standingsColumnGamesBack" /></th>
                        <th width="60"><fmt:message key="standingsStreak" /></th>
                    </tr>
                </thead>
                <tbody>
                
                    <c:forEach items="${standings}" var="standing" varStatus="ctr">
                    	<c:if test="${!standing.campaign.division.id.equals(theDivision) && multiDivision}">
                        	<c:set var="theDivision" value="${standing.campaign.division.id}" scope="page"></c:set>
                        	<tr>
                            	<td colspan="7">${standing.campaign.division.confDivName}</td>
                        	</tr>
                   		</c:if>
                        <tr
                        <c:if test="${ctr.count>40}">
                             class="outOfPostSeason"
                        </c:if> >

                        <td><a href="${pageContext.request.contextPath}/schedule/${standing.team.id}">${standing.team.name}</a></td>
                        <td>${standing.wins}</td>
                        <td>${standing.losses}</td>
                        <td>${standing.ties}</td>
                        <td>${standing.formattedWinningPercent}</td>
                        <td>${standing.gamesBack}</td>
                        <td>${standing.streakText}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p class="footnote"><fmt:message key="standingsTieFootnote"/></p>
        </c:if>
    </section>
</body>
</html>