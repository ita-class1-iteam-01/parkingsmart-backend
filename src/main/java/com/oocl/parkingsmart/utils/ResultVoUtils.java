package com.oocl.parkingsmart.utils;

import com.oocl.parkingsmart.vo.ResultVo;

public class ResultVoUtils {

    public static <T> ResultVo success(String msg, T data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setData(data);
        resultVo.setCode(0);
        resultVo.setMsg(msg);
        return resultVo;
    }

}
