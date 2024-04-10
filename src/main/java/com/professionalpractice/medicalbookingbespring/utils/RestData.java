package com.professionalpractice.medicalbookingbespring.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestData<T> {

  private Number status;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  public RestData(Number status, T data) {
    this.status = status;
    this.data = data;
  }

  public static RestData<?> error(Number status, Object message) {
    return new RestData<>(status, message, null);
  }

}
