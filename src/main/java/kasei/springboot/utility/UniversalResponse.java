package kasei.springboot.utility;

import lombok.Data;

@Data
public class UniversalResponse {

    private Integer state;
    private String msg;
    private Object data;

    public UniversalResponse(Integer state, String msg, Object data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }
}
