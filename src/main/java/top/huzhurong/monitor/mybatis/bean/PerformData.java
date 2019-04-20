package top.huzhurong.monitor.mybatis.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author chenshun
 * @since 2018-08-06 09:53:58
 */
@Getter
@Setter
public class PerformData {
    /**
     * id
     */
    private Long id;
    /**
     * 内容
     */
    private String context;
    /**
     * ecs/rds
     */
    private String type;
    /**
     * 实例
     */
    private String metaId;
    /**
     * 时间
     */
    private Date addTime;
}