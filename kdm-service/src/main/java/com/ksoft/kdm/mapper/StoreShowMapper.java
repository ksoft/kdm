package com.zcckj.storeshow.mapper;

import com.zcckj.storeshow.common.BaseMapper;
import com.zcckj.storeshow.entity.StoreShow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * StoreShowMapper
 *
 * @author HZH
 * @date 2017/4/17
 */
@Mapper
public interface StoreShowMapper extends BaseMapper<StoreShow> {

    /**
     * 统计累计发布量、累计发布门店、累计关联轮胎规格
     * @return
     */
    @Select("SELECT  " +
            "(SELECT COUNT(1) FROM STORE_SHOW) total, " +
            //门店数量
            "(SELECT COUNT(1) FROM ( " +
            "SELECT store_id FROM STORE_SHOW GROUP BY store_id " +
            ") t) storeTotal, " +
            //轮胎规格数量
            "(SELECT COUNT(1) FROM ( " +
            "SELECT tyre_spec_id FROM STORE_SHOW GROUP BY tyre_spec_id " +
            ") t) specTotal")
    public Map<String,Long> statisticStoreAndSpec();

}
