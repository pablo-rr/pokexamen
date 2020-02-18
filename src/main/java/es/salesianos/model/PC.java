package es.salesianos.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component("pc")
public class PC implements Team {

	private int maxMembers = 1000;
	private ArrayList<Pokemon> members = new ArrayList<Pokemon>();
	
	@Override
	public void addMember(Pokemon newMember) {
		if (!isFull()) {
			members.add(newMember);
			newMember.setWild(false);
			newMember.setID(Integer.toString(members.size()));
		}
	}

	@Override
	public void removeMember(String memberName) {
		for (int i = 0; i < getMemberCount(); i++) {
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

	public Pokemon getCurrentPokemon() {
		for (Pokemon poke : members) {
			if (poke.isCurrentFighter()) {
				return poke;
			}
		}
		return null;
	}

	@Override
	public boolean isFull() {
		if(members.size() < maxMembers) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int getMemberCount() {
		return members.size();
	}


	public int getMaxMembers() {
		return maxMembers;
	}

	public void setMaxMembers(int maxMembers) {
		this.maxMembers = maxMembers;
	}

	@Override
	public ArrayList<Pokemon> getMembers() {
		return members;
	}
}
