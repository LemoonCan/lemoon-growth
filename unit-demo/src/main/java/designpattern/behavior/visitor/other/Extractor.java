package designpattern.behavior.visitor.other;

import designpattern.behavior.visitor.PdfFile;
import designpattern.behavior.visitor.PptFile;
import designpattern.behavior.visitor.WordFile;

/**
 * @author lee
 * @since 2022/10/20
 */
public class Extractor implements Vistor{
    @Override
    public void visit(PdfFile file){
        System.out.println("pdf extract to txt");
    }

    @Override
    public void visit(WordFile file){
        System.out.println("word extract to txt");
    }

    @Override
    public void visit(PptFile file){
        System.out.println("ppt extract to txt");
    }
}
