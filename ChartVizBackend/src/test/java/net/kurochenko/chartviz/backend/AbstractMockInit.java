package net.kurochenko.chartviz.backend;

import org.junit.Before;
import org.junit.Ignore;
import org.mockito.MockitoAnnotations;

/**
 * @author Andrej Kuročenko <andrej@kurochenko.net>
 */
@Ignore
public abstract class AbstractMockInit {

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

}
