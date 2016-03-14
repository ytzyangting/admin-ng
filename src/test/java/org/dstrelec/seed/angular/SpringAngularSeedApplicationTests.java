package org.dstrelec.seed.angular;

import org.dstrelec.adminseed.AdminSeedApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AdminSeedApplication.class)
@WebAppConfiguration
public class SpringAngularSeedApplicationTests {

	@Test
	public void contextLoads() {
	}

}
