package org.x.study.shirodemo.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    String getPassword(String username);
    String getRole(String username);
}
