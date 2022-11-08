package cherry.demo.contract.flow;

import cherry.demo.contract.OrderEntity;
import cherry.demo.contract.OrderQuery;
import cherry.demo.contract.configure.BizModel;
import cherry.demo.contract.configure.ContractConfigure;
import cherry.demo.contract.configure.ContractConfigureQuery;
import cherry.demo.contract.remote.MultiSignParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/7
 */
public class ContractFlow {
    SignParamsBuilder signParamsBuilder;
    ContractConfigureQuery contractConfigureQuery;
    OrderQuery orderQuery;

    public List<ContractDTO> previewList(String orderNo) {
        OrderEntity orderEntity = orderQuery.order(orderNo);

        List<ContractConfigure> contractConfigures = contractConfigureQuery.businessContract(
                new BizModel(orderEntity.getBusinessType(), orderEntity.getFund(), orderEntity.getState()));
        List<ContractDTO> contractDTOs = new ArrayList<>();
        contractConfigures.forEach(item -> contractDTOs.add(new ContractDTO(item.getId(), item.getContractName()))
        );
        return contractDTOs;
    }

    public String previewContent(String orderNo, Long contractId) {
        ContractConfigure contractConfigure = contractConfigureQuery.find(contractId);
        Map<String, Object> elements = signParamsBuilder.buildElements(orderNo, contractConfigure);

        //远程

        return null;
    }

    public void signContract(String orderNo, String captcha) {
        OrderEntity orderEntity = orderQuery.order(orderNo);

        List<ContractConfigure> contractConfigures = contractConfigureQuery.businessContract(null);
        MultiSignParam multiSignParam = signParamsBuilder.buildMulti(orderNo, contractConfigures, captcha);

        //远程

        //保存合同
    }
}
