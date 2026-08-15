package com.forge.capabilities;

public interface Capability<I, O> {

    O execute(I input);
}