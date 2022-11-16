package cherry.demo.order.remote.dto;

import java.io.Serializable;

/**
 * @author lee
 * @date 2022/11/10
 */
public class PartyDTO implements Serializable {
    private String name;
    private String number;
    private String mobile;
    private PartyType partyType;
}
