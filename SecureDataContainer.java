import java.util.Iterator;
import java.lang.String;
import java.lang.*;

//TE: {<Idi,passwi,{Ej|1<=j<n}, {Usrj|1<=j<=m}> | 0<=i<=k} dove Idi e passwi sono di tipo String, Ej di tipo E,
//      Usrj di tipo String

public interface SecureDataContainer<E> {
    public void createUser(String Id, String passw);
    /* Creates the identity of a new user in this collection.
        requires: Id and password
        modifies: this
        throws: if the Id is already been used throws IllegalArgumentException
                if Id or password are both null throws NullPointerException
                If Id or password are empty strings throws IllegalArgumentException
        effects: creates a new user for this collection
     */
    public void RemoveUser(String Id, String passw) throws UserNotFoundException, IncorrectPswException;
    /* Removes the user from the collection.
        requires: Id and password
        modifies: this
        throws: if Id or password are both null throws NullPointerException
                If Id or password are empty strings throws IllegalArgumentException
                If user is not present in the collection throws UserNotFoundException
                If the password is not correct throws IncorrectPswException
        effects: removes the selected user from this collection
     */
    public int getSize(String Owner, String passw) throws IncorrectPswException, UserNotFoundException;
    /* Returns the number of data elements owned by the current selected user of this collection.
        requires: owner and password
        modifies: this
        throws: if the Id is incorrect throws UserNotFoundException
                if the password is incorrect throws IncorrectPswException
                if Id or password are both null throws NullPointerException
        effects: returns the effective number of elements of a user
     */
    public boolean put(String Owner, String passw, E data) throws IncorrectPswException, UserNotFoundException;
    /* If Id and password are both correct inserts the E data value in the collection.
        requires: owner, password and data
        modifies: this
        throws: if the Id is incorrect throws UserNotFoundException
                if the password is incorrect throws IncorrectPswException
                if data==null throws NullPointerException
                if Id or password are both null throws NullPointerException
        effects: inserts the E data value in the user's collection and returns true if it has been correctly inserted
     */
    public E get(String Owner,String passw, E data) throws IncorrectPswException, UserNotFoundException, ElementNotFoundException;
    /* If Id and password are both correct it gets a copy of the E data value in the collection.
        requires: owner, password and data
        modifies: -
        throws: if the Id is incorrect throws UserNotFoundException
                if the password is incorrect throws IncorrectPswException
                if data==null throws NullPointerException
                if Id or password are both null throws NullPointerException
                if the element is not present in the user collection throws ElementNotFoundException
        effects: returns a copy of the E data value in the user's collection
     */
    public E remove(String Owner, String passw, E data) throws IncorrectPswException, UserNotFoundException, ElementNotFoundException;
    /* If Id and password are both correct it removes the E data value in the collection.
        requires: owner, password and data
        modifies: this
        throws: if the Id is incorrect throws UserNotFoundException
                if the password is incorrect throws IncorrectPswException
                if data==null throws NullPointerException
                if the element is not present throws ElementNotFoundException
                if Id or password are both null throws NullPointerException
        effects: removes and returns the E data value from the user's collection
    */
    public void copy(String Owner, String passw, E data) throws IncorrectPswException, UserNotFoundException, ElementNotFoundException;
    /* If Id and password are both correct it creates a copy of the E data value and adds it in the collection.
        requires: owner, password and data
        modifies: this
        throws: if the Id is incorrect throws UserNotFoundException
                if the password is incorrect throws IncorrectPswException
                if data==null throws NullPointerException
                if Id or password are both null throws NullPointerException
                if the element is not present in the user collection throws ElementNotFoundException
        effects: creates a copy of the E data value and adds it in the user's collection
     */
    public void share(String Owner, String passw, String Other, E data) throws IncorrectPswException, UserNotFoundException, NotAuthorizedUserException, UserAlreadyPresentException;
    /* If Id and password are both correct it shares the E data value in the collection with another user.
        requires: owner, password, other user and data
        modifies: this
        throws: if the Owner's or Other's Id is incorrect throws UserNotFoundException
                if the password is incorrect throws IncorrectPswException
                if the other user is not part of the owner's linked list throws NotAuthorizedUserException
                if the other user is already present in the owner's linked list throws UserAlreadyPresentException
                if data==null throws NullPointerException
                if Id or password are both null throws NullPointerException
        effects: shares the E data value in the collection with another user
     */
    public Iterator<E> getIterator(String Owner, String passw) throws IncorrectPswException, UserNotFoundException, NullPointerException;
    /*If Id and password are both correct it returns a Iterator that generates all user's data value randomly.
        requires: owner and password
        modifies: -
        throws: if the Id is incorrect throws UserNotFoundException
                if the password is incorrect throws IncorrectPswException
                if data==null throws NullPointerException
                if Id or password are both null throws NullPointerException
        effects: returns a Iterator
     */
    public void authorizedUser(String Owner,String passw, String Other) throws UserNotFoundException, IncorrectPswException, UserAlreadyPresentException;
    /*If Id and password are both correct checks if the other user has been authorized by the owner and it has been inserted
      in the owner's linked list. If not, it adds the other user to the list.
      requires: owner, password and other
      modifies: this
      throws: if Id or password are both null throws NullPointerException
              if the password is incorrect throws IncorrectPswException
              if the Owner's or Other's Id is incorrect throws UserNotFoundException
              if the Other's Id is already present in the Owner's linked list throws UserAlreadyPresentException
      effects: checks if the other user is in the owner's linked list, otherwise it adds it
     */
}

