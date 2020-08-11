package com.oocl.parkingsmart.vo;

import lombok.Data;

@Data
public class ResultVo<T> {
    private Integer Code;
    private T data;
    private String msg;
}
