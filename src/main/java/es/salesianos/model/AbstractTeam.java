package es.salesianos.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("team")
public class AbstractTeam implements Team{
	private int maxMembers = 6;
	private ArrayList<TeamPokemon> members = new ArrayList<TeamPokemon>();
	
	public TeamPokemon getCurrentPokemon() {
		for(TeamPokemon poke : members) {
			if(poke.isCurrentFighter()) {
				return poke;
			}
		}
		return null;
	}
	
	public int getMaxMembers() {
		return maxMembers;
	}

	public void setMaxMembers(int maxMembers) {
		this.maxMembers = maxMembers;
	}

	@Override
	public void addMember(TeamPokemon newMember) {
		if(members.size() < maxMembers) {
			members.add(newMember);
			newMember.setID(Integer.toString(members.size()));
		}
	}
	
	@Override
	public void removeMember(String memberName) {
		for (int i = 0; i < members.size(); i++) {
			if(members.get(i).getName().equals(memberName)) {
				members.remove(i);
				return;
			}
		}
	}
	
	@Override
	public void removeMember(int memberIndex) {
		members.remove(memberIndex);
	}

	@Override
	public boolean isFull() {
		if(members.size() < maxMembers) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public int getMemberCount() {
		return members.size();
	}

	@Override
	public ArrayList<TeamPokemon> getMembers() {
		return members;
	}
}
