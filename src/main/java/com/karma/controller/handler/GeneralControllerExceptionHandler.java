package com.karma.controller.handler;

import com.karma.dto.DataDTO;
import com.karma.dto.ErrorDTO;
import com.karma.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GeneralControllerExceptionHandler {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = {ServiceException.class})
    @ResponseBody
    public DataDTO handleServiceException(ServiceException e, HttpServletResponse response) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(Integer.toString(HttpStatus.BAD_REQUEST.value()) + "-Service");
        errorDTO.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        DataDTO dataDTO = new DataDTO();
        dataDTO.setData(errorDTO);
        return dataDTO;
    }

}
