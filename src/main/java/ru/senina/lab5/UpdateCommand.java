package ru.senina.lab5;
public class UpdateCommand extends Command{
    private CollectionKeeper collectionKeeper;
    public UpdateCommand(String name, CollectionKeeper collectionKeeper) {
        super(name);
        this.collectionKeeper = collectionKeeper;
    }

    @Override
    public String run() {
        //TODO: метод проверяющий, что у команды есть все необходимые аргументы и нет лишних
       return null;
    }
}
