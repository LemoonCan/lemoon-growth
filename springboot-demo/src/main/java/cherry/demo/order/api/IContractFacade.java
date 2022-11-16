package cherry.demo.order.api;

import cherry.demo.order.api.dto.PreviewContractDTO;
import cherry.demo.order.api.dto.SignedContractDTO;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/16
 */
public interface IContractFacade {
    List<PreviewContractDTO> previewList(String orderNo);
    String previewContent(String orderNo, Long contractId);

    void signContract(String orderNo, String captcha);
    List<SignedContractDTO> signedList(String orderNo);
}
