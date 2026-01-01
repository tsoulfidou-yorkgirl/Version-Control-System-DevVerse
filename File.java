import java.util.List;
import java.util.ArrayList;

public class File {
    private String name;
    private List<String> versions = new ArrayList<>(); // versions[0] is initial

    public File(String name, String initialContent) {
        this.name = name;
        this.versions.add(initialContent);
    }

    public void updateContent(String newContent) {
        versions.add(newContent);
    }

    public List<String> getVersions() { return new ArrayList<>(versions); }
    public String getName() { return name; }
    public String getLatest() { return versions.get(versions.size() - 1); }
}