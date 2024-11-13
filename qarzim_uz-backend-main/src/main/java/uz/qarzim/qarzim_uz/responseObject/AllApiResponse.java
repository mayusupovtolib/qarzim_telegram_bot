package uz.qarzim.qarzim_uz.responseObject;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public class AllApiResponse {

    public static ResponseEntity<?> response(int dataStatus, String message) {
        return ResponseEntity.ok(new ApiResponse(dataStatus, message));
    }

    public static ResponseEntity<?> response(int dataStatus, String message, Object data) {
        return ResponseEntity.ok(new ApiResponse(dataStatus, message, data));
    }

    public static ResponseEntity<?> response(Integer status, int dataStatus, String message) {
        return ResponseEntity.status(status).body(new ApiResponse(dataStatus, message));
    }

    public static ResponseEntity<?> response(Integer status, int dataStatus, String message, Object data) {
        return ResponseEntity.status(status).body(new ApiResponse(dataStatus, message, data));
    }

    public static ResponseEntity<?> response(Integer status, int dataStatus, String message, Page<?> data, int page, int size) {
        return ResponseEntity.status(status).body(new ApiResponse(dataStatus, message, new DataWithPage(data, page, size)));
    }

    public static ResponseEntity<?> response(int dataStatus, String message, Page<?> data, int page, int size) {
        return ResponseEntity.ok(new ApiResponse(dataStatus, message, new DataWithPage(data, page, size)));
    }
}
