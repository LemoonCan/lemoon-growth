package cherry.demo.order.core.adapter;

import cherry.demo.order.api.dto.PreviewContractDTO;
import cherry.demo.order.core.domain.model.OrderEntity;
import cherry.demo.order.core.domain.repository.IOrderQuery;
import cherry.demo.order.core.infrastructure.contract.configure.BizModel;
import cherry.demo.order.core.infrastructure.contract.configure.ContractConfigure;
import cherry.demo.order.core.infrastructure.contract.configure.ContractConfigureQuery;
import cherry.demo.order.core.infrastructure.contract.parambuilder.SignParamsBuilder;
import cherry.demo.order.core.domain.model.Contract;
import cherry.demo.order.remote.ContractApi;
import cherry.demo.order.remote.dto.ContractDTO;
import cherry.demo.order.remote.dto.ContractElementDTO;
import cherry.demo.order.remote.dto.MultiSignParam;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lee
 * @date 2022/11/7
 */
public class ContractFlow {
    SignParamsBuilder signParamsBuilder;
    ContractConfigureQuery contractConfigureQuery;
    IOrderQuery orderQuery;
    ContractApi contractApi;
    /**
     * 待签合同列表
     *
     * @param orderNo
     * @return
     */
    public List<PreviewContractDTO> previewList(String orderNo) {
        OrderEntity orderEntity = orderQuery.order(orderNo);

        List<ContractConfigure> contractConfigures = contractConfigureQuery.businessContract(
                new BizModel(orderEntity.getBusinessType(), orderEntity.getFund(), orderEntity.getState()));
        List<PreviewContractDTO> contracts = new ArrayList<>();
        contractConfigures.forEach(item -> contracts.add(new PreviewContractDTO(item.getId(), item.getContractName())));
        return contracts;
    }

    /**
     * 预览合同内容
     *
     * @param orderNo
     * @param contractId
     * @return
     */
    public String previewContent(String orderNo, Long contractId) {
        ContractConfigure contractConfigure = contractConfigureQuery.find(contractId);
        Map<String, Object> elements = signParamsBuilder.buildElements(orderNo, contractConfigure);

        return contractApi.getContractHtml(contractConfigure.getTemplateCode(), elements);
    }

    /**
     * 签署合同
     *
     * @param orderNo
     * @param captcha
     */
    public List<Contract> signContract(String orderNo, String captcha) {
        OrderEntity orderEntity = orderQuery.order(orderNo);

        //1.查询待签合同列表
        List<ContractConfigure> contractConfigures = contractConfigureQuery.businessContract(
                new BizModel(orderEntity.getBusinessType(), orderEntity.getFund(), orderEntity.getState()));

        //2.构建签署参数
        MultiSignParam multiSignParam = signParamsBuilder.buildMulti(orderNo, contractConfigures, captcha);

        //3.签署合同
        List<ContractDTO> contractDTOList = contractApi.multiSignContract(multiSignParam);

        //4.结果集转换
        Map<String, ContractConfigure> configureMap = new HashMap<>(16);
        for (ContractConfigure item : contractConfigures) {
            configureMap.put(item.getTemplateCode(), item);
        }
        List<Contract> contracts = contractDTOList.stream()
                .map(item ->
                        {
                            ContractConfigure configure = configureMap.get(item.getTemplateCode());
                            Contract contract = Contract.builder()
                                    .contractNo(item.getContractNo())
                                    .signDate(LocalDateTime.ofInstant(item.getSignAt().toInstant(), ZoneId.of("CTT")))
                                    .name(configure.getContractName())
                                    .contractType(configure.getContractType())
                                    .show(configure.getShow())
                                    .build();

                            if (configure.getBlankContract()) {
                                String blankContractUrl = contractApi.getTemplatePdfUrl(item.getTemplateCode(),
                                        item.getElements()
                                                .stream()
                                                .collect(Collectors.toMap(ContractElementDTO::getName, ContractElementDTO::getValue)));
                                contract.generateBlankContract(blankContractUrl);
                            }

                            return contract;
                        }
                )
                .collect(Collectors.toList());
        return contracts;
    }
}
