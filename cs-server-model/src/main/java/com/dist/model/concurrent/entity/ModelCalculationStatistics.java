package com.dist.model.concurrent.entity;

import javax.persistence.*;

/**
 * 模型计算结果统计表
 * 设置reviewTaskId和reviewPointId唯一索引
 *
 * @author yujx
 * @date 2019/03/04 13:27
 */
@Entity
@Table(name = "ars_model_calc_statistics", uniqueConstraints = @UniqueConstraint(columnNames = {"REVIEWTASKID", "REVIEWPOINTID"}))
@SequenceGenerator(name = "ID_SEQ", sequenceName = "SEQ_ARS_HIBERNATE", allocationSize = 1)
public class ModelCalculationStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
    private Long id;

    // 计算结果冲突数量
    private Integer conflictNumber;

    // 检测状态  @See StatusEnum.DetectionStatus
    @Column(length = 1)
    private Integer resultStatus;

    // 审查任务id   @See ReviewTask.id
    private Long reviewTaskId;

    // 审查要点id   @See ReviewPoint.id
    private Long reviewPointId;

    // 乐观锁的版本
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getConflictNumber() {
        return conflictNumber;
    }

    public void setConflictNumber(Integer conflictNumber) {
        this.conflictNumber = conflictNumber;
    }

    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Long getReviewTaskId() {
        return reviewTaskId;
    }

    public void setReviewTaskId(Long reviewTaskId) {
        this.reviewTaskId = reviewTaskId;
    }

    public Long getReviewPointId() {
        return reviewPointId;
    }

    public void setReviewPointId(Long reviewPointId) {
        this.reviewPointId = reviewPointId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ModelCalculationStatistics{" +
                "id=" + id +
                ", conflictNumber=" + conflictNumber +
                ", resultStatus=" + resultStatus +
                ", reviewTaskId=" + reviewTaskId +
                ", reviewPointId=" + reviewPointId +
                ", version=" + version +
                '}';
    }
}
