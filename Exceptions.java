class IncorrectPswException extends Exception{
    public IncorrectPswException(){
        super("Incorrect password!");
    }
}

class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("Incorrect Id, user not found!");
    }
}

class NotAuthorizedUserException extends Exception{
    public NotAuthorizedUserException(){
        super("Not authorized user!");
    }
}

class UserAlreadyPresentException extends Exception{
    public UserAlreadyPresentException(){
        super("User is already present!");
    }
}

class ElementNotFoundException extends Exception{
    public ElementNotFoundException() {
        super("Element not found!");
    }
}