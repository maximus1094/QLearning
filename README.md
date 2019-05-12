# Artificail Intelligence - QLearning
Q-Learning Algorithm Implementation in Java. <br />
Although this was done a few years ago in college, this project is a good example of Object Oriented Programming in Java, althought it's very tiny.

## Wikipedia Description
> Q-learning is a model-free reinforcement learning algorithm. The goal of Q-learning is to learn a policy, which tells an agent what action to take under what circumstances. It does not require a model (hence the connotation "model-free") of the environment, and it can handle problems with stochastic transitions and rewards, without requiring adaptations.

https://en.wikipedia.org/wiki/Q-learning

## Project

There are 3 classes:
- Agent - contains logic of the qLearning algorithm.
- AgentsBrain - contains and allows access to probability and reward matrices.
- World - calss used to spawn the Agent and pass in parameters.

### To calculate Q-value
1. Create an `Agent` in the `World`.
2. Call `agent.qFunction` with following parameters:
 - nVal - number of interations
 - yVal - discount value on future reward
 - state - current state of the agent
 - action - action to consider in calculation
