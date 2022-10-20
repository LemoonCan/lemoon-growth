package designpattern.behavior.visitor;

import designpattern.behavior.visitor.other.Compressor;
import designpattern.behavior.visitor.other.Extractor;

/**
 * @author lee
 * @date 2022/10/20
 */
public class WordFile extends ResourceFile{
    public WordFile(String name) {
        super(name);
    }

    @Override
    public void accept(Extractor extractor) {
        extractor.visit(this);
    }

    @Override
    public void accept(Compressor compressor) {
        compressor.visit(this);
    }
}
