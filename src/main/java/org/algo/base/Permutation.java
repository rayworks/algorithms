package org.algo.base;

/**
 * Created by Sean on 6/4/17.
 */
public class Permutation extends BackTrack {
    public static void main(String[] args) {
        int[] vec = new int[10];
        new Permutation().backTrack(vec, 0, 3);
    }

    @Override
    protected void unmakeMove(int[] solutionVec, int pos, int size) {

    }

    @Override
    protected void makeMove(int[] solutionVec, int pos, int size) {

    }

    @Override
    protected int constructCandidates(int[] solutionVec, int pos, int size, int[] outCandidates) {
        int[] inPerm = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < inPerm.length; i++) {
            inPerm[i] = 0;
        }

        for (int j = 0; j < pos; j++) {
            inPerm[solutionVec[j]] = 1;
        }

        int nCount = 0;
        for (int i = 0; i <= size; i++) {
            if (inPerm[i] == 0) {
                outCandidates[nCount] = i;

                nCount += 1;
            }
        }
        return nCount;
    }

    @Override
    protected void processSolution(int[] solutionVec, int pos, int inputSize) {
        for (int i = 1; i <= pos; i++) {
            System.out.print(String.format(" %d", solutionVec[i]));
        }
        System.out.println("");
    }

    @Override
    protected boolean isASolution(int[] solutionVec, int pos, int inputSize) {
        return pos == inputSize;
    }
}
