package designpattern.behavior.visitor.other;

import designpattern.behavior.visitor.PdfFile;
import designpattern.behavior.visitor.PptFile;
import designpattern.behavior.visitor.WordFile;

/**
 * @author lee
 * @since 2022/10/20
 */
public class Compressor implements Vistor{
    @Override
    public void visit(PdfFile file){
        System.out.println("pdf compress");
    }

    @Override
    public void visit(WordFile file){
        System.out.println("word compress");
    }

    @Override
    public void visit(PptFile file){
        System.out.println("ppt compress");
    }
}
