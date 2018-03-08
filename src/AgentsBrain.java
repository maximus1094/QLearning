
public class AgentsBrain {
	
	// S - STATE, A - ACTION
	public final static int S_FIT = 0;
	public final static int S_UNFIT = 1;
	public final static int A_EXERCISE = 0;
	public final static int A_RELAX = 1;
	
	// IN: S, S'
	private double[][] A_EX_PROB = { {0.99, 0.01}, {0.2, 0.8} };
	private double[][] A_EX_REW = { {8, 8}, {0, 0} };
	private double[][] A_RLX_PROB = { {0.7, 0.3}, {0, 1} };
	private double[][] A_RLX_REW = { {10, 10}, {5, 5} };	
	
	public double getProbability(int sCurrent, int action, int sNext) {
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
	
	public double getReward(int sCurrent, int action, int sNext) {
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
	
}
