package com.dist.concurrent.repository;

import com.dist.model.concurrent.entity.ModelCalculationStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author yujx
 * @date 2019/04/04 10:59
 */
public interface ModelCalculationStatisticsRepository extends JpaRepository<ModelCalculationStatistics, Long>, JpaSpecificationExecutor<ModelCalculationStatistics> {

    @Query(value = "SELECT * FROM ARS_MODEL_CALC_STATISTICS M WHERE  M.REVIEWPOINTID = ?2 AND  M.REVIEWTASKID = ?1", nativeQuery = true)
    ModelCalculationStatistics searchByReviewTaskIdAndReviewPointId(Long reviewTaskId, Long reviewPointId);


    @Query(value = "update ModelCalculationStatistics mcs set mcs.conflictNumber = ?1,mcs.version = ?2 where mcs.id=?3 and mcs.version < ?2")
    @Modifying
    Integer updateOne(Integer a, Integer b, Long c);


//    @Modifying
    @Query(value = "INSERT INTO ars_model_calc_statistics\n" +
            "(conflictNumber, resultStatus, reviewPointId, reviewTaskId, version, id) SELECT\n" +
            "                                                                           1,\n" +
            "                                                                           1,\n" +
            "                                                                           1,\n" +
            "                                                                           1,\n" +
            "                                                                           0,\n" +
            "                                                                           SEQ_ARS_HIBERNATE.nextval\n" +
            "                                                                         FROM DUAL\n" +
            "                                                                         WHERE NOT exists\n" +
            "                                                                         (SELECT 1\n" +
            "                                                                          FROM ARS_MODEL_CALC_STATISTICS mcs\n" +
            "                                                                          WHERE mcs.REVIEWTASKID = 1 AND\n" +
            "                                                                                mcs.REVIEWPOINTID = 1)", nativeQuery = true)
    int saveOne();

}
