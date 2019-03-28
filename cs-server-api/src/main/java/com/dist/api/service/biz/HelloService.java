package com.dist.api.service.biz;

import com.dist.model.biz.dto.UserDTO;

import java.util.List;

/**
 * @author yujx
 * @date 2019/02/14 09:24
 */
public interface HelloService {

    List<UserDTO> findAll();
}
