package top.huzhurong.monitor.mybatis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.huzhurong.monitor.mybatis.bean.PerformData;
import top.huzhurong.monitor.mybatis.dao.PerformDataDaoImpl;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonitorMybatisApplicationTests {

    @Resource
    private PerformDataDaoImpl performDataDaoImpl;

    @Before
    public void setUp() throws Exception {
        String resource = "config.xml";
    }

    @Test
    public void testInsert() {
        PerformData performData = new PerformData();
        performData.setAddTime(new Date());
        performData.setContext("11");
        performData.setMetaId("123");
        performData.setType("ggg");
        performDataDaoImpl.addPerformData(performData);
    }


    @Test
    public void testDelete() {
        performDataDaoImpl.deletePerformDataByKey(7);
    }

    @Test
    public void testSelect() {
        performDataDaoImpl.selectPerformDataByKey(6);
    }
}
