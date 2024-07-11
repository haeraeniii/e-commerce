package hhplus.e_commerce.base.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiOneResult<T> extends ApiResult
{
    private T val;

    public ApiOneResult(boolean success, String message, T val)
    {
        super (success, message);
        this.val = val;
    }

    public ApiOneResult(T val)
    {
        super (val!=null,	null);
        this.val = val;
    }
}
