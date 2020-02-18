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

<%-- 	<form:form action="insertTrainer" method="post" modelAttribute="pokemonTrainer" > --%>
<!-- 		<span>Poketrainer name:</span> -->
<%-- 		<form:input type="text" path="name" /> --%>
<!-- 		<br /> -->
<!-- 		<span>Is gym owner?:</span> -->
<%-- 		<form:checkbox path="gymLeader" /> --%>
<!-- 		<br /> -->
<!-- 		<input type="submit" value="Save trainer" /> -->
<%-- 	</form:form> --%>
<!-- 	<br /> -->
<!-- 	<br />  -->
<%-- 	Trainer: <span><c:out value="${pokemonTrainer.name}" /></span> --%>
<!-- 	<br />  -->
<%-- 	Gym owner: <span><c:out value="${pokemonTrainer.gymLeader}" /></span> --%>
<!-- 	<br /> -->
<!-- 	<br /> -->
<!-- 	<br /> -->

	<h1> Capture form </h1>
	<form:form action="pokemonEvent" method="post" modelAttribute="pokemon">
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
	
	<h1> Fight form </h1>
	<form:form action="pokemonEvent" method="post" modelAttribute="pokemon">
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
	</form:form>
	
	<br />
	<c:out value="${pokeball.getMessage()}"></c:out>
	<br /><br /><br />
	<span>Team members: </span><c:out value="${pokemonTrainer.getTeam().getMemberCount()}"></c:out>
	<br />
	<table border="1">
			<thead>
				<td colspan="6">Team</td>
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
<%-- 	<form:form action="wildPokemon" method="post" modelAttribute="wildPokemon"> --%>
<!-- 		<input type="submit" value="Explore grass"></input> -->
<%-- 	</form:form> --%>
	<h1>Battle</h1>
	<br />
	<form:form action="battle${team.getCurrentPokemon().getPower()}-${wildPokemon.power}" method="post">
		<input type="submit" value="Attack"></input>
	</form:form>
	<br /> 
	<c:out value="${team.getCurrentPokemon().getMessage()}"></c:out>
	<c:out value="${pokemon.getMessage()}"></c:out>
	<br />
	Pokemon: <span><c:out value="Wild ${wildPokemon.name}" /></span>
	<br /> 
	Power: <span><c:out value="${wildPokemon.power}" /></span>
	<br />
	Health: <span><c:out value="${wildPokemon.health}" /></span>
	<br />
	<br />


<%-- 	<form:form action="insert" method="post" modelAttribute="person"> --%>
<!-- 		<span>nuevo item:</span> -->
<!-- 		<span>nombre:</span> -->
<%-- 		<form:input type="text" path="item.name" /> --%>
<!-- 		<span>peso:</span> -->
<%-- 		<form:input type="text" path="item.peso" /> --%>
<!-- 		<span>tipo:</span> -->
<%-- 		<form:input type="text" path="item.type" /> --%>
<!-- 		<input type="submit" value="guardar item" /> -->
<%-- 	</form:form> --%>
<!-- 	<table border="1"> -->
<!-- 		<thead> -->
<!-- 			<tr> -->
<!-- 				<td>Mochila</td> -->
<!-- 				<td>peso</td> -->
<!-- 				<td>tipo</td> -->
<!-- 			</tr> -->
<!-- 		</thead> -->
<!-- 		<tbody> -->
<%-- 			<c:forEach var="item" items="${person.bag.items}"> --%>
<!-- 				<tr> -->
<%-- 					<td><c:out value="${item.name}" /></td> --%>
<%-- 					<td><c:out value="${item.peso}" /></td> --%>
<%-- 					<td><c:out value="${item.type}" /></td> --%>
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>
<!-- 		</tbody> -->
<!-- 	</table> -->
<!-- 	<br /> -->
<!-- 	<br /> -->
<!-- 	<table border="1"> -->
<!-- 		<thead> -->
<!-- 			<tr> -->
<!-- 				<td>armas disponibles</td> -->
<!-- 			</tr> -->
<!-- 		</thead> -->
<!-- 		<tbody> -->
<!-- 			<tr> -->
<!-- 				primaria -->
<%-- 				<td><c:out value="${person.primary.name}" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				secundaria -->
<%-- 				<td><c:out value="${person.secondary.name}" /></td> --%>
<!-- 			</tr> -->
<!-- 		</tbody> -->
<!-- 	</table> -->
<!-- 	<br /> -->
<!-- 	<br /> -->
<!-- 	<table border="1"> -->
<!-- 		<thead> -->
<!-- 			<tr> -->
<!-- 				<td>primary customization</td> -->
<!-- 			</tr> -->
<!-- 		</thead> -->
<!-- 		<tbody> -->
<%-- 			<c:forEach var="item" items="${person.primary.items}"> --%>
<!-- 				<tr> -->
<%-- 					<td><c:out value="${item.name}" /></td> --%>
<%-- 					<td><c:out value="${item.peso}" /></td> --%>
<%-- 					<td><c:out value="${item.type}" /></td> --%>
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>
<!-- 		</tbody> -->
<!-- 	</table> -->
<!-- 	<br /> -->


<!-- 	<table border="1"> -->
<!-- 		<thead> -->
<!-- 			<tr> -->
<!-- 				<td>secundary customization</td> -->
<!-- 			</tr> -->
<!-- 		</thead> -->
<!-- 		<tbody> -->
<%-- 			<c:forEach var="item" items="${person.secondary.items}"> --%>
<!-- 				<tr> -->
<%--                     <td><c:out value="${item.name}" /></td> --%>
<%--                     <td><c:out value="${item.peso}" /></td> --%>
<%--                     <td><c:out value="${item.type}" /></td> --%>
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>
<!-- 		</tbody> -->
<!-- 	</table> -->


<%-- 	<form:form action="switchWeapon" method="post"> --%>
<!-- 		<input type="submit" value="cambiar armas" /> -->
<%-- 	</form:form> --%>




</body>
</html>