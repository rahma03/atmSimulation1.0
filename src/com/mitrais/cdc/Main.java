package com.mitrais.cdc;

import com.mitrais.cdc.service.MenuService;
import com.mitrais.cdc.service.impl.WelcomeServiceImpl;

public class Main {

    public static void main(String[] args) {
        MenuService menu = new WelcomeServiceImpl();
        while (menu !=null){
            menu = menu.process();
        }
    }
}
