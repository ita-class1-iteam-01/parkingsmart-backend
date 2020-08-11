package com.oocl.parkingsmart.controller;

import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.service.BookOrderService;
import com.oocl.parkingsmart.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookOrder")
public class BookOrderController {

    @Autowired
    BookOrderService bookOrderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResultVo addBookOrder(@RequestBody BookOrder bookOrder){
        ResultVo<Object> resultVo = new ResultVo<>();
        if(bookOrderService.create(bookOrder)!=null){
            resultVo.setCode(0);
            resultVo.setMsg("booking success");
        }else {
            resultVo.setCode(1);
            resultVo.setMsg("booking fail");
        }
        return resultVo;
    }
}
