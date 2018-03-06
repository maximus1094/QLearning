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
	
	public void calculateReturns(int nVal, double yVal, int state) {
		double q0ex = instantReward(state, A_EXERCISE);
		double q0rlx = instantReward(state, A_RELAX);
		
		if (nVal == 0) {
			System.out.println("(F,E)n=0: " + (q0ex));
			System.out.println("(F,R)n=0: " + (q0rlx));
		} else {
			double discFutureEx = yVal * futureReward(nVal, yVal, state, A_EXERCISE);
			double discFutureRlx = yVal * futureReward(nVal, yVal, state, A_RELAX);
		
			System.out.println("(F,E): " + (q0ex + discFutureEx));
			System.out.println("(F,R): " + (q0rlx + discFutureRlx));
		}
	}
	
	// Here S' doesn't matter because only S, A changes the reward
	private double vN(int state) {
		double sExercise = getReward(state, A_EXERCISE, state);
		double sRelax = getReward(state, A_RELAX, state);
		
		return sExercise > sRelax ? sExercise : sRelax;
	}
	
	private double qN(int state, int action) {
		double psaf = getProbability(state, action, S_FIT);
		double vnf = vN(S_FIT);
	
		double psau = getProbability(state, action, S_UNFIT);
		double vnu = vN(S_UNFIT);
		
		return psaf*vnf + psau*vnu;
	}
	
	public double futureReward(int nVal, double yVal, int state, int action) {
		if(nVal == 1) return qN(state, action);
		
		return qN(state, action) + yVal * futureReward(--nVal, yVal, state, action);
	}
	
	public double instantReward(int state, int action) {
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
		System.out.println(agent.instantReward(S_UNFIT, A_EXERCISE));
	}
	
}
