package com.professionalpractice.medicalbookingbespring.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomException extends RuntimeException {

  private Object errMessage;

  private HttpStatus status;

  private String[] params;

  public CustomException(String errMessage) {
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    this.errMessage = errMessage;
  }

  public CustomException(HttpStatus status, Object errMessage) {
    this.errMessage = errMessage;
    this.status = status;
  }

  public CustomException(String errMessage, String[] params) {
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    this.errMessage = errMessage;
    this.params = params;
  }

  public CustomException(HttpStatus status, String errMessage, String[] params) {
    this.status = status;
    this.errMessage = errMessage;
    this.params = params;
  }

}
