package com.qf.travel.manager.service.impl;

import com.qf.travel.common.pojo.dto.ItemResult;
import com.qf.travel.common.pojo.dto.PageInfo;
import com.qf.travel.manager.dao.TbTravelCustomMapper;
import com.qf.travel.manager.dao.TbTravelMapper;
import com.qf.travel.manager.dao.TbTravelOrderMapper;
import com.qf.travel.manager.pojo.po.TbTravelOrder;
import com.qf.travel.manager.pojo.po.TbTravelOrderExample;
import com.qf.travel.manager.pojo.vo.TbTravelOrderQuery;
import com.qf.travel.manager.service.TravelOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TravelOrderServiceImpl implements TravelOrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbTravelCustomMapper ttCustomDao;

    @Autowired
    private TbTravelOrderMapper ttOrderDao;

    @Override
    public ItemResult<TbTravelOrder> listTravelOrderByPage(PageInfo page, TbTravelOrderQuery query) {
        ItemResult<TbTravelOrder> result = new ItemResult<TbTravelOrder>();
        result.setCode(0);
        result.setMsg("success");
        try {
            //调用dao层接口查询总数量
            long count = ttCustomDao.countItems(query);
            //调用dao层接口将符合条件的集合查询出来
            List<TbTravelOrder> list = ttCustomDao.listItemsByPage(page);
            /*for (int i=0;i<list.size();i++){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
                sdf.format(list.get(i).getCloseTime());
                sdf.format(list.get(i).getConsignTime());
                sdf.format(list.get(i).getCreateTime());
                sdf.format(list.get(i).getEndTime());
                sdf.format(list.get(i).getPaymentTime());
                sdf.format(list.get(i).getUpdateTime());
            }*/
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

    //批量删除
    @Override
    public int updateTravelOrderByIds(List<String> ids) {
        int i = 0;
        try {
            //封装了一个旅游订单对象TbTravelOrder 携带了删除状态
            TbTravelOrder record = new TbTravelOrder();
            record.setStatus(7);
            //使用example
            //创建模板
            TbTravelOrderExample example = new TbTravelOrderExample();
            TbTravelOrderExample.Criteria criteria = example.createCriteria();
            //设值
            criteria.andOrderIdIn(ids);
            //真正执行修改状态为3 即不在页面展示出来
            //相当于update tb_item set status=3 where id in (数组)
            i = ttOrderDao.updateByExampleSelective(record,example);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int addTravelOrder(TbTravelOrder torder) {
        int i = 0;
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.format(date);
            torder.setCloseTime(date);
            torder.setConsignTime(date);
            torder.setCreateTime(date);
            torder.setEndTime(date);
            torder.setPaymentTime(date);
            torder.setUpdateTime(date);
            i = ttOrderDao.insert(torder);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

}
