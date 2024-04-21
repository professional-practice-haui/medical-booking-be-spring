package com.professionalpractice.medicalbookingbespring.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestData<T> {

    private Number code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public RestData(Number code, T data) {
        this.code = code;
        this.data = data;
    }

    public static RestData<?> error(Number code, Object message) {
        return new RestData<>(code, message, null);
    }

}
