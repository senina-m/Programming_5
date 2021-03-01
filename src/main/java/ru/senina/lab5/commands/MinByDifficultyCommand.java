package ru.senina.lab5.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.Parser;


public class MinByDifficultyCommand extends CommandWithoutArgs {
    private CollectionKeeper collectionKeeper;
    private Parser parser;

    public MinByDifficultyCommand(CollectionKeeper collectionKeeper, Parser parser) {
        super("min_by_difficulty");
        this.collectionKeeper = collectionKeeper;
        this.parser = parser;
    }

    @Override
    protected String doRun() {
        try {
            return "The less difficult subject is: \n" + parser.fromElementToString(collectionKeeper.minByDifficulty());
        }catch (JsonProcessingException e){
            return "Parsing got wrong.";
        }
        catch (IndexOutOfBoundsException e){
            return "Can't do min_by_difficulty command. " + e.getMessage();
        }
    }
}
