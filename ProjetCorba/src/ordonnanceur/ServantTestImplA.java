import org.omg.CORBA.Any;

import java.util.ArrayList;

public class ServantTestImplA extends APOA{

    @Override
    public void a1(Any params, String serverRef, String objectName) {
        System.out.println("a1");
        System.out.println(serverRef);
    }

    @Override
    public void a2(Any params, String serverRef, String objectName) {
        System.out.println("a2");
        System.out.println(serverRef);
    }
}
