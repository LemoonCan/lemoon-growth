package cherry.demo.order.remote.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author lee
 * @date 2022/11/4
 */
@Data
public class SingleSignParam {
    private String contractNo;
    private String contractName;
    private String captcha;
    private String content;
    private String templateCode;
    private String templateVersion;
    private Party firstParty;
    private Party secondParty;
    private Party thirdParty;
    private Map<String, Object> elements;
}
