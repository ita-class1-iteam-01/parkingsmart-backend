package com.oocl.parkingsmart.controller;

import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.service.RentOrderService;
import com.oocl.parkingsmart.utils.ResultVoUtils;
import com.oocl.parkingsmart.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rentOrders")
@CrossOrigin
public class RentOrderController {

    @Autowired
    RentOrderService rentOrderService;

    @GetMapping
    public ResultVo<List<RentOrder>> getAll(){
        return ResultVoUtils.success("success",rentOrderService.getAll());
    }

}
