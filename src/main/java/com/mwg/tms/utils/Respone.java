package com.mwg.tms.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respone<T> {
    int code;
    String message;
    T data;
}
