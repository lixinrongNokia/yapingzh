package cn.yapin.gzh.dao;


import java.util.List;

import cn.yapin.gzh.entity.OrderDetails;

public interface OrderDetailsMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(OrderDetails record);

    void insertSelective(OrderDetails record);

    void batchInsert(List<OrderDetails> record);

    List<OrderDetails> selectByPrimaryKey(String id);
}