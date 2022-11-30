package cherry.demo.order.remote.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lee
 * @date 2022/11/10
 */
@Builder
@Getter
public class ContractDTO implements Serializable {
    private String contractNo;
    private String templateVersion;
    private String templateCode;
    private List<ContractElementDTO> elements;
    private Date signAt;
    private Long templateVersionId;
    private Long templateId;
    private PartyDTO firstParty;
    private PartyDTO secondParty;
    private List<String> imageUrls;
    private String eviId;
    private String contractId;
    private Date finishDate;
    private String pdfUrl;
}
