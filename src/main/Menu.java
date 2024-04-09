/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controlls.Utilities;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BAO TRAN
 */
public class Menu {

    private String menuTittle;
    private final List<String> optionList = new ArrayList<String>();

    public Menu(String menuTittle) {
        this.menuTittle = menuTittle;
    }

    public void addNewOption(String option) {
        optionList.add(option);
    }

    public void printMenu() {
        if (optionList.isEmpty()) {
            System.out.println("Menu is empty!!");
            return;
        }
        System.out.println("\n " + menuTittle + "");
        for (String a : optionList) {
            System.out.println(a);
        }
    }

    public int getChoice() {
        int max = optionList.size();
        return Utilities.getInt("Choose [1..." + max + "]: ", 0, max + 1);

    }
}
