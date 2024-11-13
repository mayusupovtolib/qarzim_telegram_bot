package uz.qarzim.qarzim_uz.utill;

import uz.qarzim.qarzim_uz.exeption.PageSizeException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CommandUtils {

    public static void validatePageAndSize(int page, int size) throws PageSizeException {
        if (page < 0) {
            throw new PageSizeException("Invalid Page number. Page number must not be less than zero!");
        } else if (size < 0) {
            throw new PageSizeException("Invalid Page size. Page number must not be less zero!");
        } else if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new PageSizeException("Invalid Page size. Page size must not be less than zero!");
        }
    }

    public static  Pageable simplePageable(int page, int size) throws PageSizeException {
        validatePageAndSize(page - 1, size);
        return PageRequest.of(page - 1, size);
    }
    public static Pageable descOrAscByCreatedPageable(int page, int size,boolean asc) throws PageSizeException{
        validatePageAndSize(page, size);
        if(asc){
            return PageRequest.of(page, size, Sort.Direction.ASC,"createdAt");
        }
        return PageRequest.of(page, size, Sort.Direction.DESC,"createdAt");
    }
}