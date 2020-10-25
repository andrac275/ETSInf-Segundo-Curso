package practica2;
import java.util.*;

/**
 * class WrapperClassesUse.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class WrapperClassesUse {        
    public static void main(String[] args) {            
        // Assignment of wrapper variables to elementary types 
        int i = new Integer(123456);		
        // TO COMPLETE ...
        double d = new Double(29.5);
        char c = new Character('a');
            
        // Writing elementary variables
        System.out.println("int i = " + i);
        // TO COMPLETE ...
        System.out.println("double d = " + d);
        System.out.println("character c = " + c);
        
        // Assignment of elementary values to wrapper variables
        Integer eI = 123456; 
        // TO COMPLETE ...
        Double eD = 29.5;
        Character eC = 'a';
        
        // Writing wrapper variables
        System.out.println("Integer I = " + eI);
        // TO COMPLETE ...
        System.out.println("Double D = " + eD);
        System.out.println("Character C = " + eC);
    }    
}