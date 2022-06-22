package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int moves;
        private SearchNode prev;

        public SearchNode(WorldState s, int m, SearchNode p) {
            state = s;
            moves = m;
            prev = p;
        }

        public WorldState getState() {
            return state;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode getPrev() {
            return prev;
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.moves + this.state.estimatedDistanceToGoal()
                    - o.moves - o.state.estimatedDistanceToGoal();
        }
    }

    private MinPQ<SearchNode> openNodes = new MinPQ<>();
    private List<WorldState> bestSolution;
    private int totalMoves;

    /** After solving the puzzle, store the solution into an arraylist. */
    private void setBestSolution(SearchNode goal) {
        totalMoves = goal.moves;
        bestSolution = new ArrayList<>();
        SearchNode node = goal;
        while (node != null) {
            bestSolution.add(node.state);
            node = node.prev;
        }
    }

    /** Constructor which solves the puzzle, computing
     *  everything necessary for moves() and solution() to
     *  not have to solve the problem again. Solves the
     *  puzzle using the A* algorithm. Assumes a solution exists. */
    public Solver(WorldState initial) {
        openNodes.insert(new SearchNode(initial, 0, null));
        while (true) {
            SearchNode node = openNodes.delMin();
            if (node.state.isGoal()) {
                setBestSolution(node);
                return;
            } else {
                for (WorldState neighbor : node.state.neighbors()) {
                    if (node.prev == null || !neighbor.equals(node.prev.state)) {
                        openNodes.insert(new SearchNode(neighbor, node.moves + 1, node));
                    }
                }
            }
        }
    }

    /** Returns the minimum number of moves to solve the puzzle starting
     *  at the initial WorldState. */
    public int moves() {
        return totalMoves;
    }

    /** Returns a sequence of WorldStates from the initial WorldState
     *  to the solution. */
    public Iterable<WorldState> solution() {
        List<WorldState> solution = new ArrayList<>();
        for (int i = totalMoves; i >= 0; i--) {
            solution.add(bestSolution.get(i));
        }
        return solution;
    }
}
