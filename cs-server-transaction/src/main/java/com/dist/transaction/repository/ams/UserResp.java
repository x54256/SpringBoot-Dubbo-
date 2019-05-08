package com.dist.transaction.repository.ams;

import com.dist.model.transaction.ams.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yujx
 * @date 2019/04/12 10:56
 */
@Repository("amsUserResp")
public interface UserResp extends JpaRepository<User, Integer> {
}
