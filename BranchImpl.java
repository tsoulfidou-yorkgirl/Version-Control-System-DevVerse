import java.util.*;

public class BranchImpl implements Branch {
    private String name;
    private List<Commit> commitHistory = new ArrayList<>();
    private Map<String, VersionedFile> files = new HashMap<>(); // current files

    public BranchImpl(String name) {
        this.name = name;
    }

    @Override
    public void commitChanges(String message) {
        if (!commitHistory.isEmpty() || !files.isEmpty()) { // only commit if changes
            Commit newCommit = new CommitImpl(message); // Explicitly assign to Commit type for clarity
            commitHistory.add(newCommit);
        }
    }

    @Override
    public List<Commit> getCommitHistory() {
        return new ArrayList<>(commitHistory);
    }

    @Override
    public void revert(Commit c) {
        // Simple: remove last commit if matches (pop)
        if (!commitHistory.isEmpty() && commitHistory.get(commitHistory.size() - 1) == c) {
            commitHistory.remove(commitHistory.size() - 1);
            // Revert files to previous state 
            files.clear();
        }
    }

    @Override
    public void merge(Branch otherBranch) {
        // add other commits, update files to latest
        List<Commit> otherHistory = otherBranch.getCommitHistory();
        commitHistory.addAll(otherHistory.subList(commitHistory.size(), otherHistory.size()));
        // Copy files, no conflict resolution
        // Assume addFile called separately for display
    }

    public void mergeBranch(Branch otherBranch) {
        // Cast to BranchImpl to access files
        if (otherBranch instanceof BranchImpl) {
            BranchImpl other = (BranchImpl) otherBranch;
            for (Map.Entry<String, VersionedFile> entry : other.files.entrySet()) {
                this.files.put(entry.getKey(), entry.getValue());
            }
            this.commitChanges("Merged branch " + other.name);
        }
    }

    public void addFile(String fileName, String content) {
        files.computeIfAbsent(fileName, k -> new VersionedFile(k)).updateContent(content);
    }

    public void displayFiles() {
        System.out.println("Branch " + name + " files:");
        for (VersionedFile f : files.values()) {
            System.out.println(f.getName() + " versions: " + f.getVersions().size() + 
                               " latest: " + f.getLatest());
        }
    }
}