package javabasic.dynamic.reflect.demo;


import java.util.List;

/**
 * 材料分块
 * @author lee
 * @since 2023/4/24
 */
public class FileBlock extends AbstractDataBlock<List<String>> {
    private String id;
    private List<Rule<FileBlock>> rules;
    private List<String> value;

    public FileBlock(String id){
        this.id = id;
    }

    @Override
    public List<String> getValue() {
        return value;
    }
}
