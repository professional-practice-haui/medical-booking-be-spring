package com.professionalpractice.medicalbookingbespring.exceptions;

import com.professionalpractice.medicalbookingbespring.utils.CustomResponse;
import com.professionalpractice.medicalbookingbespring.utils.RestData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

  private final MessageSource messageSource;

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<RestData<?>> handlerNotFoundException(NotFoundException ex) {
    String message = messageSource.getMessage(ex.getMessage(), ex.getParams(), LocaleContextHolder.getLocale());
    log.error(message, ex);
    return CustomResponse.error(HttpStatus.NOT_FOUND, message);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<RestData<?>> handlerInvalidException(BadRequestException ex) {
    log.error(ex.getMessage(), ex);
    String message = messageSource.getMessage(ex.getMessage(), ex.getParams(), LocaleContextHolder.getLocale());
    return CustomResponse.error(HttpStatus.BAD_REQUEST, message);
  }

  @ExceptionHandler(InternalServerException.class)
  public ResponseEntity<RestData<?>> handlerInternalServerException(InternalServerException ex) {
    String message = messageSource.getMessage(ex.getMessage(), ex.getParams(), LocaleContextHolder.getLocale());
    log.error(message, ex);
    return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, message);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<RestData<?>> handleUnauthorizedException(UnauthorizedException ex) {
    String message = messageSource.getMessage(ex.getMessage(), ex.getParams(), LocaleContextHolder.getLocale());
    log.error(message, ex);
    return CustomResponse.error(HttpStatus.UNAUTHORIZED, message);
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<RestData<?>> handleAccessDeniedException(ForbiddenException ex) {
    String message = messageSource.getMessage(ex.getMessage(), ex.getParams(), LocaleContextHolder.getLocale());
    log.error(message, ex);
    return CustomResponse.error(HttpStatus.FORBIDDEN, message);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<RestData<?>> handlerException(Exception ex) {
    log.error(ex.getMessage(), ex);
    return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
  }
}