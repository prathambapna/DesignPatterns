//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Folder root=new Folder("root");
        File file1=new File("file 1",2);
        File file2=new File("file 2",1);
        File doc1=new File("doc 1",3);
        File doc2=new File("doc 2",1);
        File img1=new File("img 1",5);

        Folder docs=new Folder("docs");
        Folder img=new Folder("img");

        docs.addFileSystemItemInFolder(doc1);
        docs.addFileSystemItemInFolder(doc2);

        img.addFileSystemItemInFolder(img1);

        root.addFileSystemItemInFolder(file1);
        root.addFileSystemItemInFolder(docs);
        root.addFileSystemItemInFolder(file2);
        root.addFileSystemItemInFolder(img);

        System.out.println("root ls");
        root.ls();

        System.out.println("root open all");
        root.openAll(0);

        System.out.println("docs open all");
        docs.openAll(0);

        System.out.println("root size");
        System.out.println(root.getSize());

        System.out.println("cd docs");
        System.out.println(root.cd("docs").getName());

    }
}