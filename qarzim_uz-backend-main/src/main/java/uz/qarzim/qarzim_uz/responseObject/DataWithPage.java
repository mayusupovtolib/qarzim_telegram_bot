package uz.qarzim.qarzim_uz.responseObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@Data
public class DataWithPage {
    private Object items;
    private Meta meta;

    public DataWithPage(Page<?> data, int page, int size) {
        this.meta = new Meta(
                page,
                size,
                data.getTotalElements(),
                data.getTotalPages(),
                page > 1,
                page < data.getTotalPages()
        );
        this.items = data.stream().collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    public class Meta {
        private int page;
        private int size;
        private long totalCount;
        private int totalPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
    }

}
