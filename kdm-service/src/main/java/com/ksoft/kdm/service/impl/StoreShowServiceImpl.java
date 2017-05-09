package com.zcckj.storeshow.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageRowBounds;
import com.zcckj.storeshow.common.PageDto;
import com.zcckj.storeshow.dto.*;
import com.zcckj.storeshow.entity.ShowDzLog;
import com.zcckj.storeshow.entity.ShowPhoto;
import com.zcckj.storeshow.entity.StoreShow;
import com.zcckj.storeshow.enums.DzChannelEnum;
import com.zcckj.storeshow.enums.JxStatusEnum;
import com.zcckj.storeshow.enums.StatusEnum;
import com.zcckj.storeshow.mapper.ShowDzLogMapper;
import com.zcckj.storeshow.mapper.ShowPhotoMapper;
import com.zcckj.storeshow.mapper.StoreShowMapper;
import com.zcckj.storeshow.service.StoreShowService;
import com.zcckj.storeshow.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * StoreShowServiceImpl
 *
 * @author HZH
 * @date 2017/4/14
 */
@Service(version = "1.0.0",interfaceClass = StoreShowService.class)
@Transactional
public class StoreShowServiceImpl implements StoreShowService {
    @Autowired
    StoreShowMapper storeShowMapper;
    @Autowired
    ShowPhotoMapper showPhotoMapper;
    @Autowired
    ShowDzLogMapper showDzLogMapper;

    public ResponseDto<PageDto<StoreShowListDto>> getSimplePage(StoreShowListSearchDto listSearchDto) {
        StoreShowSearchDto searchDto = new StoreShowSearchDto();
        searchDto.setJxStatus(JxStatusEnum.JX);
        searchDto.setStatus(StatusEnum.Y);
        BeanUtils.copyProperties(listSearchDto, searchDto);
        return getPage(searchDto,new StoreShowListDto());
    }

    private List<StoreShowListDto> toStoreShowListDtos(List<StoreShow> entitys){
        List<StoreShowListDto> dtos = new ArrayList<>();
        if(null != entitys && entitys.size() > 0)
            for(StoreShow entity : entitys){
                if(entity != null){
                    String carName = entity.getCarBrandName()+" "+entity.getCarSeriesName();
                    StoreShowListDto dto = new StoreShowListDto(entity.getId(), entity.getStoreId(), entity.getStoreName(), entity.getContent(), carName, entity.getTyreSpecName(), entity.getDzNum(), entity.getFxNum(), entity.getModifyTm(), entity.getCreateTm());
                    if(null != entity.getId()){
                        //查询图片
                        dto.setPhotos(getPhotos(entity.getId().toString()));
                    }
                    dtos.add(dto);
                }
            }
        return dtos;
    }


    public ResponseDto<PageDto<StoreShowDetailListDto>> getDetailPage(StoreShowSearchDto searchDto) {
        return getPage(searchDto,new StoreShowDetailListDto());
    }

    private List<StoreShowDetailListDto> toStoreShowDetailListDtos(List<StoreShow> entitys){
        List<StoreShowDetailListDto> dtos = new ArrayList<>();
        if(null != entitys && entitys.size() > 0)
            for(StoreShow entity : entitys){
                if(entity != null){
                    String carName = entity.getCarBrandName()+" "+entity.getCarSeriesName();
                    StoreShowDetailListDto dto = new StoreShowDetailListDto(entity.getId(), entity.getStoreId(), entity.getStoreName(), entity.getContent(),
                            carName, entity.getTyreSpecName(), entity.getDzNum(), entity.getFxNum(), entity.getModifyTm(), entity.getCreateTm(),
                            entity.getSupplierName(),entity.getCusDzNum(),entity.getJxStatus(),entity.getLable(),entity.getStatus());
                    if(null != entity.getId()){
                        //查询图片
                        dto.setPhotos(getPhotos(entity.getId().toString()));
                    }
                    dtos.add(dto);
                }
            }
        return dtos;
    }

    /**
     * 获取卖家秀图片
     * @param showId
     * @return
     */
    private List<String> getPhotos(String showId){
        List<String> list = new ArrayList<>();
        //查询图片
        Example ePhoto = new Example(ShowPhoto.class);
        ePhoto.createCriteria().andCondition("SHOW_ID=" + showId);
        List<ShowPhoto> showPhotos = showPhotoMapper.selectByExample(ePhoto);
        for(ShowPhoto photo:showPhotos){
            list.add(photo.getPhotoUrl());
        }

        return list;
    }

    private <T> ResponseDto<PageDto<T>> getPage(StoreShowSearchDto searchDto, T dto) {
        if (null == searchDto){
            return ResponseDtoFactory.toError("searchDto不能为空");
        }
        Example e=new Example(StoreShow.class);
        Example.Criteria c = e.createCriteria();
        //排序
        if(StringUtils.isNotEmpty(searchDto.getSort())){
            e.setOrderByClause(searchDto.getSort());
        }
        //汽车品牌
        if(CollectionUtils.isNotEmpty(searchDto.getCarBrandIds())){
            c.andIn("carBrandId",searchDto.getCarBrandIds());
        }
        //汽车车系
        if(CollectionUtils.isNotEmpty(searchDto.getCarSeriesIds())){
            c.andIn("carSeriesId",searchDto.getCarSeriesIds());
        }
        //轮胎品牌
        if(CollectionUtils.isNotEmpty(searchDto.getTyreBrandIds())){
            c.andIn("tyreBrandId",searchDto.getTyreBrandIds());
        }
        //轮胎宽度
        if(StringUtils.isNotEmpty(searchDto.getTyreKd())){
            c.andEqualTo("tyreKd",searchDto.getTyreKd());
        }
        //轮胎扁平率
        if(StringUtils.isNotEmpty(searchDto.getTyreBpl())){
            c.andEqualTo("tyreBpl",searchDto.getTyreBpl());
        }
        //轮胎内径
        if(StringUtils.isNotEmpty(searchDto.getTyreNj())){
            c.andEqualTo("tyreNj",searchDto.getTyreNj());
        }
        //门店ID
        if(null != searchDto.getStoreId()){
            c.andEqualTo("storeId",searchDto.getStoreId());
        }
        //门店名称
        if(StringUtils.isNotEmpty(searchDto.getStoreName())){
            c.andLike("storeName",searchDto.getStoreName());
        }
        //经销商名称
        if(StringUtils.isNotEmpty(searchDto.getSupplierName())){
            c.andLike("supplierName",searchDto.getSupplierName());
        }
        //开始时间
        if(null != searchDto.getBeginDate()){
            c.andGreaterThanOrEqualTo("modifyTm",DateUtil.getDayBegin(searchDto.getBeginDate()));
        }
        //结束时间
        if(null != searchDto.getEndDate()){
            c.andLessThanOrEqualTo("modifyTm",DateUtil.getDayEnd(searchDto.getEndDate()));
        }
        //精选状态
        if(null != searchDto.getJxStatus()){
            c.andEqualTo("jxStatus",searchDto.getJxStatus());
        }
        //启用状态
        if(null != searchDto.getStatus()){
            c.andEqualTo("status",searchDto.getStatus());
        }
        //标签
        if(StringUtils.isNotEmpty(searchDto.getLable())){
            c.andLike("lable",searchDto.getLable());
        }
        PageRowBounds bounds = new PageRowBounds(searchDto.getOffset(), searchDto.getPageSize());
        List<StoreShow> list = storeShowMapper.selectByExampleAndRowBounds(e,bounds);
        List<T> dtoList = null;
        if(dto instanceof StoreShowListDto){
            dtoList = (List<T>) toStoreShowListDtos(list);
        }else if(dto instanceof StoreShowDetailListDto){
            dtoList = (List<T>) toStoreShowDetailListDtos(list);
        }
        return ResponseDtoFactory.toSuccess(new PageDto<>(searchDto, dtoList, bounds.getTotal().intValue()));
    }

    public ResponseDto<StoreShowDto> getShowInfo(Long id) {
        if (null == id){
            return ResponseDtoFactory.toError("id不能为空");
        }
        StoreShow storeShow = storeShowMapper.selectByPrimaryKey(id);
        if (null == storeShow){
            return ResponseDtoFactory.toError("无此记录");
        }
        if (storeShow.getStatus().equals(StatusEnum.N)){
            return ResponseDtoFactory.toError("此记录已被禁用");
        }
        StoreShowDto dto = new StoreShowDto();
        BeanUtils.copyProperties(storeShow, dto);
        //查询图片
        Example ePhoto = new Example(ShowPhoto.class);
        ePhoto.createCriteria().andCondition("SHOW_ID=" + id);
        List<ShowPhoto> showPhotos = showPhotoMapper.selectByExample(ePhoto);
        for(ShowPhoto photo:showPhotos){
            dto.getPhotos().add(photo.getPhotoUrl());
        }
        return ResponseDtoFactory.toSuccess(dto);
    }

    public ResponseDto<Long> save(StoreShowSaveDto dto) {
        if (null == dto){
            return ResponseDtoFactory.toError("storeShowSaveDto不能为空");
        }
        if(null == dto.getTyreSn() || dto.getTyreSn()==0L){
            return ResponseDtoFactory.toError("轮胎SN不能为空");
        }
        if(null == dto.getCarBrandId()){
            return ResponseDtoFactory.toError("汽车品牌ID不能为空");
        }
        if(StringUtils.isEmpty(dto.getCarBrandName())){
            return ResponseDtoFactory.toError("汽车品牌不能为空");
        }
        if(null == dto.getCarSeriesId()){
            return ResponseDtoFactory.toError("车系ID不能为空");
        }
        if(StringUtils.isEmpty(dto.getCarSeriesName())){
            return ResponseDtoFactory.toError("车系不能为空");
        }
        try {
            StoreShow show = new StoreShow();
            BeanUtils.copyProperties(dto, show);
            show.setCreateTm(DateUtil.getCurrentTimestamps());
            show.setModifyTm(DateUtil.getCurrentTimestamps());
            storeShowMapper.insertSelective(show);
            List<ShowPhoto> photolt = new ArrayList<ShowPhoto>();
            for (String url : dto.getPhotoUrlList()) {
                ShowPhoto photo = new ShowPhoto();
                photo.setShowId(show.getId());
                photo.setPhotoUrl(url);
                photolt.add(photo);
            }
            showPhotoMapper.insertList(photolt);
            return ResponseDtoFactory.toSuccess("保存成功", show.getId());
        }catch (Exception e){
            return ResponseDtoFactory.toError("保存失败");
        }
    }

    public ResponseDto<Boolean> delete(Long id) {
        if(null == id || id<0 ){
            return ResponseDtoFactory.toError("id不能为空");
        }
        StoreShow show=new StoreShow();
        show.setId(id);
        show=storeShowMapper.selectByPrimaryKey(show);
        if(null == show){
            return ResponseDtoFactory.toError("找不到id为"+id+"的卖家秀信息");
        }
        try {
            Example photo = new Example(ShowPhoto.class);
            photo.createCriteria().andCondition("SHOW_ID=" + show.getId());
            Example showDzLog = new Example(ShowDzLog.class);
            showDzLog.createCriteria().andCondition("SHOW_ID=" + show.getId());
            showDzLogMapper.deleteByExample(showDzLog);
            showPhotoMapper.deleteByExample(photo);
            storeShowMapper.deleteByPrimaryKey(show);
            return ResponseDtoFactory.toSuccess("删除成功");
        }catch (Exception e){
            return ResponseDtoFactory.toError("删除失败"+e.getMessage());
        }
    }

    public ResponseDto<Boolean> update(StoreShowEditDto dto) {
        if(null == dto || null == dto.getId()){
            return ResponseDtoFactory.toError("dto或dto.id不能为空");
        }

        if(null == dto.getPhotoUrlList() || dto.getPhotoUrlList().size()==0){
            return ResponseDtoFactory.toError("图片不能为空");
        }
        StoreShow show=new StoreShow();
        show.setId(dto.getId());
        show=storeShowMapper.selectByPrimaryKey(show);
        if(null == show){
            return ResponseDtoFactory.toError("找不到id为"+dto.getId()+"的卖家秀信息");
        }
        try {
            //更新主表
            BeanUtils.copyProperties(dto, show, new String[]{"id", "createBy", "createTm"});//dto字段为null,会覆盖show对应字段为null
            show.setModifyBy(dto.getModifyBy());
            show.setModifyTm(DateUtil.getCurrentTimestamps());
            //根据主键更新属性不为null的值
            storeShowMapper.updateByPrimaryKeySelective(show);
            //删除图片
            Example ePhoto = new Example(ShowPhoto.class);
            ePhoto.createCriteria().andCondition("SHOW_ID=" + show.getId());
            showPhotoMapper.deleteByExample(ePhoto);
            //插入图片
            List<ShowPhoto> photolt = new ArrayList<ShowPhoto>();
            for (String url : dto.getPhotoUrlList()) {
                ShowPhoto photo = new ShowPhoto();
                photo.setShowId(show.getId());
                photo.setPhotoUrl(url);
                photolt.add(photo);
            }
            showPhotoMapper.insertList(photolt);
            return ResponseDtoFactory.toSuccess("更新成功");
        }catch (Exception e){
            return ResponseDtoFactory.toError("更新失败"+e.getMessage());
        }
    }

    public ResponseDto<Boolean> setLable(Long id, String lable) {
        if(null == id || id<0 ){
            return ResponseDtoFactory.toError("卖家秀id不能为空");
        }

        StoreShow show=storeShowMapper.selectByPrimaryKey(id);
        if(null == show){
            return ResponseDtoFactory.toError("找不到id为"+id+"的卖家秀信息");
        }else{
            try{
                show.setLable(lable);
                storeShowMapper.updateByPrimaryKeySelective(show);
                return ResponseDtoFactory.toSuccess("打标成功");
            }catch (Exception e){
                return ResponseDtoFactory.toError("打标失败");
            }
        }
    }

    @Override
    public ResponseDto<Boolean> setJxStatus(Long id, String jxStatus) {
        if(null == id || id<0 ){
            return ResponseDtoFactory.toError("卖家秀id不能为空");
        }

        //精选状态，精选:JX，待选:DX
        if(null == jxStatus || !("JX".equals(jxStatus)||"DX".equals(jxStatus))){
            return ResponseDtoFactory.toError("jxStatus必须为 JX/DX 其中之一");
        }

        StoreShow show=storeShowMapper.selectByPrimaryKey(id);
        if(null == show){
            return ResponseDtoFactory.toError("找不到id为"+id+"的卖家秀信息");
        }else{
            try{
                if("JX".equals(jxStatus)){
                    show.setJxStatus(JxStatusEnum.JX);
                }else{
                    show.setJxStatus(JxStatusEnum.DX);
                }
                storeShowMapper.updateByPrimaryKeySelective(show);
                return ResponseDtoFactory.toSuccess("设定成功");
            }catch (Exception e){
                return ResponseDtoFactory.toError("设定失败");
            }
        }
    }

    @Override
    public ResponseDto<Boolean> setStatus(Long id, String status) {
        if(null == id || id<0 ){
            return ResponseDtoFactory.toError("卖家秀id不能为空");
        }

        if(null == status || !("Y".equals(status)||"N".equals(status))){
            return ResponseDtoFactory.toError("status必须为 Y/N 其中之一");
        }

        StoreShow show=storeShowMapper.selectByPrimaryKey(id);
        if(null == show){
            return ResponseDtoFactory.toError("找不到id为"+id+"的卖家秀信息");
        }else{
            try{
                if("N".equals(status)){
                    show.setStatus(StatusEnum.N);
                }else {
                    show.setStatus(StatusEnum.Y);
                }
                storeShowMapper.updateByPrimaryKeySelective(show);
                return ResponseDtoFactory.toSuccess("操作成功");
            }catch (Exception e){
                return ResponseDtoFactory.toError("操作失败");
            }
        }
    }

    public ResponseDto<Long> getUnReadLogsCnt(Long storeId) {
        if (null == storeId  || storeId<0 ){
            return ResponseDtoFactory.toError("门店ID不能为空");
        }
        try{
            Long cnt=showDzLogMapper.getUnReadLogsCnt(storeId);
            return ResponseDtoFactory.toSuccess("查询成功", cnt);
        }catch (Exception e){
            return ResponseDtoFactory.toError("查询失败");
        }
    }


    public ResponseDto<Integer> dzOpt(ShowDzLogSaveDto showDzLogSaveDto) {
        if(null == showDzLogSaveDto || showDzLogSaveDto.getId()==null || showDzLogSaveDto.getId()<0 ){
            return ResponseDtoFactory.toError("卖家秀id不能为空");
        }
        if(!(showDzLogSaveDto.getFlag().equals(1)||showDzLogSaveDto.getFlag().equals(-1))){
            return ResponseDtoFactory.toError("flag只能为1或-1");
        }
        if(null == showDzLogSaveDto.getDzChannel() || !(DzChannelEnum.APP.equals(showDzLogSaveDto.getDzChannel())||DzChannelEnum.WX.equals(showDzLogSaveDto.getDzChannel())||DzChannelEnum.QT.equals(showDzLogSaveDto.getDzChannel()))){
            return ResponseDtoFactory.toError("dzChannel必须为 APP/WX/QT 其中之一");
        }
        StoreShow show=storeShowMapper.selectByPrimaryKey(showDzLogSaveDto.getId());
        if(null == show){
            return ResponseDtoFactory.toError("找不到id为"+showDzLogSaveDto.getId()+"的卖家秀信息");
        }else{
            try {
                int effectCnt=0;
                if(showDzLogSaveDto.getFlag().equals(1)) {//点赞
                    ShowDzLog log = new ShowDzLog();
                    log.setShowId(showDzLogSaveDto.getId());
                    if(DzChannelEnum.APP.equals(showDzLogSaveDto.getDzChannel())){
                        log.setDzChannel(DzChannelEnum.APP);
                        log.setStoreIconUrl(showDzLogSaveDto.getStoreIconUrl());
                    }else if(DzChannelEnum.WX.equals(showDzLogSaveDto.getDzChannel())){
                        log.setDzChannel(DzChannelEnum.WX);
                    }else if(DzChannelEnum.QT.equals(showDzLogSaveDto.getDzChannel())){
                        log.setDzChannel(DzChannelEnum.QT);
                    }
                    log.setCreateBy(showDzLogSaveDto.getCreateBy());
                    log.setCreateByName(showDzLogSaveDto.getCreateByName());
                    log.setCreateTm(DateUtil.getCurrentTimestamps());
                    effectCnt=showDzLogMapper.insertSelective(log);
                }else{//取消点赞
                    Example e=new Example(ShowDzLog.class);
                    e.createCriteria()
                            .andCondition("SHOW_ID="+showDzLogSaveDto.getId())
                            .andCondition("CREATE_BY='"+showDzLogSaveDto.getCreateBy()+"'");
                    effectCnt=showDzLogMapper.deleteByExample(e);
                }
                if(effectCnt>0){
                    show.setDzNum(show.getDzNum() + showDzLogSaveDto.getFlag());
                    if (!DzChannelEnum.APP.equals(showDzLogSaveDto.getDzChannel())) {//如果点赞渠道非APP，认为是消费者点赞
                        show.setCusDzNum(show.getCusDzNum() + showDzLogSaveDto.getFlag());
                    }
                    storeShowMapper.updateByPrimaryKeySelective(show);
                    return ResponseDtoFactory.toSuccess("操作成功",show.getDzNum());
                }else{
                    return ResponseDtoFactory.toError("操作失败，请检查参数有效性");
                }
            }catch (Exception e){
                return ResponseDtoFactory.toError("操作失败");
            }
        }

    }

    public ResponseDto<Integer> fxOpt(Long id) {
        if(null == id || id<0 ){
            return ResponseDtoFactory.toError("卖家秀id不能为空");
        }
        StoreShow show=storeShowMapper.selectByPrimaryKey(id);
        if(null == show){
            return ResponseDtoFactory.toError("找不到id为"+id+"的卖家秀信息");
        }else{
            show.setFxNum(show.getFxNum()+1);
            try {
                storeShowMapper.updateByPrimaryKeySelective(show);
                return ResponseDtoFactory.toSuccess("更新分享数成功",show.getFxNum());
            }catch (Exception e){
                return ResponseDtoFactory.toError("更新分享数失败");
            }
        }
    }

    public ResponseDto<List<ShowDzLogDto>> getDzLogs(ShowDzLogSearchDto showDzLogSearchDto) {
        if(null == showDzLogSearchDto){
            return ResponseDtoFactory.toError("showDzLogSearchDto不能为空");
        }
        if(null == showDzLogSearchDto.getStoreId() || showDzLogSearchDto.getStoreId()<0){
            return ResponseDtoFactory.toError("门店id不能为空");
        }
        try {
            List<ShowDzLogDto> dataList = new ArrayList<ShowDzLogDto>();
            if (showDzLogSearchDto.getLastDzLogId() == -1) {//请求第一页数据时，返回所有未读数据
                dataList = showDzLogMapper.getUnReadLogs(showDzLogSearchDto.getStoreId());
                showDzLogMapper.updateUnReadLogsAll(showDzLogSearchDto.getStoreId());//更新消息状态为已读（全部更新）
            } else {
                dataList = showDzLogMapper.getReadedLogs(showDzLogSearchDto.getStoreId(), showDzLogSearchDto.getLastDzLogId(), showDzLogSearchDto.getPageSize());
            }
            return ResponseDtoFactory.toSuccess("查询成功", dataList);
        }catch (Exception e){
            return ResponseDtoFactory.toError("查询失败");
        }
    }

    @Override
    public Map<String, Long> statisticStoreAndSpec() {
        Map<String, Long> map = storeShowMapper.statisticStoreAndSpec();
        return map;
    }
}
