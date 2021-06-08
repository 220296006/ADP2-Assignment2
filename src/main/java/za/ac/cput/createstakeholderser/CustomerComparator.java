package za.ac.cput.createstakeholderser;

import java.util.Comparator;
/**
 * 
 * This is my CustomerComparator.java file
 * @author Thabiso Matsaba (220296006) 
 * 2 June 2021
 */

//This method sorts the Customer Arraylist
public class CustomerComparator implements Comparator<Customer>{

   
    @Override
    public int compare(Customer c1,Customer c2) {
       
        return c1.getStHolderId().compareToIgnoreCase(c2.getStHolderId());
       
    }
}
