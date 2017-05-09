package com.zcckj.storeshow.mapper;

import com.zcckj.storeshow.common.BaseMapper;
import com.zcckj.storeshow.dto.ShowDzLogDto;
import com.zcckj.storeshow.entity.ShowDzLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhangjianbo
 * @since 2017-04-17
 */
@Mapper
public interface ShowDzLogMapper extends BaseMapper<ShowDzLog> {

    /**
     * 根据门店id，获取本门店未读消息数
     * @param storeId 门店id
     * @return
     */
    @Select("SELECT COUNT(1) FROM STORE_SHOW A INNER JOIN SHOW_DZ_LOG B ON A.ID=B.SHOW_ID " +
            "WHERE READ_FLAG='N' AND B.DZ_CHANNEL='APP' AND A.STORE_ID = #{storeId}")
    public Long getUnReadLogsCnt(Long storeId);

    /**
     * 根据门店id，按点赞ID降序，获取本门店未读消息列表，限制最多100笔
     * @param storeId 门店id
     * @return
     */
    @Select("SELECT A.ID,B.ID DZ_LOG_ID, B.CREATE_BY STORE_ID,B.CREATE_BY_NAME STORE_NAME,B.STORE_ICON_URL,C.PHOTO_URL,B.CREATE_TM " +
            "FROM STORE_SHOW A,SHOW_DZ_LOG B,SHOW_PHOTO C "+
            "WHERE B.SHOW_ID=A.ID AND C.ID=(SELECT MIN(ID) FROM SHOW_PHOTO P WHERE P.SHOW_ID=A.ID) " +
            "AND B.READ_FLAG='N' AND B.DZ_CHANNEL='APP' AND A.STORE_ID = #{0} " +
            "ORDER BY B.ID DESC LIMIT 100 ")
    @Results({
            @Result(property = "dzLogId",  column = "DZ_LOG_ID"),
            @Result(property = "storeId",  column = "STORE_ID"),
            @Result(property = "storeName", column = "STORE_NAME"),
            @Result(property = "storeIconUrl", column = "STORE_ICON_URL"),
            @Result(property = "photoUrl", column = "PHOTO_URL"),
            @Result(property = "createTm", column = "CREATE_TM")
    })
    public List<ShowDzLogDto> getUnReadLogs(Long storeId);


    /**
     * 根据门店id和上一页最后一笔数据dzLogId，获取本门店已读消息列表
     * @param storeId 门店id
     * @param lastId 上一页最后一笔点赞数据id
     * @param pageSize
     * @return
     */
    @Select({"<script>",
            "SELECT A.ID,B.ID DZ_LOG_ID,B.CREATE_BY STORE_ID,B.CREATE_BY_NAME STORE_NAME,B.STORE_ICON_URL,C.PHOTO_URL,B.CREATE_TM "+
                    "FROM STORE_SHOW A,SHOW_DZ_LOG B,SHOW_PHOTO C "+
                    "WHERE B.SHOW_ID=A.ID AND C.ID=(SELECT MIN(ID) FROM SHOW_PHOTO P WHERE P.SHOW_ID=A.ID) " +
                    "AND B.DZ_CHANNEL='APP' AND A.STORE_ID = #{storeId} " +
                    "<if test=' lastId &gt; 0'>"+
                    "AND B.ID &lt; #{lastId} "+
                    "</if>"+
                    "ORDER BY B.ID DESC "+
                    "LIMIT 0,#{pageSize}" ,
            "</script>"})
    @Results({
            @Result(property = "dzLogId",  column = "DZ_LOG_ID"),
            @Result(property = "storeId",  column = "STORE_ID"),
            @Result(property = "storeName", column = "STORE_NAME"),
            @Result(property = "storeIconUrl", column = "STORE_ICON_URL"),
            @Result(property = "photoUrl", column = "PHOTO_URL"),
            @Result(property = "createTm", column = "CREATE_TM")
    })
    public List<ShowDzLogDto> getReadedLogs(@Param("storeId") Long storeId, @Param("lastId") Long lastId, @Param("pageSize") Integer pageSize);

    /**
     * 根据门店id，获取已读消息总数
     * @param storeId
     * @return
     */
    @Select("SELECT COUNT(1) FROM STORE_SHOW A INNER JOIN SHOW_DZ_LOG B ON A.ID=B.SHOW_ID " +
            "WHERE READ_FLAG='Y' AND B.DZ_CHANNEL='APP' AND A.STORE_ID = #{storeId}")
    public Integer getReadedLogsCnt(Long storeId);

    /**
     * 根据门店id，未读消息列表，更新所有点赞信息为已读
     * @param storeId 门店id
     * @return
     */
    @Update("UPDATE SHOW_DZ_LOG B SET B.READ_FLAG='Y' " +
            "WHERE B.READ_FLAG='N' AND B.DZ_CHANNEL='APP' " +
            "AND B.SHOW_ID IN(SELECT A.ID FROM STORE_SHOW A WHERE A.STORE_ID = #{storeId}) ")
    public Long updateUnReadLogsAll(Long storeId);
}
