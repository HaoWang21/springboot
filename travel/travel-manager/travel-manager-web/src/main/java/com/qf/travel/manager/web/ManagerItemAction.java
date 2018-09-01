package com.qf.travel.manager.web;

import com.qf.travel.common.pojo.dto.ItemResult;
import com.qf.travel.common.pojo.dto.PageInfo;
import com.qf.travel.manager.pojo.po.TbTravelOrder;
import com.qf.travel.manager.pojo.vo.TbTravelOrderQuery;
import com.qf.travel.manager.service.TravelOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ManagerItemAction {
    //初始化logger
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //注入service层依赖接口
    @Autowired
    private TravelOrderService tOrderService;

    //查询所有
    @ResponseBody
    @RequestMapping(value = "/travelOrders",method = RequestMethod.GET)
    public ItemResult<TbTravelOrder> listTravelOrderByPage(PageInfo page,TbTravelOrderQuery query){
        ItemResult<TbTravelOrder> result = null;
        try {
            result = tOrderService.listTravelOrderByPage(page,query);
        }catch (Exception e){
            //通过logback将异常打印到日志中
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    //批量删除
    @ResponseBody
    @RequestMapping(value = "/travelorder/batch",method = RequestMethod.POST)
    public Object updateTravelOrderByIds(@RequestParam("ids[]") List<String> ids){
        int i = 0;
        try {
            i = tOrderService.updateTravelOrderByIds(ids);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    //新增
    @ResponseBody
    @RequestMapping(value = "/travelorder/add",method = RequestMethod.POST)
    public Object addTravelOrder(TbTravelOrder torder){
        int i = 0;
        try {
            i = tOrderService.addTravelOrder(torder);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

}
