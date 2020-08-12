package com.oocl.parkingsmart.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oocl.parkingsmart.model.ParkingLot;
import com.oocl.parkingsmart.service.ParkingLotService;
import com.oocl.parkingsmart.utils.ResultVoUtils;
import com.oocl.parkingsmart.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("parkingLots")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @GetMapping(value = "/{id}",params = {"startTime","endTime"})
    public ResultVo getOne(@PathVariable("id") Integer parkingLotId,@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date startTime,@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime){
        ParkingLot result = parkingLotService.findOneByIdAndStartTimeAndEndTime(parkingLotId, startTime,  endTime);
        return ResultVoUtils.success("success",result);
    }
}
