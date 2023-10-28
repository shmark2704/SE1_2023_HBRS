package org.hbrs.se1.ws23.uebung2;

public class SingletonContainer extends Container{

    private static SingletonContainer singleton;

    private void SingletonContainer() {
        singleton = (SingletonContainer) new Container();
    }

    public static SingletonContainer getInstance() {
        return singleton;
    }
}
