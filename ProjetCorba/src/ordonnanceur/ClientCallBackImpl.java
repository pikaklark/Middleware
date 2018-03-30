import org.omg.CORBA.Any;
import org.omg.CORBA.TCKind;

public class ClientCallBackImpl extends ClientCallBackPOA{
    @Override
    public void callback(int id, Any res) {
        System.out.println("id :" + id);

        switch (res.type().kind().value()){
            case TCKind._tk_long:
                System.out.println(res.extract_long());
                break;
            case TCKind._tk_string:
                System.out.println(res.extract_string());
                break;
            case TCKind._tk_null:
                System.out.println("Aucun servant dispo");
                break;
            default:
                System.out.println("Type inconnu");
                break;
        }
    }
}
