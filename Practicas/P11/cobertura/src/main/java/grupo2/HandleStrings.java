package grupo2;

public class HandleStrings {
    public String extractMiddle(String s){
        int indx1 = s.indexOf(':');
        int indx2 = s.indexOf(':', indx1 +1);
        return s.substring(indx1+1,indx2);
    }
}
