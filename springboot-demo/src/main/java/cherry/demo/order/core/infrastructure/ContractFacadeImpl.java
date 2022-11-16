package cherry.demo.order.core.infrastructure;

import cherry.demo.order.api.IContractFacade;
import cherry.demo.order.api.dto.PreviewContractDTO;
import cherry.demo.order.api.dto.SignedContractDTO;
import cherry.demo.order.core.adapter.ContractFlow;
import cherry.demo.order.core.domain.IOrderDomainService;
import cherry.demo.order.core.domain.model.Contract;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/16
 */
public class ContractFacadeImpl implements IContractFacade {
    private ContractFlow contractFlow;
    private IOrderDomainService orderDomainService;

    @Override
    public List<PreviewContractDTO> previewList(String orderNo) {
        return contractFlow.previewList(orderNo);
    }

    @Override
    public String previewContent(String orderNo, Long contractId) {
        return contractFlow.previewContent(orderNo, contractId);
    }

    @Override
    public void signContract(String orderNo, String captcha) {
        List<Contract> contracts = contractFlow.signContract(orderNo, captcha);
        orderDomainService.signContract(orderNo, contracts);
    }

    @Override
    public List<SignedContractDTO> signedList(String orderNo) {
        return null;
    }
}
