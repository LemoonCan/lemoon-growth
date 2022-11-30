package cherry.demo.order.remote;

import cherry.demo.order.remote.dto.ContractDTO;
import cherry.demo.order.remote.dto.MultiSignParam;
import cherry.demo.order.remote.dto.SingleSignParam;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/10
 */
@Component
public class ContractApiImpl implements ContractApi {
    @Override
    public String getContractHtml(String templateCode, Map<String, Object> elements) {
        return "contractHtml";
    }

    @Override
    public List<ContractDTO> multiSignContract(MultiSignParam multiSignParam) {
        List<ContractDTO> contractDTOs = new ArrayList<>();

        for (SingleSignParam item:multiSignParam.getSingleSignParams()){
            ContractDTO contractDTO = ContractDTO.builder()
                    .templateCode(item.getTemplateCode())
                    .contractNo(item.getContractNo())
                    .elements(new ArrayList<>())
                    .signAt(new Date())
                    .build();
            contractDTOs.add(contractDTO);
        }
       return contractDTOs;
    }

    @Override
    public String getTemplatePdfUrl(String templateCode, Map<String, Object> elements) {
        return "contractUrl";
    }
}
