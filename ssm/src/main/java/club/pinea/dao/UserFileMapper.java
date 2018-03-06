package club.pinea.dao;

import club.pinea.model.UserFile;

public interface UserFileMapper {
    int insert(UserFile record);

    int insertSelective(UserFile record);
}