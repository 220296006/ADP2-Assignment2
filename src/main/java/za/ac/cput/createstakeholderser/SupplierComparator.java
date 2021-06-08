package za.ac.cput.createstakeholderser;

/**
 * 
 * This is my CustomerComparator.java file
 * @author Thabiso Matsaba (220296006) 
 * 2 June 2021
 */
import java.util.Comparator;

public class SupplierComparator implements Comparator<Supplier>{

    @Override
    public int compare(Supplier s1, Supplier s2) {
        
        return s1.getName().compareToIgnoreCase(s2.getName());
    }

   
    
}
