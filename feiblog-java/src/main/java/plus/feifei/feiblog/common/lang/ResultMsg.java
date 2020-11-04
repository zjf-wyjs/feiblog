package plus.feifei.feiblog.common.lang;

import lombok.Data;

/**
 * 200表示正常，非200表示异常
 */
@Data
public class ResultMsg {
    private int code;
    private Object data;
    private String msg;

    public ResultMsg setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultMsg setData(Object data) {
        this.data = data;
        return this;
    }

    public ResultMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultMsg(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 处理成功
     * @return
     */
    public static ResultMsg success(){
        return new ResultMsg(200,null,"处理成功");
    }
    /**
     * 处理失败
     */
    public static ResultMsg fail(){
        return new ResultMsg(500,null,"处理失败");
    }
}
