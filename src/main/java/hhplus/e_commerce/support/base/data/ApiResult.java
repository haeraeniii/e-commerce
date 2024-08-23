package hhplus.e_commerce.support.base.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult
{
    private boolean success;

    private String message;

    private String data;

    public ApiResult (boolean success, String message)
    {
        this.success = success;
        this.message = message;
    }

    public ApiResult (boolean success, String message, String data)
    {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static ApiResult success (String message)
    {
        return new ApiResult (true, message);
    }

    public static ApiResult fail (String message)
    {
        return new ApiResult (false, message);
    }
}
