package Util;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public final class MyUtil {
    private static MyUtil instance = new MyUtil();
    
    private MyUtil() {}
    
    public String testNullAndSet(String val) {
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    public static MyUtil getInstance() {
        return instance;
    }
    
    
    public <T> HashSet<T> ArrayToHashset(String[] gr) {
        @SuppressWarnings("unchecked")
        Collection<? extends T> asList = (Collection<? extends T>) Arrays.asList(gr);
        return new HashSet<T>(asList);
    }

    
    public boolean showDiag(String msg, boolean val) {
        javax.swing.JOptionPane.showMessageDialog(null,msg);
        return val;
    }


}