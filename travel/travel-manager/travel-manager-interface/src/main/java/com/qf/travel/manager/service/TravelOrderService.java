package com.qf.travel.manager.service;


import com.qf.travel.common.pojo.dto.ItemResult;
import com.qf.travel.common.pojo.dto.PageInfo;
import com.qf.travel.manager.pojo.po.TbTravelOrder;
import com.qf.travel.manager.pojo.vo.TbTravelOrderQuery;

import java.util.List;

public interface TravelOrderService {
    ItemResult<TbTravelOrder> listTravelOrderByPage(PageInfo page, TbTravelOrderQuery query);

    //批量修改状态为3
    int updateTravelOrderByIds(List<String> ids);

    int addTravelOrder(TbTravelOrder torder);
}
