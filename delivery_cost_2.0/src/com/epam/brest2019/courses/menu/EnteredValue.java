package com.epam.brest2019.courses.menu;

public interface EnteredValue {
    enum Types{EXIT, INCORRECT, CORRECT}
    Types getType();
}
