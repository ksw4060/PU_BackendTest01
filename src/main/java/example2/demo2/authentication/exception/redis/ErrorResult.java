package example2.demo2.authentication.exception.redis;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {

    private String code;
    private String message;
}
