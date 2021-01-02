import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

//  AF: a2(dataContainer)={<Id, a-user(dataContainer.get(Id))>}
//      a-user(User)=<User.Id,User.passw,{User.DataElements.get(i) | 0<=i<User.DataElements.size()},
//      {User.AuthorizedUsers.get(i) | 0<=i<User.AuthorizedUsers.size()}>
//  IR: dataContainer!=null && (dataContainer.SetKeys.get(i)!=null && dataContainer.SetKeys.get(i)!="" | ∀i 0<=i<dataContainer.SetKeys.size())
//      && get(Id,passw,data)!=null ∀Id)


public class TreeSecureDataContainer <E> implements SecureDataContainer<E> {
    Map<String, User<E>> dataContainer;

    public TreeSecureDataContainer(){
        dataContainer= new TreeMap<String, User<E>>();
    }

    private boolean FoundId(String id) {
        if(id==null) throw new NullPointerException();
        if(!dataContainer.containsKey(id)) return false;
        else return true;
    }

    public void createUser(String id, String passw){
        if(id==null || passw==null) throw new NullPointerException();
        if(id.isEmpty() || passw.isEmpty()) throw new IllegalArgumentException();
        if(dataContainer.containsKey(id)) throw new IllegalArgumentException();
        else dataContainer.put(id,new User<E>(id, passw));
    }

    public void RemoveUser(String id, String passw) throws UserNotFoundException, IncorrectPswException{
        if(id==null || passw==null) throw new NullPointerException();
        if(id.isEmpty() || passw.isEmpty()) throw new IllegalArgumentException();
        if (!dataContainer.containsKey(id)) throw new UserNotFoundException();
        else {
            if(dataContainer.get(id).VerifyPsw(passw)) dataContainer.remove(id);
        }
    }

    public int getSize(String Owner, String passw) throws IncorrectPswException, UserNotFoundException{
        if(!FoundId(Owner)) throw new UserNotFoundException();
        else return dataContainer.get(Owner).getSize(passw);
    }

    public boolean put(String Owner, String passw, E data) throws IncorrectPswException, UserNotFoundException{
        if(!FoundId(Owner)) throw new UserNotFoundException();
        else return dataContainer.get(Owner).Put(passw,data);
    }

    public E get(String Owner,String passw, E data) throws IncorrectPswException, UserNotFoundException, ElementNotFoundException{
        if(!FoundId(Owner)) throw new UserNotFoundException();
        else return dataContainer.get(Owner).Get(passw,data);
    }

    public E remove(String Owner, String passw, E data) throws IncorrectPswException, UserNotFoundException, NullPointerException, ElementNotFoundException{
        if(!FoundId(Owner)) throw new UserNotFoundException();
        else return dataContainer.get(Owner).Remove(passw,data);
    }

    public void copy(String Owner, String passw, E data) throws IncorrectPswException, UserNotFoundException, ElementNotFoundException{
        if(!FoundId(Owner)) throw new UserNotFoundException();
        else dataContainer.get(Owner).Copy(passw,data);
    }

    public void authorizedUser(String Owner,String passw,String Other) throws UserNotFoundException, IncorrectPswException, UserAlreadyPresentException{
        if(!FoundId(Owner) || !FoundId(Other)) throw new UserNotFoundException();
        else dataContainer.get(Owner).Authorized(passw,Other);
    }

    public void share(String Owner, String passw, String Other, E data) throws IncorrectPswException, UserNotFoundException, NotAuthorizedUserException{
        if(!FoundId(Owner) || !FoundId(Other)) throw new UserNotFoundException();
        if(data==null) throw new NullPointerException();
        if(dataContainer.get(Owner).VerifyPsw(passw)){
            if (dataContainer.get(Other).CheckAuthorizedUser(Owner)){
                dataContainer.get(Other).ShareData(data);
            }
            else throw new NotAuthorizedUserException();
        }

    }

    public Iterator<E> getIterator(String Owner, String passw) throws IncorrectPswException, UserNotFoundException{
        if(!FoundId(Owner)) throw new UserNotFoundException();
        else return dataContainer.get(Owner).GetIterator(passw);
    }



}
