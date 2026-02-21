public interface FileSystemItem {
    int getSize();
    void openAll(int indent);
    void ls();
    FileSystemItem cd(String target);
    boolean isFolder();
    String getName();
}
