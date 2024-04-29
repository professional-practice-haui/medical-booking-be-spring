package com.professionalpractice.medicalbookingbespring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.professionalpractice.medicalbookingbespring.utils.CustomResponse;
import com.professionalpractice.medicalbookingbespring.utils.RestData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<RestData<?>> handlerNotFoundException(NotFoundException ex) {

    log.error(ex.getMessage(), ex);

    return CustomResponse.error(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<RestData<?>> handlerInvalidException(BadRequestException ex) {

    log.error(ex.getMessage(), ex);

    return CustomResponse.error(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ExceptionHandler(InternalServerException.class)
  public ResponseEntity<RestData<?>> handlerInternalServerException(InternalServerException ex) {

    log.error(ex.getMessage(), ex);

    return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<RestData<?>> handleUnauthorizedException(UnauthorizedException ex) {

    log.error(ex.getMessage(), ex);

    return CustomResponse.error(HttpStatus.UNAUTHORIZED, ex.getMessage());
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<RestData<?>> handleAccessDeniedException(ForbiddenException ex) {

    log.error(ex.getMessage(), ex);

    return CustomResponse.error(HttpStatus.FORBIDDEN, ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<RestData<?>> handlerException(Exception ex) {

    log.error(ex.getMessage(), ex);

    return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
  }
}
