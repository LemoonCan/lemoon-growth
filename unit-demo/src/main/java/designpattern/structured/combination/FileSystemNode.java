package designpattern.structured.combination;

/**
 * @author lee
 * @since 2022/10/10
 */
public abstract class FileSystemNode {
    protected String path;

    public FileSystemNode(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public abstract int countFiles();
}
