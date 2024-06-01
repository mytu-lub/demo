package ss.bean;

import lombok.Data;
import java.util.Set;

/**
 * 用户登陆成功信息
 *
 * @author tujr
 */
@Data
public class UserLoginInfo {
    private String token;

    private Set<String> limits;

}
