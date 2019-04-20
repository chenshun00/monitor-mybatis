package top.huzhurong.monitor.mybatis.dao;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import top.huzhurong.monitor.mybatis.bean.PerformData;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenshun
 * @since 2018-08-06 09:56:59
 */
@Repository
public class PerformDataDaoImpl {

    @Resource
    @Getter
    @Setter
    private SqlSessionTemplate sqlSessionTemplate;

    public List<PerformData> selectPerformDataByKey(Integer key) {
        return getSqlSessionTemplate().selectList("PerformData.selectPerformDataByKey", key);
    }

    public Integer addPerformData(PerformData performData) {
        return getSqlSessionTemplate().insert("PerformData.insertPerformData", performData);
    }


    public Integer updatePerformDataByKey(PerformData performData) {
        return this.getSqlSessionTemplate().update("PerformData.updatePerformDataByKey", performData);
    }


    public Integer deletePerformDataByKey(Integer key) {
        return getSqlSessionTemplate().delete("PerformData.deletePerformDataByKey", key);
    }

}
