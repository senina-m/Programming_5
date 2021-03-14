package ru.senina.lab5.commands;

import ru.senina.lab5.labwork.LabWork;

/**
 * Interface to mark classes who need to receive an element
 */
abstract public interface ElementNeed {
    abstract void setLabWorkElement(LabWork labWorkElement);
}
