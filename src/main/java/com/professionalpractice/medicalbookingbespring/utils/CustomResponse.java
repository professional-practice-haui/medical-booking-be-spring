package com.professionalpractice.medicalbookingbespring.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class CustomResponse {

  public static ResponseEntity<RestData<?>> success(Object data) {
    return success(HttpStatus.OK, data);
  }

  public static ResponseEntity<RestData<?>> success(HttpStatus status, Object data) {
    RestData<?> response = new RestData<>(status.value(), data);
    return new ResponseEntity<>(response, status);
  }

  public static ResponseEntity<RestData<?>> success(MultiValueMap<String, String> header, Object data) {
    return success(HttpStatus.OK, header, data);
  }

  public static ResponseEntity<RestData<?>> success(HttpStatus status, MultiValueMap<String, String> header, Object data) {
    RestData<?> response = new RestData<>(status.value(), data);
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.addAll(header);
    return ResponseEntity.ok().headers(responseHeaders).body(response);
  }

  public static ResponseEntity<RestData<?>> error(HttpStatus status, Object message) {
    RestData<?> response = RestData.error(status.value(), message);
    return new ResponseEntity<>(response, status);
  }
}
