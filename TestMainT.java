import java.util.*;


public class TestMainT {

    public static void main(String[] args) {
        TreeSecureDataContainer<String> Documents=new TreeSecureDataContainer<>();

        //creates users
        Documents.createUser("alby", "1234");
        Documents.createUser("benny", "4567");
        Documents.createUser("chris", "8900");
        Documents.createUser("dave", "112233");
        try {
            Documents.createUser(null,null);
        }catch(Exception e) {System.out.println(e);}

        try { //remove user
            Documents.RemoveUser("dave", "112233");
        }catch(Exception e)	{System.out.println(e);}


        try {//inserts data
            Documents.put("alby","1234","Passport");
            Documents.put("alby","1234","DriverLicense");
            Documents.put("alby","1234","Visa");
            Documents.put("benny","4567","Passport");
            Documents.put("chris","8900","DriverLicense");
        }catch(Exception e) {System.out.println(e);}

        try {//get size
            System.out.println("Data size of alby: " + Documents.getSize("alby", "1234"));
        }catch(Exception e) {System.out.println(e);}

        try {//get data copy
            System.out.println("Data copy:");
            System.out.println("-"+Documents.get("alby", "1234","Visa"));
            System.out.println("-"+Documents.get("benny", "4567","Passport"));
        }catch(Exception e) {System.out.println(e);}


        try {//removes data
            System.out.println("Removed data from alby: "+Documents.remove("alby", "1234", "DriverLicense"));
        } catch (Exception e) {	System.out.println(e);	}

        try{//creates data copy in the collection
            Documents.copy("alby", "1234", "Visa");
        }catch(Exception e){System.out.println(e);}

        try {//authorized users of benny
            Documents.authorizedUser("benny", "4567", "alby");
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
