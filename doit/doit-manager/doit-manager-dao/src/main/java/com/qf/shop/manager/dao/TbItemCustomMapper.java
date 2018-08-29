package com.qf.shop.manager.dao;

import com.qf.shop.common.pojo.dto.PageInfo;
import com.qf.shop.manager.pojo.po.TbItem;

import java.util.List;

public interface TbItemCustomMapper {
    long countItems();

    List<TbItem> listItemsByPage(PageInfo page);
}
