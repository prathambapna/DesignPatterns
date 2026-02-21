import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemItem{
    private final String name;
    private final List<FileSystemItem>fileSystemItemList;

    public Folder(String name){
        this.name=name;
        fileSystemItemList=new ArrayList<>();
    }

    public void addFileSystemItemInFolder(FileSystemItem fileSystemItem){
        fileSystemItemList.add(fileSystemItem);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void ls() {
        for(FileSystemItem fsi:fileSystemItemList){
            if(fsi.isFolder()){
                System.out.println("+ " + fsi.getName());
            }
            else{
                System.out.println(fsi.getName());
            }
        }
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    @Override
    public void openAll(int indent) {
        for (FileSystemItem fsi:fileSystemItemList){
            if(fsi.isFolder()){
                System.out.println(" ".repeat(indent)+"+ " +fsi.getName());
                fsi.openAll(indent+4);
            }
            else{
                System.out.println(" ".repeat(indent)+ fsi.getName());
            }
        }
    }

    @Override
    public FileSystemItem cd(String  target) {
        for(FileSystemItem fsi:fileSystemItemList){
            if(fsi.isFolder() && fsi.getName().equals(target)){
                return fsi;
            }
        }
        System.out.println(target + " not found");
        return null;
    }

    @Override
    public int getSize() {
        int total=0;
        for(FileSystemItem fsi:fileSystemItemList){
                total+= fsi.getSize();
        }
        return total;
    }
}
