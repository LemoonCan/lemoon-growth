package cherry.demo.contract.remote;

import lombok.Data;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/7
 */
@Data
public class MultiSignParam {
    private String captcha;
    private List<SingleSignParam> SingleSignParams;
}
