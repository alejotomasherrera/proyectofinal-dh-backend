package digitalMonyHouse.cuenta_service.infrastructure.web.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActivityDTO {
    private Long id;
    private String name;
    private Double amount;
    private String date;
    private String origin;
    private String destination;
    private String type;

}
