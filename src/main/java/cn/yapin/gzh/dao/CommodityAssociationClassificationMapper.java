package cn.yapin.gzh.dao;


import java.util.List;

import cn.yapin.gzh.entity.Commodity;
import cn.yapin.gzh.entity.CommodityAssociationClassification;

public interface CommodityAssociationClassificationMapper {
    void deleteByPrimaryKey(CommodityAssociationClassification commodityAssociationClassification) throws Exception;

    int insert(CommodityAssociationClassification record) throws Exception;

    void deleteCommodityRecord(Commodity commodity) throws Exception;

    void batchInsert(List<CommodityAssociationClassification> list) throws Exception;
}