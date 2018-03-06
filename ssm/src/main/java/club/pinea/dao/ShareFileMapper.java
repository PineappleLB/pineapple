package club.pinea.dao;

import club.pinea.model.ShareFile;

public interface ShareFileMapper {
    int insert(ShareFile record);

    int insertSelective(ShareFile record);
}