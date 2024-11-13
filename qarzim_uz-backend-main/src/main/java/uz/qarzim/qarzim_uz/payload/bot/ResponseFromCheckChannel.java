package uz.qarzim.qarzim_uz.payload.bot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFromCheckChannel {

    private Boolean ok;

    private Integer error_code;

    private String description;

    private ResultFromCheckChannel result;
}
