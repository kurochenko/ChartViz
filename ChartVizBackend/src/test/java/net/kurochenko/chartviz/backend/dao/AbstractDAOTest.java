package net.kurochenko.chartviz.backend.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/test-datasource-config.xml", "classpath:/META-INF/spring-config.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class AbstractDAOTest {



}
