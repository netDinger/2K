package com.dingar.twok.core;

public interface BasePresenter<T>{

    /**
     * Binds presenter with a View when resumed. The Presenter will perform initialization here.
     *
     * @param view the View associated with this presenter
     */
    void setView(T view);

    /**
     * Drops the reference to the View when destroyed
     */
    void dropView();

}
