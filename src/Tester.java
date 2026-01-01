
public class Tester {
    public static void main(String[] args) {
        System.out.println("=== Basic Functionality Tests ===\n");

        // Test 1: Create repository and main branch, add files, commit, display
        Repository repo = new RepositoryImpl();
        repo.createBranch("main");
        Branch main = repo.getBranch("main");
        main.addFile("hello.txt", "Hello World v1");
        main.commitChanges("Initial commit");
        System.out.println("Main commit history: " + main.getCommitHistory().size());

        // Test 2: Create feature branch, update file, commit, display
        repo.createBranch("feature");
        Branch feature = repo.getBranch("feature");
        feature.addFile("hello.txt", "Hello World v2");
        feature.commitChanges("Feature update");
        feature.displayFiles();
        System.out.println("Feature commit history: " + feature.getCommitHistory().size());

        // Test 3: Merge feature to main, display versions
        repo.mergeBranches("main", "feature");
        main.displayFiles();
        System.out.println("Main after merge commit history: " + main.getCommitHistory().size());

        // Test 4: Revert last commit
        Commit lastCommit = main.getCommitHistory().get(main.getCommitHistory().size() - 1);
        main.revert(lastCommit);
        System.out.println("After revert commit history: " + main.getCommitHistory().size());

        System.out.println("\n=== Performance Test: Merge 1000 files ===\n");

        // Large data: many files
        Repository perfRepo = new RepositoryImpl();
        perfRepo.createBranch("perfMain");
        Branch perfMain = perfRepo.getBranch("perfMain");
        long start = System.nanoTime();
        for (int i = 1; i <= 1000; i++) {
            perfMain.addFile("file" + i + ".txt", "content v1 for file " + i);
        }
        perfMain.commitChanges("Bulk add");
        Branch perfFeat = new BranchImpl("perfFeat");
        for (int i = 1; i <= 1000; i++) {
            perfFeat.addFile("file" + i + ".txt", "content v2 for file " + i);
        }
        perfFeat.commitChanges("Bulk update");
        perfRepo.mergeBranches("perfMain", "perfFeat");
        long end = System.nanoTime();
        System.out.println("Time for 1000-file merge: " + (end - start) / 1_000_000.0 + " ms");
        perfMain.displayFiles(); // shows 1000 files
    }
}


