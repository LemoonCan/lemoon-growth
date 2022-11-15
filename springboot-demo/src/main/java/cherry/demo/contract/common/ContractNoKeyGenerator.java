package cherry.demo.contract.common;

/**
 * @author lee
 * @date 2022/11/10
 */
public class ContractNoKeyGenerator {
    private final static String CONTRACT_NO_KEY_PREFIX = "conNum_";
    public static String build(ContractType contractType){
        return CONTRACT_NO_KEY_PREFIX + contractType.name();
    }
}
