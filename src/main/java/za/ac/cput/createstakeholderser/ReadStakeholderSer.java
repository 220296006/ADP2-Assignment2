package za.ac.cput.createstakeholderser;

/**
 * This is my ReadStakeholderSer.java file
 * @author Thabiso Matsaba (220296006) 
 * 2 June 2021
 */
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class ReadStakeholderSer extends Stakeholder implements Serializable {

    private ArrayList<Customer> customers;
    private ArrayList<Supplier> suppliers;

    public ReadStakeholderSer() {
        customers = new ArrayList<>();
        suppliers = new ArrayList<>();
    }

    //Step 1: Read the object values in the stakeholderser.file
    public void ReadStakeholderSer() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream fs = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
            Customer customer1 = (Customer) fs.readObject();
            Supplier supplier1 = (Supplier) fs.readObject();
            Customer customer2 = (Customer) fs.readObject();
            Supplier supplier2 = (Supplier) fs.readObject();
            Supplier supplier3 = (Supplier) fs.readObject();
            Customer customer3 = (Customer) fs.readObject();
            Customer customer4 = (Customer) fs.readObject();
            Supplier supplier4 = (Supplier) fs.readObject();
            Supplier supplier5 = (Supplier) fs.readObject();
            Customer customer5 = (Customer) fs.readObject();
            Customer customer6 = (Customer) fs.readObject();

            System.out.println("Customer:" + customer1.toString());
            System.out.println("Supplier:" + supplier1.toString());
            System.out.println("Customer:" + customer2.toString());
            System.out.println("Supplier:" + supplier2.toString());
            System.out.println("Supplier:" + supplier3.toString());
            System.out.println("Customer:" + customer3.toString());
            System.out.println("Customer:" + customer4.toString());
            System.out.println("Supplier:" + supplier4.toString());
            System.out.println("Customer:" + supplier5.toString());
            System.out.println("Customer:" + customer5.toString());
            System.out.println("Customer:" + customer6.toString());

            customers.add(customer1);
            customers.add(customer2);
            customers.add(customer3);
            customers.add(customer4);
            customers.add(customer5);
            customers.add(customer6);

            suppliers.add(supplier1);
            suppliers.add(supplier2);
            suppliers.add(supplier3);
            suppliers.add(supplier4);
            suppliers.add(supplier5);

            System.out.println("*** ser fs opened for reading ***");
        } //end try
        catch (IOException ioe) {
            System.out.println("error opening ser fs: " + ioe.getMessage());
            System.exit(1);
        }//end catch
    }

    // Store the customer objects in arraylist
    public void storeCustomerObjects() throws FileNotFoundException, IOException, ClassNotFoundException, ClassCastException {
        //Sorts the Customer object Arraylist
        Collections.sort(customers, new CustomerComparator());
        System.out.println(customers);

    }

    // Stores the supplier objects in arraylist
    public void storeSupplierObjects() throws FileNotFoundException, IOException, ClassNotFoundException, ClassCastException {
        //Sorts the Suplier object ArrayList
        Collections.sort(suppliers, new SupplierComparator());
        System.out.println(suppliers);

    }
    // Calculates the age of the customer objects
    public int getAge(String date_of_birth) throws Exception {
        String s = date_of_birth;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(s);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        LocalDate l1 = LocalDate.of(year, month, date);
        LocalDate now1 = LocalDate.now();
        Period diff1 = Period.between(l1, now1);

        return diff1.getYears();
    }

    //writes the customer objects into a text file
       public void writeCustomerObjects() throws Exception, ClassNotFoundException {

        FileWriter fw1 = new FileWriter("customerOutFile.txt");
        int count_can_rent = 0;
        int count_cannot_rent = 0;
        try (BufferedWriter bw = new BufferedWriter(fw1)) {
            bw.write("============================ CUSTOMERS =================================================");
            bw.write("\nId    " + "Name      " + "Surname          " + "Date Of Birth          " + "Age      ");
            bw.write("\n=========================================================================================");
            for (Customer customer : customers) {
                bw.write("\n" + customer.getStHolderId() + "  " + customer.getFirstName() + "        " + customer.getSurName() + "        " + customer.getDateOfBirth() + "       "
                        + getAge(customer.getDateOfBirth()) + "      ");
                if (customer.getCanRent() == true) {
                    count_can_rent++;
                } else {
                    count_cannot_rent++;
                }// end else
            }// end for
            bw.write("\n");
            bw.write("\nNumber of customers who can rent: " + count_can_rent);
            bw.write("\nNumber of customers who cannot rent: " + count_cannot_rent);
        }//end try
    }
    //writes the supplier objects into a text file
    public void writeSupplierObjects() throws IOException, ClassNotFoundException {

        FileWriter fw2 = new FileWriter("supplierOutFile.txt");
        try (BufferedWriter bw1 = new BufferedWriter(fw2)) {
            bw1.write("============================ SUPPLIERS =================================================");
            bw1.write("\nId    " + "Name       " +                                                   "Prod Type            "+                    "Description        ");
            bw1.write("\n=========================================================================================");
            for (Supplier supplier: suppliers) {
                bw1.write("\n" + supplier.getStHolderId() +"  "+ supplier.getName() + "          " + supplier.getProductType() + "        " + supplier.getProductDescription()  +"     ");
            }//end for
        }// end try
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, ClassCastException, Exception {
        ReadStakeholderSer obj = new ReadStakeholderSer();
        obj.ReadStakeholderSer();
        obj.storeCustomerObjects();
        obj.storeSupplierObjects();
        obj.writeCustomerObjects();
        obj.writeSupplierObjects();
    }//rnd main
}
