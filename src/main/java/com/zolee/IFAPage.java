package com.zolee;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

public class IFAPage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "IFA";
    private final Label selection = new Label("-");
    private Label WELCOME;
    private Button calculationPolicy;

    public IFAPage() {

        addComponent(selection);
        MenuBar barmenu = new MenuBar();
        addComponent(barmenu);

        // Another top-level item
        MenuBar.MenuItem login = barmenu.addItem("Login", null, mycommand);

        // Yet another top-level item
        MenuBar.MenuItem servs = barmenu.addItem("Services", null, null);
        servs.addItem("Car Service", null, mycommand);


        calculationPolicy = new Button("Add a score calculation policy");
        calculationPolicy.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Page.getCurrent().setUriFragment("!"+CalculationPolicyPage.NAME);
            }
        });
        WELCOME = new Label("WELCOME TO OUR IFA!");
        addComponent(WELCOME);
        addComponent(calculationPolicy);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

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
