package com.dist.concurrent.service;

import com.dist.base.utils.JsonUtils;
import com.dist.concurrent.repository.ModelCalculationStatisticsRepository;
import com.dist.model.concurrent.entity.ModelCalculationStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author yujx
 * @date 2019/04/04 12:34
 */
@Slf4j
@Service
public class ModelCalculationStatisticsServiceImpl {

    @Autowired
    private ModelCalculationStatisticsRepository modelCalculationStatisticsRepository;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Async
    @Transactional
    public void save1() {
//
//        ModelCalculationStatistics modelCalculationStatistics1 = modelCalculationStatisticsRepository.searchByReviewTaskIdAndReviewPointId(1L, 1L);
//
//        if (modelCalculationStatistics1 == null) {

        ModelCalculationStatistics modelCalculationStatistics = new ModelCalculationStatistics();

        modelCalculationStatistics.setReviewPointId(1L);
        modelCalculationStatistics.setReviewTaskId(1L);
        modelCalculationStatistics.setConflictNumber(1);
        modelCalculationStatistics.setVersion(1);

        try {
            modelCalculationStatisticsRepository.save(modelCalculationStatistics);
        } catch (DataIntegrityViolationException e) {
            System.out.println(111);
//            e.printStackTrace();
        }


        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        } else {
//
//            // 成功 返回1，失败返回0
//            Integer o = modelCalculationStatisticsRepository.updateOne(modelCalculationStatistics1.getConflictNumber() + 1, modelCalculationStatistics1.getVersion() + 1, modelCalculationStatistics1.getId());
//            System.out.println("o = " + o);
//        }
    }

    @Async
    @Transactional
    public void save11() {
        ModelCalculationStatistics modelCalculationStatistics = new ModelCalculationStatistics();

        modelCalculationStatistics.setReviewPointId(1L);
        modelCalculationStatistics.setReviewTaskId(1L);
        modelCalculationStatistics.setConflictNumber(1);
        modelCalculationStatistics.setVersion(1);

        try {
            modelCalculationStatisticsRepository.save(modelCalculationStatistics);
        } catch (DataIntegrityViolationException e) {
            System.out.println(222);
        }

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(333);
    }


    @Async
    @Transactional
    public void save2() {
        ModelCalculationStatistics modelCalculationStatistics1 = modelCalculationStatisticsRepository.searchByReviewTaskIdAndReviewPointId(1L, 1L);

        log.info(JsonUtils.toString(modelCalculationStatistics1));
        Integer o = modelCalculationStatisticsRepository.updateOne(modelCalculationStatistics1.getConflictNumber() + 1, modelCalculationStatistics1.getVersion() + 1, modelCalculationStatistics1.getId());


        log.info(String.valueOf(o) + "save2()");

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.error("save2完成");
    }


    @Async
    @Transactional
    public void save3() {
        ModelCalculationStatistics modelCalculationStatistics1 = modelCalculationStatisticsRepository.searchByReviewTaskIdAndReviewPointId(1L, 1L);

        log.info(JsonUtils.toString(modelCalculationStatistics1));

        Integer o = modelCalculationStatisticsRepository.updateOne(modelCalculationStatistics1.getConflictNumber() + 1, modelCalculationStatistics1.getVersion() + 1, modelCalculationStatistics1.getId());

        log.info(String.valueOf(o) + "save3()");

        log.error("save3完成");
    }


    // 一级缓存，理想很美好，现实很骨感（又不能关闭，只能采用jdbcTemplate） @Query也不行
    @Async
    @Transactional
    public Future<Integer> save4() {

        while (true) {

            RowMapper<ModelCalculationStatistics> rowMapper = new BeanPropertyRowMapper<>(ModelCalculationStatistics.class);

//            ModelCalculationStatistics modelCalculationStatistics1 = modelCalculationStatisticsRepository.searchByReviewTaskIdAndReviewPointId(1L, 1L);

            List<ModelCalculationStatistics> query = jdbcTemplate.query("SELECT * FROM ARS_MODEL_CALC_STATISTICS M WHERE  M.REVIEWPOINTID = 1 AND  M.REVIEWTASKID = 1", rowMapper);
            ModelCalculationStatistics modelCalculationStatistics1 = query.get(0);

            log.warn(modelCalculationStatistics1.getVersion() + "");

            // 成功 返回1，失败返回0
            Integer o = modelCalculationStatisticsRepository.updateOne(modelCalculationStatistics1.getConflictNumber() + 1, modelCalculationStatistics1.getVersion() + 1, modelCalculationStatistics1.getId());
            log.error("o = " + o);

            if (o == 1) {
                break;
            }
        }

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("save4完成，睡了1s");

        return new AsyncResult<>(1);

    }


    @Async
    @Transactional
    public Future<Integer> save5() {

        while (true) {

            RowMapper<ModelCalculationStatistics> rowMapper = new BeanPropertyRowMapper<>(ModelCalculationStatistics.class);

//            ModelCalculationStatistics modelCalculationStatistics1 = modelCalculationStatisticsRepository.searchByReviewTaskIdAndReviewPointId(1L, 1L);

            List<ModelCalculationStatistics> query = jdbcTemplate.query("SELECT * FROM ARS_MODEL_CALC_STATISTICS M WHERE  M.REVIEWPOINTID = 1 AND  M.REVIEWTASKID = 1", rowMapper);
            ModelCalculationStatistics modelCalculationStatistics1 = query.get(0);

            log.warn(modelCalculationStatistics1.getVersion() + "");

            // 成功 返回1，失败返回0
            Integer o = modelCalculationStatisticsRepository.updateOne(modelCalculationStatistics1.getConflictNumber() + 1, modelCalculationStatistics1.getVersion() + 1, modelCalculationStatistics1.getId());
            log.error("o = " + o);

            if (o == 1) {
                break;
            }
        }

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("save5完成，睡了10s");

        return new AsyncResult<>(1);
    }

    /*

    不加锁（synchronized）的情况下：121ms

    [SimpleAsyncTaskExecutor-1] o = 1
    [SimpleAsyncTaskExecutor-2] o = 0
    [SimpleAsyncTaskExecutor-4] o = 0
    [SimpleAsyncTaskExecutor-3] o = 0

    [SimpleAsyncTaskExecutor-4] o = 1
    [SimpleAsyncTaskExecutor-2] o = 0
    [SimpleAsyncTaskExecutor-3] o = 0

    [SimpleAsyncTaskExecutor-3] o = 1
    [SimpleAsyncTaskExecutor-2] o = 0

    [SimpleAsyncTaskExecutor-2] o = 1


    加锁（synchronized）的情况下：116ms

    [SimpleAsyncTaskExecutor-4] o = 1
    [SimpleAsyncTaskExecutor-3] o = 0
    [SimpleAsyncTaskExecutor-3] o = 1
    [SimpleAsyncTaskExecutor-2] o = 1
    [SimpleAsyncTaskExecutor-1] o = 1


    由上面可知，不加锁的情况下需要访问数据库次数更多（与数据库交互的IO操作），这些IO操作导致的耗时比加锁的耗时更多，所以最终考虑自旋锁（此处的自旋锁的锁变量是数据库中的version字段）+synchronization

     */

    @Async
    public Future<Integer> func() {

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(111);

        return new AsyncResult<>(1);
    }


}
