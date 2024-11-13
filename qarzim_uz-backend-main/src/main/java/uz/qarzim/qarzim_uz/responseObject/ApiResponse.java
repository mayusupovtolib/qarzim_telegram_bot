package uz.qarzim.qarzim_uz.responseObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private int dataStatus;

    private String message;

    private Object data;

    public ApiResponse(int dataStatus, String message) {
        this.dataStatus = dataStatus;
        this.message = message;
    }
}
