package com.souche.finance.trade.center.domain.datamodel.material;

/**
 * @author lee
 * @since 2023/4/24
 */
public interface Rule<T extends DataBlock<?>> {
    String identity();

    void verify(T block);
}
