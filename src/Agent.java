public class Agent {
	
	// S - STATE, A - ACTION
	public final static int S_FIT = 0;
	public final static int S_UNFIT = 1;
	public final static int A_EXERCISE = 0;
	public final static int A_RELAX = 1;
	
	// IN: S, S'
	public double[][] A_EX_PROB = { {0.99, 0.01}, {0.2, 0.8} };
	public double[][] A_EX_REW = { {8, 8}, {0, 0} };
	public double[][] A_RLX_PROB = { {0.7, 0.3}, {0, 1} };
	public double[][] A_RLX_REW = { {10, 10}, {5, 5} };	
	
	public double calculateReturns(int nVal, double yVal, int state, int action) {
		double q0 = instantReward(state, action);
		double futureReward = futureReward(nVal, yVal, state, action);
		
		return q0 + yVal*futureReward;
	}
	
	private double vN(int state, int nVal, double yVal) {
		double sExercise = calculateReturns(nVal, yVal, state, A_EXERCISE);
		double sRelax = calculateReturns(nVal, yVal, state, A_RELAX);
		
		return sExercise > sRelax ? sExercise : sRelax;
	}
	
	private double qN(int state, int action) {
		double psaf = getProbability(state, action, S_FIT);
		double vnf = vN(state, action, S_FIT);
	
		double psau = getProbability(state, action, S_UNFIT);
		double vnu = vN(state, action, S_UNFIT);
		
		return psaf*vnf + psau*vnu;
	}
	
	private double futureReward(int nVal, double yVal, int state, int action) {
		if(nVal == 1) return qN(state, action);
		
		return qN(state, action) + yVal * futureReward(--nVal, yVal, state, action);
	}
	
	private double instantReward(int state, int action) {
		double psaf = getProbability(state, action, S_FIT);
		double rsaf = getReward(state, action, S_FIT);
		
		double psau = getProbability(state, action, S_UNFIT);
		double rsau = getReward(state, action, S_UNFIT);
		
		return psaf * rsaf + psau * rsau;
	}
	
	private double getProbability(int sCurrent, int action, int sNext) {
		double prob = -1;
		switch(action) {
			case A_EXERCISE:
				prob = A_EX_PROB[sCurrent][sNext];
				break;
			case A_RELAX:
				prob = A_RLX_PROB[sCurrent][sNext];
				break;
			default:
				System.err.println("Agent.getProbability() - Unknown action");
		}
		return prob;
	}
	
	private double getReward(int sCurrent, int action, int sNext) {
		double reward = -1;
		switch(action) {
			case A_EXERCISE:
				reward = A_EX_REW[sCurrent][sNext];
				break;
			case A_RELAX:
				reward = A_RLX_REW[sCurrent][sNext];
				break;
			default:
				System.err.println("Agent.getReward() - Unknown action");
		}
		return reward;
	}
	
	public static void main(String[] args) {
		Agent agent = new Agent();
		System.out.println(agent.calculateReturns(2, 0.9, S_FIT, A_EXERCISE));
	}
	
}
