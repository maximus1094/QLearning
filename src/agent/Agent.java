package agent;
public class Agent {
	
	private AgentsBrain brain = new AgentsBrain();
	
	public double qFunction(int nVal, double yVal, int state, int action) {
		double q0 = q0(state, action);
		double discFutureReward = 0;
		
		// Only calculate next step if n is still greater than 0
		if (nVal > 0) {
			discFutureReward = yVal * futureReward(nVal, yVal, state, action);
		}
		
		return q0 + discFutureReward;
	}
	
	private double vN(int nVal, double yVal, int state) {
		double sExercise = qFunction(nVal, yVal, state, brain.A_EXERCISE);
		double sRelax = qFunction(nVal, yVal, state, brain.A_RELAX);
		
		return sExercise > sRelax ? sExercise : sRelax;
	}
	
	private double futureReward(int nVal, double yVal, int state, int action) {
		int newNval = nVal - 1;
		
		double psaf = brain.getProbability(state, action, brain.S_FIT);
		double vnf = vN(newNval, yVal, brain.S_FIT);
	
		double psau = brain.getProbability(state, action, brain.S_UNFIT);
		double vnu = vN(newNval, yVal, brain.S_UNFIT);

		return psaf*vnf + psau*vnu;
	}
	
	private double q0(int state, int action) {
		double psaf = brain.getProbability(state, action, brain.S_FIT);
		double rsaf = brain.getReward(state, action, brain.S_FIT);
		
		double psau = brain.getProbability(state, action, brain.S_UNFIT);
		double rsau = brain.getReward(state, action, brain.S_UNFIT);
		
		return psaf * rsaf + psau * rsau;
	}
	
	public AgentsBrain getBrain() { return brain; }

}
