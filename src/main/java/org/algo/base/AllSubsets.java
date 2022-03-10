package org.algo.base;

/**
 * Created by Sean on 6/4/17.
 */
public class AllSubsets extends BackTrack {

    public static void main(String[] args) {
        int[] a = new int[10];

        AllSubsets as = new AllSubsets();
        as.backTrack(a, 0, 3);
    }

    @Override
    protected void unmakeMove(int[] solutionVec, int pos, int size) {

    }

    @Override
    protected void makeMove(int[] solutionVec, int pos, int size) {

    }

    @Override
    protected int constructCandidates(int[] solutionVec, int pos, int size, int[] outCandidates) {
        outCandidates[0] = 1;
        outCandidates[1] = 0;

        return 2;
    }

    @Override
    protected void processSolution(int[] solutionVec, int pos, int inputSize) {
        System.out.print("{");
        for (int i = 1; i <= pos; i++) {
            if (solutionVec[i] == 1) {
                System.out.print(String.format(" %d", i));
            }
        }
        System.out.print(" }\n");
    }

    @Override
    protected boolean isASolution(int[] solutionVec, int pos, int inputSize) {
        return pos == inputSize;
    }
}
