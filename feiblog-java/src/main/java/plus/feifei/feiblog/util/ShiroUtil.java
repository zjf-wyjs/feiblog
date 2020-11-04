package plus.feifei.feiblog.util;

import org.apache.shiro.SecurityUtils;
import plus.feifei.feiblog.shiro.AccountProfile;

public class ShiroUtil {
    /**
     * 获取当前用户
     * @return 当前用户
     */
    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
