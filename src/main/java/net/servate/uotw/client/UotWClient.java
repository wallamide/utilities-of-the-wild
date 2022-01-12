package net.servate.uotw.client;

import net.servate.uotw.reg.UotWRender;

import net.fabricmc.api.ClientModInitializer;

public class UotWClient implements ClientModInitializer {
    

    @Override
    public void onInitializeClient() {
        
        UotWRender.register();


    }
}