package com.dist.transaction.service;

import com.dist.model.transaction.ams.User;
import com.dist.transaction.repository.ams.UserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yujx
 * @date 2019/04/12 10:57
 */
@Service
public class DoubleUserService {

    @Autowired
    private UserResp amsUserResp;

    @Autowired
    private com.dist.transaction.repository.ars.UserResp arsUserResp;


    @Transactional(value = "txManager", rollbackFor = Exception.class)
    public void func() {

        User amsUser = new User();
        amsUser.setId(1111);
        amsUser.setLastName("xxx");

        amsUserResp.save(amsUser);

        com.dist.model.transaction.ars.User arsUser = new com.dist.model.transaction.ars.User();
        arsUser.setLastName("yyy");
        arsUser.setId(2222);
        arsUserResp.save(arsUser);

//        int a = 1 / 0;
    }

}
