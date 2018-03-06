package club.pinea.dao;

import club.pinea.model.RecycleBin;

public interface RecycleBinMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecycleBin record);

    int insertSelective(RecycleBin record);

    RecycleBin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecycleBin record);

    int updateByPrimaryKey(RecycleBin record);
}