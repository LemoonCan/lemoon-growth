package designpattern.structured.combination;

/**
 * @author lee
 * @since 2022/10/10
 */
public class File extends FileSystemNode{
    public File(String path) {
        super(path);
    }

    @Override
    public int countFiles() {
        return 1;
    }
}
