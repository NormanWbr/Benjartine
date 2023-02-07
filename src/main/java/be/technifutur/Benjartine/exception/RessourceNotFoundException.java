package be.technifutur.Benjartine.exception;

public class RessourceNotFoundException extends RuntimeException{

    public RessourceNotFoundException(){
        super("The requested resource was not found");
    }

    public RessourceNotFoundException(Throwable cause){
        super("The requested resource was not found", cause);
    }

}
