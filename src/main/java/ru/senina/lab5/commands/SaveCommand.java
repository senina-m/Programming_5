package ru.senina.lab5.commands;

import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.Parser;

import java.io.IOException;

/**
 * Command saves collection to file
 */
public class SaveCommand extends CommandWithoutArgs {
    private final CollectionKeeper collectionKeeper;
    private final Parser parser;
    private String filename;

    public SaveCommand(CollectionKeeper collectionKeeper, Parser parser, String filename) {
        super("save", "save collection to file");
        this.collectionKeeper = collectionKeeper;
        this.parser = parser;
        this.filename = filename;
    }

    @Override
    protected String doRun(){
        try {
            parser.writeStringToFile(filename, parser.fromCollectionKeeperToJson(collectionKeeper));
            return "Collection was successfully saved to " + filename + " file.";
        } catch (IOException e){
            return "Collection saving was failed - there is no " + filename + " file.";
        }
    }
}
