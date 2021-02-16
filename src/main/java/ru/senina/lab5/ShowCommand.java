package ru.senina.lab5;

public class ShowCommand extends Command{

    CollectionKeeper collectionKeeper;

    public ShowCommand(String name) {
        super(name);
    }

    public CollectionKeeper getCollectionKeeper() {
        return collectionKeeper;
    }

    public void setCollectionKeeper(CollectionKeeper collectionKeeper) {
        this.collectionKeeper = collectionKeeper;
    }

    @Override
    public String run() {
        return "Эта коллекция была создана: " + collectionKeeper.getTime() + "\n"
                + "Тип коллекции: " + collectionKeeper.getType() + "\n"
                + "Колличество элементов в коллекции: " + collectionKeeper.getAmountOfElements() + "\n";
    }
}
