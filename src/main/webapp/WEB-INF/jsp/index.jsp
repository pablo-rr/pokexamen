<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gotta catch 'em all!</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/cosmo/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<h1> Team </h1>
	<h3> Adopt a pokemon! </h3>
	<form:form action="pokemonInsert" method="post" modelAttribute="pokemon">
		<span>Pokemon name:</span>
		<form:input type="text" path="name" />
		<br />
		<span>Power:</span>
		<form:input type="number" path="power" />
		<br />
		<span>Health:</span>
		<form:input type="number" path="health" />
		<br />
		<input name="capture" type="submit" value="Throw pokeball" />
	</form:form>
	<c:out value="${pokeball.getMessage()}"></c:out>
	
	<br />
	
	<h1>_____________________________________________________________________</h1>
	<br />
	<h1> Tall grass </h1>
	<h3> Fight a wild pokemon! </h3>
	<form:form action="wildPokemon" method="post" modelAttribute="wildPokemon">
		<span>Pokemon name:</span>
		<form:input type="text" path="name" />
		<br />
		<span>Power:</span>
		<form:input type="number" path="power" />
		<br />
		<span>Health:</span>
		<form:input type="number" path="health" />
		<br />
		<input name="fight" type="submit" value="Fight wild pokemon" />
<!-- 		<input /> -->
	</form:form>
	
	<h1>_____________________________________________________________________</h1>
	<br />
	<h1>Battle</h1>
	<h3>Fight against a wild pokemon!</h3>
	
		<table border="1">
			<thead>
				<td colspan="6">Team: <c:out value="${pokemonTrainer.getTeam().getMemberCount()}"></c:out>/6</td>
			</thead>
			<tbody>
				<tr>
					<td><span>Name</span></td>
					<td><span>Power</span></td>
					<td><span>Health</span></td>
					<td><span>Out of pokeball</span></td>
					<td></td>
				</tr>
				<c:forEach var="pokemon" items="${pokemonTrainer.getTeam().getMembers()}">
					<tr>
						<td><c:out value="${pokemon.name}"/></td>
						<td><c:out value="${pokemon.power}"/></td>
						<td><c:out value="${pokemon.health}"/></td>
						<td><c:out value="${pokemon.currentFighter}"/></td>
						<td>
							<form:form action="changePokemon${pokemon.ID}" method="post" modelAttribute="pokemon">
								<input type="submit" value="Put out of pokeball"></input>
							</form:form>
						</td>
						<td>
							<form:form action="healPokemon${pokemon.ID}" method="post" modelAttribute="pokemon">
								<input type="submit" value="Use potion"></input>
							</form:form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
	</table>
	<br />
	<br /> 
	<br />
	<form:form action="battle${team.getCurrentPokemon().getPower()}-${wildPokemon.power}-${team.getCurrentPokemon().isDead()}-${wildPokemon.dead}" method="post">
		<input type="submit" value="Attack"></input>
	</form:form>
	
	<form:form action="wildCapture${wildPokemon.name}-${wildPokemon.power}-${wildPokemon.health}-${wildPokemon.captureHealth}" method="post" modelAttribute="wildCapt">
		<input type="submit" value="Capture"/></input>
	</form:form>
	<br />
	Pokemon: <span><c:out value="Wild ${wildPokemon.getName()}" /></span>
	<br /> 
	Power: <span><c:out value="${wildPokemon.power}" /></span>
	<br />
	Health: <span><c:out value="${wildPokemon.health}" /></span>
	<br />
	Capture health: <span><c:out value="${wildPokemon.captureHealth}" /></span>
	<br />
</body>
</html>