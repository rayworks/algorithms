package org.sean.backtracking;

/**
 * Created by Sean on 6/4/17.
 */
public abstract class BackTrack {
    public static boolean finished;

    public void backTrack(int[] solutionVec, int pos, int inputSize) {
        int[] outCandidates = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int nCandidate;

        if (isASolution(solutionVec, pos, inputSize)) {
            processSolution(solutionVec, pos, inputSize);
        } else {
            pos += 1;

            nCandidate = constructCandidates(solutionVec, pos, inputSize, outCandidates);

            for (int i = 0; i < nCandidate; i++) {
                solutionVec[pos] = outCandidates[i];

                makeMove(solutionVec, pos, inputSize);
                backTrack(solutionVec, pos, inputSize);
                unmakeMove(solutionVec, pos, inputSize);

                if (finished) // terminate early
                    return;
            }
        }

    }

    protected abstract void unmakeMove(int[] solutionVec, int pos, int size);

    protected abstract void makeMove(int[] solutionVec, int pos, int size);

    protected abstract int constructCandidates(int[] solutionVec, int pos, int size, int[] outCandidates);

    protected abstract void processSolution(int[] solutionVec, int pos, int inputSize);

    protected abstract boolean isASolution(int[] solutionVec, int pos, int inputSize);
}
