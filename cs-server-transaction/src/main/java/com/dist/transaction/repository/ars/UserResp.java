package com.dist.transaction.repository.ars;

import com.dist.model.transaction.ars.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yujx
 * @date 2019/04/12 10:56
 */
@Repository("arsUserResp")
public interface UserResp extends JpaRepository<User, Integer> {
}
