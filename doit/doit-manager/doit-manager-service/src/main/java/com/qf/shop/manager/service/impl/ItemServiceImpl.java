package com.qf.shop.manager.service.impl;

import com.qf.shop.common.pojo.dto.ItemResult;
import com.qf.shop.common.pojo.dto.PageInfo;
import com.qf.shop.manager.dao.TbItemCustomMapper;
import com.qf.shop.manager.pojo.po.TbItem;
import com.qf.shop.manager.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemCustomMapper itemCustomDao;

    @Override
    public ItemResult<TbItem> listItemsByPage(PageInfo page) {
        ItemResult<TbItem> result = new ItemResult<TbItem>();
        result.setCode(0);
        result.setMsg("success");
        try {
            //调用dao层接口查询商品的总数量
            long count = itemCustomDao.countItems();
            //调用dao层接口将符合条件的商品集合查询出来
            List<TbItem> list = itemCustomDao.listItemsByPage(page);
            result.setCount(count);
            result.setData(list);
        }catch(Exception e){
            result.setCode(1);
            result.setMsg("failed");
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }
}
