package com.souche.finance.trade.center.domain.datamodel.material;

/**
 * @author lee
 * @since 2023/4/24
 */
public class FileQuantityRule implements Rule<FileBlock>{
    public static final String ID = "fileQuantityRule";
    private final int quantity;

    public FileQuantityRule(int quantity){
        this.quantity = quantity;
    }

    @Override
    public String identity() {
        return ID;
    }

    @Override
    public void verify(FileBlock block) {
        if (block.getValue().size() != quantity) {
            throw new IllegalArgumentException(String.format("材料数量不符,应为%s",quantity));
        }
    }
}
