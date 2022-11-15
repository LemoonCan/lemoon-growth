package cherry.demo.contract.remote;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author lee
 * @date 2022/11/10
 */
@Getter
public class ContractElementDTO implements Serializable {
    private String name;
    private String value;
}
