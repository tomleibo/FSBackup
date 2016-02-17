package tests;

import core.API;
import org.junit.Test;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public class SystemTests {
    @Test
    public void test1() {
        API.sync().from("C:\\dev\\batches").to("C:\\dev\\code").go();
    }


}
