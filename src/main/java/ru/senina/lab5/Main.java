package ru.senina.lab5;
import java.util.Optional;


/**
 * @author Senina Mariya
 * Main class of programm to keeping and processing Collection of LabWork elements.
 */
public class Main {

    public static void main(String[] args){
        try{
            String path = Optional.ofNullable(System.getenv("SENINA")).orElseThrow(
                    () -> new InvalidArgumentsException("SENINA variable is not set in the environment! Set thi variable! The program can't work without it!"));
            Keeper keeper = new Keeper(System.getenv(path));
            keeper.start();
        }catch (InvalidArgumentsException e){
            System.out.println(e.getMessage());
        }

    }
}
