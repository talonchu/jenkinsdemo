package com.gsoft.Exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gsoft.cos3.dto.ReturnDto;
import com.gsoft.cos3.exception.ReturnCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author plsy
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    protected Log logger = LogFactory.getLog(getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object defaultErrorHandler(HttpServletRequest request, Exception exception) throws Exception {
        logger.error(exception.getMessage());
        exception.printStackTrace();
        return new ReturnDto(ReturnCode.FAIL_STATUS,exception.getMessage());
    }

}
