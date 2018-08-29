package com.qf.shop.manager.service;

import com.qf.shop.common.pojo.dto.ItemResult;
import com.qf.shop.common.pojo.dto.PageInfo;
import com.qf.shop.manager.pojo.po.TbItem;

public interface ItemService {
    ItemResult<TbItem> listItemsByPage(PageInfo page);
}
