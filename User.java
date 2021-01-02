import java.util.Iterator;
import java.util.LinkedList;

//  AF: a(Id,passw,DataElements,AuthorizedUsers)= <Id,passw,{DataElements.get(i) | 0<=i<DataElements.size()},
//      {AuthorizedUsers.get(i) | 0<=i<AuthorizedUsers.size()}>
//  IR: Id!=null && Id!="" && pssw!=null && pssw!="" && (DataElements.get(i)!=null | ∀i 0<=i<DataElements.size())
//      (AuthorizedUsers.get(i)!=null | ∀i 0<=i<AuthorizedUsers.size()) && DataElement.size()>=0 && AuthorizedUsers.size>=0

public class User<E> implements UserInterface<E> {
    private String Id;
    private String passw;
    private LinkedList<E> DataElements;
    private LinkedList<String> AuthorizedUsers;

    public User(String id, String password){
        if(id==null || password==null) throw new NullPointerException();
        if(id.isEmpty() || password.isEmpty()) throw new IllegalArgumentException();
        Id=id;
        passw=password;
        DataElements= new LinkedList<E>();
        AuthorizedUsers= new LinkedList<String>();
    }

    public String getId(){
        return Id;
    }

    public boolean VerifyPsw(String psw) throws NullPointerException, IncorrectPswException{
        if(psw==null) throw new NullPointerException();
        if (psw.equals(passw)) return true;
        else throw new IncorrectPswException();
    }

    public int getSize(String passw) throws IncorrectPswException{
        if(VerifyPsw(passw)) return DataElements.size();
        else throw new IncorrectPswException();
    }

    public boolean Put(String passw, E element) throws IncorrectPswException, NullPointerException{
        if(element!=null){
            if(VerifyPsw(passw)) {
                DataElements.add(element);
                return true;
            }
            else {
                throw new IncorrectPswException();
            }
        }
        else {
            throw new NullPointerException();
        }
    }

    public E Get(String passw, E element) throws IncorrectPswException, NullPointerException,ElementNotFoundException{
        if(!VerifyPsw(passw)) throw new IncorrectPswException();
        int index= DataElements.indexOf(element);
        if(index==-1) throw new ElementNotFoundException();
        E ElCopy= DataElements.get(index);
        return ElCopy;
    }

    public E Remove(String passw, E element) throws IncorrectPswException, NullPointerException, ElementNotFoundException{
        if(!VerifyPsw(passw)) throw new IncorrectPswException();
        int index= DataElements.indexOf(element);
        if(index==-1) throw new ElementNotFoundException();
        else return DataElements.remove(index);
    }

    public void Copy(String passw, E element) throws IncorrectPswException, NullPointerException, ElementNotFoundException{
        E ElCopy= Get(passw, element);
        DataElements.add(element);
    }

    public Iterator<E> GetIterator(String passw) throws IncorrectPswException{
        if(!VerifyPsw(passw)) throw new IncorrectPswException();
        else return new iterator(DataElements.iterator());
    }

    private class iterator implements Iterator<E>{
        private Iterator<E> i;

        public iterator(Iterator<E> E_Iterator){
            i=E_Iterator;
        }
        public boolean hasNext(){
            return i.hasNext();
        }
        public E next(){
            return i.next();
        }
        public void remove() throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
        }
    }

    public boolean Authorized(String passw,String Other) throws IncorrectPswException, UserAlreadyPresentException, NullPointerException{
        if(Other==null) throw new NullPointerException();
        if(!VerifyPsw(passw)) throw new IncorrectPswException();
        if(AuthorizedUsers.contains(Other)) throw new UserAlreadyPresentException();
        return AuthorizedUsers.add(Other);
    }

    public boolean CheckAuthorizedUser(String Other) throws NullPointerException{
        if(Other==null) throw new NullPointerException();
        return AuthorizedUsers.contains(Other);
    }

    public void ShareData( E element) throws NullPointerException{
        if(element==null) throw new NullPointerException();
        DataElements.add(element);
    }

}
