import java.util.Map;
import java.util.HashMap;

public class RepositoryImpl implements Repository {
    // You need to create a BranchImpl class that implements Branch
    private Map<String, Branch> branches = new HashMap<>(); //Decided to do this with a map because it allows for fast lookups by branch name

    @Override
    public void createBranch(String branchName) {
        branches.put(branchName, new BranchImpl(branchName));
    } //Here im creating a new branch and adding it to the map

    @Override
    public Branch getBranch(String branchName) {
        return branches.get(branchName);
    } //Here im getting the branch from the map by its name

    @Override
    public void mergeBranches(String sourceBranch, String targetBranch) {
        // TODO: Implement merge logic
    }
}
