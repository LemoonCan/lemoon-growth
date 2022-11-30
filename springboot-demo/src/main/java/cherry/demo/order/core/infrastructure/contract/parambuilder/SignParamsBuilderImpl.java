package cherry.demo.order.core.infrastructure.contract.parambuilder;

import cherry.demo.order.core.domain.model.CustomerInfo;
import cherry.demo.order.core.domain.model.ShopInfo;
import cherry.demo.order.core.domain.repository.IOrderQuery;
import cherry.demo.order.core.infrastructure.contract.configure.ConfigureParty;
import cherry.demo.order.core.infrastructure.contract.configure.ContractConfigure;
import cherry.demo.order.remote.dto.MultiSignParam;
import cherry.demo.order.remote.dto.Party;
import cherry.demo.order.remote.dto.PartyType;
import cherry.demo.order.remote.dto.SingleSignParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/4
 */
@Component
public class SignParamsBuilderImpl implements SignParamsBuilder {
    @Autowired
    private ValuesPreProcessing valuesPreProcessing;
    @Autowired
    private IOrderQuery orderQuery;

    @Override
    public MultiSignParam buildMulti(String orderNo, List<ContractConfigure> contractConfigures, String captcha) {
        Map<String, Object> values = valuesPreProcessing.query(orderNo, contractConfigures);

        MultiSignParam multiSignParam = new MultiSignParam();
        multiSignParam.setCaptcha(captcha);
        multiSignParam.setSingleSignParams(new ArrayList<>());
        for (ContractConfigure item : contractConfigures) {
            SingleSignParam singleSignParam = buildSingle(orderNo, item, values, null);
            multiSignParam.getSingleSignParams().add(singleSignParam);
        }
        return multiSignParam;
    }

    @Override
    public Map<String, Object> buildElements(String orderNo, ContractConfigure contractConfigure) {
        Map<String, Object> values = valuesPreProcessing.query(orderNo, contractConfigure);
        return buildElements(values, contractConfigure);
    }

    private SingleSignParam buildSingle(String orderNo, ContractConfigure contractConfigure, Map<String, Object> values, String captcha) {
        SingleSignParam singleSignParam = new SingleSignParam();
        List<Party> parties = buildParty(orderNo, contractConfigure);
        Map<String, Object> elements = buildElements(values, contractConfigure);

        singleSignParam.setContractNo(valuesPreProcessing.getAContractNo(values, contractConfigure));
        singleSignParam.setContractName(contractConfigure.getContractName());
        singleSignParam.setTemplateCode(contractConfigure.getTemplateCode());
        singleSignParam.setCaptcha(captcha);
        singleSignParam.setFirstParty(parties.size() >= 1 ? parties.get(0) : null);
        singleSignParam.setSecondParty(parties.size() >= 2 ? parties.get(1) : null);
        singleSignParam.setThirdParty(parties.size() >= 3 ? parties.get(2) : null);
        singleSignParam.setElements(elements);
        return singleSignParam;
    }

    private List<Party> buildParty(String orderNo, ContractConfigure contractConfigure) {
        List<Party> parties = new ArrayList<>();
        for (ConfigureParty configureParty : contractConfigure.getConfigureParties()) {
            switch (configureParty.getType()) {
                case FINANCE_LEASING_HEAD:
                    Party flhParty = new Party();
                    flhParty.setName("融租总公司");
                    flhParty.setNumber("00000");
                    flhParty.setPartyType(PartyType.self_company);
                    flhParty.setPlatformSign(true);
                    parties.add(flhParty);
                    break;
                case CAR_SERVICE:
                    Party csParty = new Party();
                    csParty.setName("汽服总公司");
                    csParty.setNumber("00000");
                    csParty.setPartyType(PartyType.self_company);
                    csParty.setPlatformSign(true);
                    parties.add(csParty);
                    break;
                case SHOP:
                    ShopInfo shopInfo = orderQuery.shop(orderNo);
                    Party shopParty = new Party();
                    shopParty.setName(shopInfo.getName());
                    shopParty.setNumber(shopInfo.getCreditCode());
                    shopParty.setCreatorMobile(shopInfo.getSalePersonMobile());
                    shopParty.setCreatorNumber(shopInfo.getSalePersonIdCardNo());
                    shopParty.setCreatorName(shopInfo.getSalePersonName());
                    parties.add(shopParty);
                    break;
                case CUSTOMER:
                    CustomerInfo customer = orderQuery.customer(orderNo);
                    Party cusParty = new Party();
                    cusParty.setName(customer.getName());
                    cusParty.setNumber(customer.getIdCardNo());
                    cusParty.setMobile(customer.getMobile());
                    parties.add(cusParty);
                    break;
                case MORTGAGE:
                    break;
                default:
            }
        }
        return parties;
    }

    private Map<String, Object> buildElements(Map<String, Object> values, ContractConfigure contractConfigure) {
        Map<String, Object> elements = new HashMap<>(16);
        contractConfigure.getElementsMap().forEach(
                (key, rule) -> elements.put(key, rule.format(values.get(rule.key())))
        );
        return elements;
    }

}
