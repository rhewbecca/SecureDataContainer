import java.util.Iterator;
import java.lang.String;

//TE: <Id, Password, {E1...En}, {Usr1...Usrm}> con Usr da 1 a m che appartiene a String
//      dove Id e Password sono di tipo String, {E1...En} è una linked list con elementi di tipo E,
//      {Usr1...Usrm} è una linked list con elementi di tipo String

public interface UserInterface<E> {

    public String getId();
    /* Return the user's id.
        requires: -
        modifies: -
        throws: -
        effects: return the user's id
    */

    public boolean VerifyPsw(String psw) throws IncorrectPswException;
    /* Checks if the password is right.
        requires: password
        modifies: -
        throws: if psw==null throws NullPointerException
                if psw!=passw throws IncorrectPswException
        effects: returns true if the password is right, otherwise returns false
    */

    public int getSize(String passw) throws IncorrectPswException;
    /* Returns the number of data elements owned by this user.
        requires: password
        modifies: -
        throws: if the password is wrong throws IncorrectPswException
        effects: returns the number of data elements
    */

    public boolean Put(String passw, E element) throws IncorrectPswException;
    /* If the password is right inserts the element in the user's collection
        requires: password and element
        modifies: this
        throws: if the password is wrong throws IncorrectPswException
                if the element==null throws NullPointerException
        effects: inserts the element in the user's collection
    */

    public E Get(String passw, E element) throws IncorrectPswException, ElementNotFoundException;
    /* If the password is right it gets a copy of the element in the collection
        requires: password and element
        modifies: this
        throws: if the password is wrong throws IncorrectPswException
                if the element==null throws NullPointerException
                if the element is not present in the user collection throws ElementNotFoundException
        effects: it returns a copy of the selected element
    */

    public E Remove(String passw, E element) throws IncorrectPswException, ElementNotFoundException;
    /* If the password is right it removes the element from the collection
        requires: password and element
        modifies: this
        throws: if the password is wrong throws IncorrectPswException
                if the element is not present throws ElementNotFoundException
        effects: removes the selected element
    */

    public void Copy(String passw, E element) throws IncorrectPswException, ElementNotFoundException;
    /* If the password is right it creates a copy of the element and adds it to the user's collection
        requires: password and element
        modifies: this
        throws: if the password is wrong throws IncorrectPswException
                if the element==null throws NullPointerException
                if the element is not present in the user collection throws ElementNotFoundException
        effects: creates a copy of the element and adds it in the collection
    */

    public Iterator<E> GetIterator(String passw) throws IncorrectPswException;
    /* If the password is correct it returns a Iterator that generates all user's elements in the collection randomly.
        requires: password
        modifies: -
        throws: if the password is incorrect throws IncorrectPswException
        effects: returns a Iterator
    */

    public boolean Authorized(String passw,String Other) throws IncorrectPswException, UserAlreadyPresentException;
    /* If the password is correct check if the other's id is already present in the user's linked list.
        requires:  password and other's id
        modifies: this
        throws: if the other's id is already present in the user's linked list throws UserAlreadyPresentException
                if the password is wrong throws IncorrectPswException
                if other==null throws NullPointerException
        effects: adds the other's id in the user's linked list
    */

    public boolean CheckAuthorizedUser(String Other);
    /* Checks if the selected id is present in the user's  AuthorizedUsers linked list.
        requires: other's id
        modifies: -
        throws: if other==null throws NullPointerException
        effects: returns true if the other's id is present in the user's linked list, otherwise returns false
    */

    public void ShareData( E element);
    /* Adds the selected element.
        requires: element
        modifies: this
        throws: if the element==null throws NullPointerException
        effects: it adds the selected element to the user collection of data
    */
}
