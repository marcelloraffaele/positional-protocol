package it.rmarcello.protocol.parsing;

/**
 *
 * @author rmarcello
 */
public class Padding {
    
    public static String padLeft(String s, int size) {
        String res = "";
        if(s!=null)
            res+=s;
        if( res.length()<size ) {
            for(int i=res.length();i<size;i++)
                res+=" ";
        }
        return res;
    }
    
    public static String padRight(String s, int size) {
        String res = "";
        if(s!=null)
            res+=s;
        if( res.length()<size ) {
            for(int i=res.length();i<size;i++)
                res=" "+res;
        }
        return res;
    }
    
    public static String pad(Integer x, int size) {
        String res = "";
        if(x!=null)
            res+=x;
        if( res.length()<size ) {
            for(int i=res.length();i<size;i++)
                res="0"+res;
        }
        return res;
    }
}
