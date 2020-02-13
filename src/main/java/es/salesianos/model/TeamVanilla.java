package es.salesianos.model;

import org.springframework.stereotype.Component;

@Component()
public class TeamVanilla extends AbstractTeam{
	public TeamVanilla() {
		setMaxMembers(6);
	}
}
