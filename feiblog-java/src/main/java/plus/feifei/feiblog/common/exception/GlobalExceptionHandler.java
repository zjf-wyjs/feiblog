package plus.feifei.feiblog.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.session.UnknownSessionException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import plus.feifei.feiblog.common.lang.ResultMsg;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 断言异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResultMsg handler(IllegalArgumentException e){
        log.error("Assert异常：----------------",e);
        return ResultMsg.fail().setMsg(e.getMessage());
    }

    /**
     * 未知settion
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UnknownSessionException.class)
    public ResultMsg handler(UnknownSessionException e){
        log.error("未知session异常");
        return ResultMsg.fail().setMsg("未知session异常");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultMsg handler(MethodArgumentNotValidException e){
        log.error("实体校验异常：----------------",e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return ResultMsg.fail().setMsg(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public ResultMsg handler(ShiroException e){
        log.error("ShiroException异常：----------------",e);
        return ResultMsg.fail().setMsg("你没有权限访问这个资源");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public ResultMsg handler(RuntimeException e){
        log.error("运行时异常：----------------",e);
        return ResultMsg.fail().setMsg(e.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BlogException.class)
    public ResultMsg handler(BlogException e){
        log.error("BlogException异常：----------------",e);
        return ResultMsg.fail().setMsg(e.getMessage());
    }
}
