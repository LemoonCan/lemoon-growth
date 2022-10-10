package designpattern.structured.combination;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lee
 * @date 2022/10/10
 */
public class Directory extends FileSystemNode {
    private List<FileSystemNode> fileSystemNodes;

    public Directory(String path) {
        super(path);
    }

    public void addSubNode(FileSystemNode item){
        if(Objects.isNull(fileSystemNodes)||fileSystemNodes.size()<1){
            this.fileSystemNodes = new ArrayList<>();
        }
        fileSystemNodes.add(item);
    }

    public void removeSubNode(FileSystemNode item){

    }

    @Override
    public int countFiles() {
        int count = 0;
        for (FileSystemNode item : fileSystemNodes){
            count+=item.countFiles();
        }
        return count;
    }
}
