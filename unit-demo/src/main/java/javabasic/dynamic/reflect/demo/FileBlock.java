package com.souche.finance.trade.center.domain.datamodel.material;

import com.souche.finance.trade.center.api.scene.dto.OSSFile;

import java.util.List;

/**
 * 材料分块
 * @author lee
 * @since 2023/4/24
 */
public class FileBlock extends DataBlock<List<OSSFile>> {
    private List<OSSFile> value;

    public FileBlock(String id){
        this.id = id;
    }

    public void init(List<Rule<DataBlock<List<OSSFile>>>> rules, List<OSSFile> value) {
        this.rules = rules;
        this.value = value;
    }

    @Override
    public List<OSSFile> getValue() {
        return this.value;
    }



    @Override
    public void convertEntity(String orderCode) {

    }
}
