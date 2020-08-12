package com.oocl.parkingsmart.controller;

import com.oocl.parkingsmart.Enum.BookOrderEnum;
import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.entity.CarSpace;
import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.service.BookOrderService;
import com.oocl.parkingsmart.service.CarSpaceService;
import com.oocl.parkingsmart.utils.ResultVoUtils;
import com.oocl.parkingsmart.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

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
        List<CarSpace> unUsedCarSpaces = carSpaceService.getUnUsedCarSpaces(bookOrder.getParkingId(), bookOrder.getReservationStartTime(), bookOrder.getReservationEndTime());
        if(unUsedCarSpaces.isEmpty()){
            resultVo.setCode(1);
            resultVo.setMsg("booking fail");
            return resultVo;
        }
        CarSpace carSpace = unUsedCarSpaces.get(0);
        bookOrder.setCarPort(carSpace.getCarPort());
        if(bookOrderService.create(bookOrder)!=null){
            resultVo.setCode(0);
            resultVo.setMsg("booking success");
            resultVo.setData(carSpace);
        }else {
            resultVo.setCode(1);
            resultVo.setMsg("booking fail");
        }
        return resultVo;
    }

    @PostMapping("/personal/{rentOrderId}")
    @ResponseStatus(HttpStatus.CREATED)
    ResultVo addPersonalBookOrder(@RequestBody BookOrder bookOrder,@PathVariable("rentOrderId") Integer rentOrderId){
        BookOrder returnedOrder = bookOrderService.createPersonalOrder(bookOrder, rentOrderId);
        if(returnedOrder != null){
            return ResultVoUtils.success("success",null);
        }
        return ResultVoUtils.fail("book fail");
    }

}
