import com.taotao.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 测试导入solr
 */
public class TestSolr {

    private ApplicationContext act;

    private ItemService itemService;

    @Before
    public void init() {
        act = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
        itemService = act.getBean(ItemService.class);
    }

    @Test
    public void importData() throws InvocationTargetException, NoSuchMethodException, IOException, SolrServerException, IllegalAccessException {
        itemService.inportSolrData();
    }

}
