import java.util.ArrayList;
import java.util.List;

public class VersionedFile {
    private String name;
    private List<String> versions = new ArrayList<>();

    // Assuming constructor takes the file name
    public VersionedFile(String name) {
        this.name = name;
    }

    // Add this method to fix the error
    public void updateContent(String content) {
        versions.add(content);
    }

    public String getName() {
        return name;
    }

    public List<String> getVersions() {
        return new ArrayList<>(versions);
    }

    public String getLatest() {
        return versions.isEmpty() ? null : versions.get(versions.size() - 1);
    }
}
