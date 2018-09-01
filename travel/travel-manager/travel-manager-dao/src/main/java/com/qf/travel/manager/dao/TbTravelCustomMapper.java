package com.qf.travel.manager.dao;

import com.qf.travel.common.pojo.dto.PageInfo;
import com.qf.travel.manager.pojo.po.TbTravelOrder;
import com.qf.travel.manager.pojo.vo.TbTravelOrderQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbTravelCustomMapper {
    long countItems(@Param("query") TbTravelOrderQuery query);

    List<TbTravelOrder> listItemsByPage(@Param("page") PageInfo page);
}
