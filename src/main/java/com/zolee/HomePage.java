package com.zolee;

import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;


public class HomePage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "Home";
    private final Label selection = new Label("-");

    public  HomePage() {
        addComponent(selection);
        MenuBar barmenu = new MenuBar();
        addComponent(barmenu);

        // Another top-level item
        MenuBar.MenuItem login = barmenu.addItem("Login", null, mycommand);


        // Yet another top-level item
        MenuBar.MenuItem servs = barmenu.addItem("Services", null, null);
        servs.addItem("Car Service", null, mycommand);


    }
    // Define a common menu command for all the menu items.
    MenuBar.Command mycommand = new MenuBar.Command() {
        public void menuSelected(MenuBar.MenuItem selectedItem) {
            selection.setValue("Ordered a " +
                    selectedItem.getText() +
                    " from menu.");

            getUI().getNavigator().addView(selectedItem.getText(), LoginPage.class);
            Page.getCurrent().setUriFragment("!"+selectedItem.getText());
        }
    };
}
