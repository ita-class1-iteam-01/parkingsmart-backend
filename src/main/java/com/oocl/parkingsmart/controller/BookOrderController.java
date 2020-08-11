package com.oocl.parkingsmart.controller;

import com.oocl.parkingsmart.Enum.BookOrderEnum;
import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.entity.CarSpace;
import com.oocl.parkingsmart.service.BookOrderService;
import com.oocl.parkingsmart.service.CarSpaceService;
import com.oocl.parkingsmart.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookOrders")
@CrossOrigin
public class BookOrderController {

    @Autowired
    BookOrderService bookOrderService;

    @Autowired
    CarSpaceService carSpaceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResultVo<CarSpace> addBookOrder(@RequestBody BookOrder bookOrder){
        ResultVo<CarSpace> resultVo = new ResultVo<>();
        bookOrder.setStatus(BookOrderEnum.BOOKED.getValue());
        if(bookOrderService.create(bookOrder)!=null){
            CarSpace carSpace = carSpaceService.getOneCarSpace(bookOrder.getParkingId());
            resultVo.setCode(0);
            resultVo.setMsg("booking success");
            resultVo.setData(carSpace);
        }else {
            resultVo.setCode(1);
            resultVo.setMsg("booking fail");
        }
        return resultVo;
    }
}
