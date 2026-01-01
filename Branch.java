import java.util.List;

public interface Branch {
    void commitChanges(String message);
    List<Commit> getCommitHistory();
    void revert(Commit c);
    void merge(Branch otherBranch);
    void addFile(String string, String string2);
    void displayFiles();
}