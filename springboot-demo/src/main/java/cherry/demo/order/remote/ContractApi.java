package cherry.demo.order.remote;

import cherry.demo.order.remote.dto.ContractDTO;
import cherry.demo.order.remote.dto.MultiSignParam;

import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/10
 */
public interface ContractApi {
    String getContractHtml(String templateCode, Map<String,Object> elements);
    List<ContractDTO> multiSignContract(MultiSignParam multiSignParam);

    String getTemplatePdfUrl(String templateCode, Map<String,Object> elements);
}
