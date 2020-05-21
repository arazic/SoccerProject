package com.zolee;

import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;


public class HomePage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "Home";

    // A feedback component
    final Label selection = new Label("-");

    public  HomePage() {
        addComponent(selection);
        MenuBar barmenu = new MenuBar();
        addComponent(barmenu);

//         MenuBar.MenuItem drinks = barmenu.addItem("Beverages", null, null);
//         // Submenu item with a sub-submenu
//         MenuBar.MenuItem hots = drinks.addItem("Hot", null, null);
//         hots.addItem("Tea",
//                 new ThemeResource("icons/tea-16px.png"), mycommand);
//         hots.addItem("Coffee",
//                 new ThemeResource("icons/coffee-16px.png"), mycommand);

//        // Another submenu item with a sub-submenu
//         MenuBar.MenuItem colds = drinks.addItem("Cold", null, null);
//         colds.addItem("Milk", null, mycommand);
//         colds.addItem("Weissbier", null, mycommand);


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
