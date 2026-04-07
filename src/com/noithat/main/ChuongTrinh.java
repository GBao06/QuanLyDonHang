package com.noithat.main;

import javax.swing.SwingUtilities;

import com.noithat.model.OrderComponent;
import com.noithat.service.ComboBuilder;
import com.noithat.ui.ComboUI;

public class ChuongTrinh {
    public static void main(String[] args) {
	      SwingUtilities.invokeLater(() -> {
	    	  new ComboUI().setVisible(true);
	      });
    }
}
