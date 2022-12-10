package designpattern.behavior.visitor;

import designpattern.behavior.visitor.other.Compressor;
import designpattern.behavior.visitor.other.Extractor;

import java.util.Arrays;
import java.util.List;

/**
 * @author lee
 * @date 2022/10/20
 */
public class Demo {
    public static void main(String[] args) {
        ResourceFile pdf = new PdfFile("x.pdf");
        ResourceFile word = new WordFile("y.word");
        ResourceFile ppt = new PptFile("z.ppt");

        List<ResourceFile> resourceFiles = Arrays.asList(pdf,word,ppt);

        Extractor extractor = new Extractor();
        //extractor.visit(pdf);
        for (ResourceFile file:resourceFiles){
            file.accept(extractor);
        }

        Compressor compressor = new Compressor();
        //compressor.visit(pdf);
        for (ResourceFile file:resourceFiles){
            file.accept(compressor);
        }
    }
}
