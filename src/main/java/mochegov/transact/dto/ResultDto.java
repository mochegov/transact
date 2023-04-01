package mochegov.transact.dto;

import lombok.Data;
import mochegov.transact.enums.Result;

@Data
public class ResultDto {
    private Result result;
    private Long objectId;
    private String errorString;

    public ResultDto() {}

    public ResultDto(Result result, Long objectId, String errorString) {
        this.result = result;
        this.objectId = objectId;
        this.errorString = errorString;
    }

    public static ResultDto resultOk(Long objectId) {
        return new ResultDto(Result.OK, objectId, "");
    }

    public static ResultDto resultError(String errorString) {
        return new ResultDto(Result.ERROR, null, errorString);
    }
}
