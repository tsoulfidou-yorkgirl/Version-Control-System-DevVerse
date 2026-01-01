public interface Repository {
    void createBranch(String branchName);
    Branch getBranch(String branchName);
    void mergeBranches(String targetBranchName, String sourceBranchName);
}
