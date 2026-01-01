public class CommitImpl implements Commit {
    private String message;

    public CommitImpl(String message) {
        this.message = message;
        
    }

    @Override
    public String getMessage() {
        return message;
    }
}