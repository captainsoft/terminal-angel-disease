/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.command;

/**
 * A parametrized command.
 *
 * @author mathias fringes
 */
public interface ParamCommand<T> {

    public void execute(T object);

}