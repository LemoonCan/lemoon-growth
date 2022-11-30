package cherry.demo.order.core.infrastructure.contract.configure;

import cherry.demo.order.core.infrastructure.contract.common.ContractType;
import cherry.demo.order.core.infrastructure.contract.common.Show;
import cherry.demo.order.core.infrastructure.contract.configure.element.format.DateFormat;
import cherry.demo.order.core.infrastructure.contract.configure.element.format.MoneyFormat;
import cherry.demo.order.core.infrastructure.contract.configure.element.format.NoFormat;
import cherry.demo.order.core.infrastructure.contract.configure.element.rule.InternRule;
import cherry.demo.order.core.infrastructure.contract.configure.element.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/7
 */
@Component
public class ContractConfigureQueryImpl implements ContractConfigureQuery {
    @Override
    public List<ContractConfigure> businessContract(BizModel model) {
        Map<String, Rule> elementsMap = new HashMap<>(4);
        elementsMap.put("customerName", new InternRule("customer.name", new NoFormat()));
        elementsMap.put("downPaymentTime", new InternRule("order.down_payment_time", new DateFormat("yyyy-MM-dd")));
        elementsMap.put("downPaymentAmount", new InternRule("product.down_payment_amount", new MoneyFormat("/100")));

        Map<String, Object> aPartyKeyWords = new HashMap<>(2);
        aPartyKeyWords.put("posKey", "甲方签章位置");
        Map<String, Object> bPartyKeyWords = new HashMap<>(2);
        bPartyKeyWords.put("posKey", "乙方签章位置");

        List<ContractConfigure> contractConfigures = Arrays.asList(
                ContractConfigure.builder().id(1L).contractType(ContractType.MORTGAGE).templateCode("MORT-001").contractName("抵押合同")
                        .show(Show.builder().admin(true).fund(true).customer(true).shop(false).build())
                        .blankContract(true)
                        .configureParties(Arrays.asList(
                                        new ConfigureParty(ConfigureParty.Type.CUSTOMER, new ConfigureParty.Position("keyWords", aPartyKeyWords)),
                                        new ConfigureParty(ConfigureParty.Type.CAR_SERVICE, new ConfigureParty.Position("keyWords", bPartyKeyWords))
                                )
                        )
                        .elementsMap(elementsMap)
                        .build(),
                ContractConfigure.builder().id(2L).contractType(ContractType.FINANCE_LEASE).templateCode("FL-001").contractName("融资租赁合同")
                        .show(Show.builder().admin(true).fund(true).customer(true).shop(false).build())
                        .blankContract(true)
                        .configureParties(Arrays.asList(
                                        new ConfigureParty(ConfigureParty.Type.CUSTOMER, new ConfigureParty.Position("keyWords", aPartyKeyWords)),
                                        new ConfigureParty(ConfigureParty.Type.FINANCE_LEASING_HEAD, new ConfigureParty.Position("keyWords", bPartyKeyWords))
                                )
                        )
                        .elementsMap(elementsMap)
                        .build()
        );
        return contractConfigures;
    }

    @Override
    public ContractConfigure find(Long id) {
        Map<String, Rule> elementsMap = new HashMap<>(4);
        elementsMap.put("customerName", new InternRule("customer.name", new NoFormat()));
        elementsMap.put("downPaymentTime", new InternRule("order.down_payment_time", new DateFormat("yyyy-MM-dd")));
        elementsMap.put("downPaymentAmount", new InternRule("product.down_payment_amount", new MoneyFormat("/100")));

        Map<String, Object> aPartyKeyWords = new HashMap<>(2);
        aPartyKeyWords.put("posKey", "甲方签章位置");
        Map<String, Object> bPartyKeyWords = new HashMap<>(2);
        bPartyKeyWords.put("posKey", "乙方签章位置");

        ContractConfigure mortConConfigure = ContractConfigure.builder().id(1L).contractType(ContractType.MORTGAGE).templateCode("MORT-001").contractName("抵押合同")
                .show(Show.builder().admin(true).fund(true).customer(true).shop(false).build())
                .blankContract(true)
                .configureParties(Arrays.asList(
                                new ConfigureParty(ConfigureParty.Type.CUSTOMER, new ConfigureParty.Position("keyWords", aPartyKeyWords)),
                                new ConfigureParty(ConfigureParty.Type.CAR_SERVICE, new ConfigureParty.Position("keyWords", bPartyKeyWords))
                        )
                )
                .elementsMap(elementsMap)
                .build();
        ContractConfigure flConConfigure = ContractConfigure.builder().id(2L).contractType(ContractType.FINANCE_LEASE).templateCode("FL-001").contractName("融资租赁合同")
                .show(Show.builder().admin(true).fund(true).customer(true).shop(false).build())
                .blankContract(true)
                .configureParties(Arrays.asList(
                                new ConfigureParty(ConfigureParty.Type.CUSTOMER, new ConfigureParty.Position("keyWords", aPartyKeyWords)),
                                new ConfigureParty(ConfigureParty.Type.FINANCE_LEASING_HEAD, new ConfigureParty.Position("keyWords", bPartyKeyWords))
                        )
                )
                .elementsMap(elementsMap)
                .build();

        if (id.equals(1L)) {
            return mortConConfigure;
        }
        if (id.equals(2L)) {
            return flConConfigure;
        }
        return null;
    }
}
