package world;

import agent.Agent;
import agent.AgentsBrain;

public class World {
	
	public static void main(String[] args) {
		
		Agent agent = new Agent();
		AgentsBrain brain = agent.getBrain();
		
		int state = brain.S_FIT;
		int action = brain.A_EXERCISE;
		int nVal = 2;
		double yVal = 0.9;
		
		double qVal = agent.qFunction(nVal, yVal, state, action);

		System.out.println("Q: " + qVal);
		
	}
	
}
