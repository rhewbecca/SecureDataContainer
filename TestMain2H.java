import java.util.*;


public class TestMain2H {

    public static void main(String[] args) {
        HashSecureDataContainer<String> Documents=new HashSecureDataContainer<>();

        //creates users
        Documents.createUser("alby", "1234");
        Documents.createUser("benny", "4567");
        Documents.createUser("chris", "8900");
        Documents.createUser("dave", "1357");
        try {
            Documents.createUser(null,null);
        }catch(Exception e) {System.out.println(e);}

        System.out.print("Remove user dave: ");
        try { //remove user
            Documents.RemoveUser("dave", "11");
        }catch(Exception e)	{System.out.println(e);}


        //inserts data
        try {
            Documents.put("alby","1234","Passport");} catch(Exception e) {System.out.println(e);}
        try {
            Documents.put("alby","1234","DriverLicense");} catch(Exception e) {System.out.println(e);}
        try {
            Documents.put("alby","1234","Visa");} catch(Exception e) {System.out.println(e);}
        try {
            Documents.put("benny","4567","Passport");} catch(Exception e) {System.out.println(e);}
        try {
            Documents.put("chris","8900",null);} catch(Exception e) {System.out.println(e);}

        try {//get size
            System.out.println("Data size of alby: " + Documents.getSize("alby", "1234"));
        }catch(Exception e) {System.out.println(e);}

        try {//get data copy
            System.out.println("Data copy:");
            System.out.println("-"+Documents.get("alby", "1234","DrivLic"));
        }catch(Exception e) {System.out.println(e);}


        try {//removes data
            System.out.println("Removed data from alby: "+Documents.remove("alby", "1234", "DriverLicense"));
        } catch (Exception e) {	System.out.println(e);	}

        try{//creates data copy in the collection
            Documents.copy(null, "1234", "Matricola");
        }catch(Exception e){System.out.println(e);}

        try {//authorized users of benny
            Documents.authorizedUser("ben", "4567", "alby");
            Documents.share("alby", "1234", "benny", "Visa");
            System.out.println("Shared data: "+Documents.get("benny", "4567","Visa"));
        }catch(Exception e) {System.out.println(e);}

        try {//iterator
            Iterator<String> it=Documents.getIterator("alby", "1234");
            while(it.hasNext()) {
                System.out.println(it.next());
            }

        }catch (Exception e) {System.out.println(e);}


    }
}