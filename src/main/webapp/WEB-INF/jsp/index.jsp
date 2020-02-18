<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title> Gotta catch 'em all! </title>
		<link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/cosmo/bootstrap.min.css"
			  rel="stylesheet">
	</head>
	<body>
	
	<h2> Insert Wild Pokemon Manually </h2><br />
	
		<form:form action="pokemonEvent" method="post" modelAttribute="pokemon">
			<span> Pokemon name: </span>
			<form:input type="text" path="name" /><br />
			<span> Power: </span>
			<form:input type="number" path="power" /><br />
			<span> Health: </span>
			<form:input type="number" path="health" /><br />
			<span> MaxHealth: </span>
			<form:input type="number" path="maxHealth" /><br />
			<input type="submit" value="Throw pokeball" />
		</form:form><br />
		
		
		<c:out value="${pokeball.getMessage()}"></c:out><br />
		
		<h2> Team </h2><br />
		
		<span> Team Pokemons: </span>
		<c:out value="${pokemonTrainer.getTeam().getMemberCount()}"></c:out><br />
		
		
		<table border="1">
			<thead>
				<tr>
					<td colspan="7" align="center"> Team </td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><span> Name </span></td>
					<td><span> Power </span></td>
					<td><span> Health </span></td>
					<td><span> MaxHealth </span></td>
					<td><span> Out of pokeball </span></td>
					<td colspan="2" align="center"><span> Actions </span></td>
				</tr>
				<c:forEach var="pokemon" items="${pokemonTrainer.getTeam().getMembers()}">
					<tr>
						<td align="center"><c:out value="${pokemon.name}"/></td>
						<td align="center"><c:out value="${pokemon.power}"/></td>
						<td align="center"><c:out value="${pokemon.health}"/></td>
						<td align="center"><c:out value="${pokemon.maxHealth}"/></td>
						<td align="center"><c:out value="${pokemon.currentFighter}"/></td>
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
		</table><br />
	
		
		<h2> Insert Wild Pokemon AUTOMATICALLY WOWOWOWOWOW (Necesito puntos Gorka) </h2><br />
		
		<form:form action="insertWildPokemon" method="get" modelAttribute="pokemon">
			<input type="submit" value="Find Wild Pokemon" />
		</form:form><br />
		 
		<c:out value="${team.getCurrentPokemon().getMessage()}"></c:out><br />
		<c:out value="${pokemon.getMessage()}"></c:out><br /><br />

		
		<span> Wild Pokemon: </span><br /><br />
		
		<table border="0">
			<tbody>
				<tr>
					<td><span> Name </span></td>
					<td><span> Power </span></td>
					<td><span> Health </span></td>
					<td><span> MaxHealth </span></td>
				</tr>
				<c:forEach var="pokemon" items="${wildEnemy.getTeam().getMembers()}">
					<tr>
						<td align="center"><c:out value="${pokemon.name}"/></td>
						<td align="center"><c:out value="${pokemon.power}"/></td>
						<td align="center"><c:out value="${pokemon.health}"/></td>
						<td align="center"><c:out value="${pokemon.maxHealth}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table><br /><br />
		
		<form:form action="battle${team.getCurrentPokemon().getPower()}-${enemyTeam.getCurrentPokemon().getPower()}" method="post">
			<input type="submit" value="Attack"></input>
		</form:form>
		<form:form action="catchWildPokemon" method="post" modelAttribute="pokemon">
			<input type="submit" value="Throw a pokeball"></input>
		</form:form><br /><br />
		
		<h2> PC </h2><br />
		
		<span> Pokemons in PC: </span>
		<c:out value="${pokemonTrainer.getTeam().getPC().getMemberCount()}"></c:out><br />
		
		
		<table border="0">
			<thead>
				<tr>
					<td colspan="6" align="center"> PC </td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><span> Name </span></td>
					<td><span> Power </span></td>
					<td><span> Health </span></td>
					<td></td>
				</tr>
				<c:forEach var="pokemon" items="${pokemonTrainer.getTeam().getPC().getMembers()}">
					<tr>
						<td><c:out value="${pokemon.name}"/></td>
						<td><c:out value="${pokemon.power}"/></td>
						<td><c:out value="${pokemon.health}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table><br /><br />
	</body>
</html>