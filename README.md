# QLearning
Artificial Intelligence - Q-Learning Algorithm Implementation in Java

There are 3 classes
- Agent - contains logic of the qLearning algorithm.
- AgentsBrain - contains and allows access to probability and reward matrices.
- World - calss used to spawn the Agent and pass in parameters.

# To calculate Q-value
1. Create an Agent in the World
2. Call agent.qFunction with following parameters:

- nVal - number of interations
- yVal - discount value on future reward
- state - current state of the agent
- action - action to consider in calculation
