public class File implements FileSystemItem{
    private final int size;
    private final String name;

    public File(String name, int size){
        this.name=name;
        this.size=size;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isFolder(){
        return false;
    }

    @Override
    public void ls() {
        System.out.println(name);
    }

    @Override
    public void openAll(int indent) {
        System.out.println(" ".repeat(indent)+name);
    }

    @Override
    public FileSystemItem cd(String target) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
