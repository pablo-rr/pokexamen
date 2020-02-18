package es.salesianos.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

public interface Team {
	public ArrayList<TeamPokemon> getMembers();
	public int getMemberCount();
	public void addMember(TeamPokemon member);
	public void removeMember(String memberName);
	public void removeMember(int memberIndex);
	public boolean isFull();
}
