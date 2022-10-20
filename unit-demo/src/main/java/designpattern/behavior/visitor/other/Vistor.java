package designpattern.behavior.visitor.other;

import designpattern.behavior.visitor.PdfFile;
import designpattern.behavior.visitor.PptFile;
import designpattern.behavior.visitor.WordFile;

/**
 * @author lee
 * @date 2022/10/20
 */
public interface Vistor {
    void visit(PdfFile pdfFile);
    void visit(WordFile wordFile);
    void visit(PptFile pptFile);
}
