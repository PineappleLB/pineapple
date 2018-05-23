package club.pinea.dao;

import club.pinea.model.SharePackage;

public interface SharePackageMapper {
    int deleteByPrimaryKey(String id);

    int insert(SharePackage record);

    int insertSelective(SharePackage record);

    SharePackage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SharePackage record);

    int updateByPrimaryKey(SharePackage record);
}