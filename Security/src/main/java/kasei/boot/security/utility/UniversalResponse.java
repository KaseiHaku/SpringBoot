package kasei.boot.security.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversalResponse {

    private int state;
    private String msg;
    private Object data;
}
