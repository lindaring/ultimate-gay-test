package za.co.lindaring.gay.prop;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class MessageProperties {

    @Value("${messages.user.updateFailed}")
    private String updateFailedMsg;

}
