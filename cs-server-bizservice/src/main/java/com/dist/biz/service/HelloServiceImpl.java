package com.dist.biz.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dist.api.service.biz.HelloService;
import com.dist.base.utils.dozer.IGenerator;
import com.dist.biz.domain.HelloDmn;
import com.dist.model.biz.dto.UserDTO;
import com.dist.model.biz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yujx
 * @date 2019/02/14 09:37
 */
@Service(version = "1.0.0", timeout = 30000)
public class HelloServiceImpl implements HelloService {

    @Autowired
    private HelloDmn helloDmn;

    @Autowired
    private IGenerator dozer;


    @Override
    public List<UserDTO> findAll() {

//        List<User> userList = helloDmn.findAll();
        List<User> userList = new ArrayList<>();

        User user = new User();

        user.setId(1);
        user.setEmail("xx@qq.com");
        user.setLastName("x5456");

        userList.add(user);

        return dozer.convert(userList, UserDTO.class);
    }

}
