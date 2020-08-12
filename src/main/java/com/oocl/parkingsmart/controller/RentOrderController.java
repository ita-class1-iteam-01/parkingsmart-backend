package com.oocl.parkingsmart.controller;

import com.oocl.parkingsmart.Enum.RentOrderEnum;
import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.service.RentOrderService;
import com.oocl.parkingsmart.utils.ResultVoUtils;
import com.oocl.parkingsmart.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentOrders")
@CrossOrigin
public class RentOrderController {

    @Autowired
    RentOrderService rentOrderService;

    @GetMapping
    public ResultVo getAll(){
        return ResultVoUtils.success("success",rentOrderService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVo createOrder(@RequestBody RentOrder rentOrder){
        rentOrder.setStatus(RentOrderEnum.PUBLISHED.getValue());
        if(rentOrderService.create(rentOrder)!=null){
            return ResultVoUtils.success("Renting success",null);
        }else {
            return ResultVoUtils.fail("Renting fail");
        }
    }

}
