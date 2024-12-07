package com.einstein.web.controller;

import com.einstein.common.entity.exception.BusinessException;
import com.einstein.common.entity.exception.authentication.AuthenticationException;
import com.einstein.common.entity.exception.authentication.UnauthorizedException;
import com.einstein.web.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/9/25
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private final HttpServletRequest request;

    public GlobalExceptionHandler(HttpServletRequest request) {
        this.request = request;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result<String> handler(BusinessException exception) {
        log.error("业务异常:{}", exception.getMsg());
        return Result.fail(exception.getCode(), exception.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handler(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        if (fieldError != null) {
            String defaultMessage = fieldError.getDefaultMessage();
            log.error("Path:{} 字段【{}】{}", request.getServletPath(), fieldError.getField(), defaultMessage);
            return Result.fail(400, defaultMessage);
        }
        return Result.fail(500, exception.getMessage());
    }

    /**
     * <p>请求方式不支持</p>
     *
     * @param exception - [HttpRequestMethodNotSupportedException]
     * @return cn.com.agree.client.console.entity.vo.Result
     * @author 张春杰
     * @date 2022/7/29
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result<String> commonExceptionHandler(HttpRequestMethodNotSupportedException exception) {
        String methods = Arrays.toString(exception.getSupportedMethods());
        log.error("Path:{} 请求方式不支持:{},仅支持:{}", request.getServletPath(), exception.getMessage(), methods);
        return Result.fail(HttpStatus.METHOD_NOT_ALLOWED.value(), String.format("请求方式不支持，仅支持%s", methods));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> commonExceptionHandler(UnauthorizedException exception) {
        log.error("Path:{} request failed.{}", request.getServletPath(), exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatus()).body(Result.fail(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> commonExceptionHandler(AuthenticationException exception) {
        log.error("Path:{} request failed.{}", request.getServletPath(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.fail(exception.getCode(), exception.getMessage()));
    }

    /**
     * <p>缺少请求参数</p>
     *
     * @param exception MissingServletRequestParameterException
     * @return Result
     * @author 张春杰
     * @date 2021/9/8
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> commonExceptionHandler(MissingServletRequestParameterException exception) {
        log.error("请求参数缺失:{}", exception.getMessage());
        return Result.fail(HttpStatus.BAD_REQUEST.value(), String.format("请求参数%s缺失", exception.getParameterName()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handler(Exception exception) {
        log.error("handler exception.", exception);
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统异常，请稍后重试");
    }
}
