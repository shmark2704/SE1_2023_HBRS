package org.hbrs.se1.ws23.uebung2;

public class SingletonContainer extends Container{

    private SingletonContainer singleton;

    private void SingletonContainer() {
        singleton = (SingletonContainer) new Container();
    }

    public SingletonContainer getInstance() {
        return singleton;
    }
}
