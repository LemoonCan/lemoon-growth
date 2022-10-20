package designpattern.behavior.visitor;

import designpattern.behavior.visitor.other.Compressor;
import designpattern.behavior.visitor.other.Extractor;

/**
 * @author lee
 * @date 2022/10/20
 */
public abstract class ResourceFile {
    private String name;

    public ResourceFile(String name) {
        this.name = name;
    }

    public abstract void accept(Extractor extractor);
    public abstract void accept(Compressor compressor);
}
