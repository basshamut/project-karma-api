package com.karma.controller.handler;

import com.karma.service.mapper.dto.DataDTO;
import com.karma.service.mapper.dto.ErrorDTO;
import com.karma.exceptions.ServiceException;
import com.karma.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GeneralControllerExceptionHandler {

	@ExceptionHandler(value = {ServiceException.class})
    @ResponseBody
    public DataDTO<ErrorDTO> handleServiceException(ServiceException e, HttpServletResponse response) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMessage(e.getMessage());
        DataDTO<ErrorDTO> dataDTO = new DataDTO<>();
        dataDTO.setCode(e.getCode().toString());
        dataDTO.setMessage(HttpStatus.BAD_REQUEST.name());
        dataDTO.setData(errorDTO);
        return dataDTO;
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseBody
    public DataDTO<ErrorDTO> handleConstraintViolationException(ConstraintViolationException e, HttpServletResponse response) {
        final ErrorDTO errorDTO = new ErrorDTO();

        List<String> violations = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        errorDTO.setErrorMessage(Constants.BAD_REQUEST_BASIC_LABEL + Constants.QUERY_PARAM_NAME + String.join(", ", violations));

        DataDTO<ErrorDTO> dataDTO = new DataDTO<>();
        dataDTO.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        dataDTO.setMessage(HttpStatus.BAD_REQUEST.name());
        dataDTO.setData(errorDTO);
        return dataDTO;
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public DataDTO<ErrorDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletResponse response) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMessage(e.getMessage());
        DataDTO<ErrorDTO> dataDTO = new DataDTO<>();
        dataDTO.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        dataDTO.setMessage(HttpStatus.BAD_REQUEST.name());
        dataDTO.setData(errorDTO);
        return dataDTO;
    }
}
