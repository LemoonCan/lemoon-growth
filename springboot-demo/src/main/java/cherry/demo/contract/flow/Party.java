package cherry.demo.contract.flow;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/4
 */
public class Party {
    private String smsCode;
    private Boolean safeSign = false;
    private String name;
    private String number;
    private String mobile;
    private String email;
    private String accountId;
    private String creatorName;
    private String creatorNumber;
    private String creatorMobile;
    private Boolean sendNotice = false;
    private PosBean posBean;
    private List<PosBean> posBeans;
    private String sealData;
    private String sealUrl;
    private PartyType partyType;
    boolean isVisible;
    private PersonSeal legalPerson;
    private String signNo;
    private boolean sign;
    private Boolean platformSign;
    private String legalName;
    private String legalNumber;
    private String legalMobile;
    private Integer signOrder;
    private String authorizedAccountId;
    private List<String> sealIds;
    private Boolean isRound;
    private Float scaleRate;
}
