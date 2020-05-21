package com.zolee;

import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

public class HomePage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "Home";
    private final Label WELCOME ;


    public  HomePage() {
        MenuBar barmenu = new MenuBar();
        addComponent(barmenu);

        // Another top-level item
        MenuBar.MenuItem login = barmenu.addItem("Login", null, mycommand);


        // Yet another top-level item
        MenuBar.MenuItem servs = barmenu.addItem("Services", null, null);
        servs.addItem("Car Service", null, mycommand);
        WELCOME = new Label("WELCOME TO Soccer game web!");
        addComponent(WELCOME);

    }
    // Define a common menu command for all the menu items.
    MenuBar.Command mycommand = new MenuBar.Command() {
        public void menuSelected(MenuBar.MenuItem selectedItem) {
            WELCOME.setValue("Ordered a " +
                    selectedItem.getText() +
                    " from menu.");

            getUI().getNavigator().addView(selectedItem.getText(), LoginPage.class);
            Page.getCurrent().setUriFragment("!"+selectedItem.getText());
        }
    };
}
