package cherry.demo.contract.flow;

import cherry.demo.contract.configure.ConfigureParty;
import cherry.demo.contract.configure.ContractConfigure;
import cherry.demo.contract.remote.MultiSignParam;
import cherry.demo.contract.remote.Party;
import cherry.demo.contract.remote.SingleSignParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/4
 */
public class SignParamsBuilderImpl implements SignParamsBuilder {
    private ValuesPreProcessing valuesPreProcessing;

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
    public SingleSignParam buildSingle(String orderNo, ContractConfigure contractConfigure, String captcha) {
        Map<String, Object> values = valuesPreProcessing.query(orderNo, contractConfigure);
        return buildSingle(orderNo, contractConfigure, values, captcha);
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
        for (ConfigureParty configureParty : contractConfigure.getConfigurePartys()) {
            switch (configureParty.getType()) {
                case FINANCE_LEASING_HEAD:
                    break;
                case CAR_SERVICE:
                    break;
                case SHOP:
                    break;
                case CUSTOMER:
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
                (key, value) -> elements.put(key, value.format(values.get(key)))
        );
        return null;
    }

}
