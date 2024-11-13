package uz.qarzim.qarzim_uz.responseObject;

import lombok.Data;

@Data
public class Meta {
    private long totalCount;

    private int size;

    private int page;

    private int totalPage;

    public Meta(long totalCount, int size, int page, int totalPage) {
        this.totalCount = totalCount;
        this.size = size;
        this.page = page;
        this.totalPage = totalPage;
    }
}
