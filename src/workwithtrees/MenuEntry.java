/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workwithtrees;

/**
 *
 * 
 * Класс Пункта меню
 * @author Пользователь
 */
abstract class MenuEntry {
    private String title; //Для маркировки объектов в коде

    public MenuEntry(String title) {
        this.title = title;
    }

    public abstract void run();
}
