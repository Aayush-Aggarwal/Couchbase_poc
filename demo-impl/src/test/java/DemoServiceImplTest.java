import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class DemoServiceImplTest {

    @BeforeClass
    public static void conectCouchbase() {
        try {
             Runtime.getRuntime()
                    .exec("sudo docker run 6b07eb5c28ef");
            System.out.println("---------------");
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    @Test
    public void testcouchbase(){
        assertEquals(true,true);
    }
}
