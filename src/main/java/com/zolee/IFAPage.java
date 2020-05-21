package com.zolee;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

public class IFAPage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "IFA";
    private Button calculationPolicy;
    private Button leaguePosition;
    private Button gamePolicy;
    private final Label WELCOME ;


    public IFAPage() {
        MenuBar barmenu = new MenuBar();
        addComponent(barmenu);

        // regular nav
        MenuBar.MenuItem login = barmenu.addItem("Login", null, mycommand);
        MenuBar.MenuItem servs = barmenu.addItem("Services", null, null);
        servs.addItem("Car Service", null, mycommand);

        WELCOME = new Label("WELCOME TO OUR IFA!");
        addComponent(WELCOME);

        Panel panel = new Panel("IFA options");
        FormLayout content = new FormLayout();
        panel.setSizeUndefined();
        addComponent(panel);

        calculationPolicy = new Button("Add a score calculation policy");
        calculationPolicy.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Page.getCurrent().setUriFragment("!"+CalculationPolicyPage.NAME);
            }
        });
        content.addComponent(calculationPolicy);

        leaguePosition = new Button("Adding a league position");
        leaguePosition.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Page.getCurrent().setUriFragment("!"+CalculationPolicyPage.NAME);
            }
        });
        content.addComponent(leaguePosition);

        gamePolicy = new Button("Define a game embedding policy");
        gamePolicy.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Page.getCurrent().setUriFragment("!"+CalculationPolicyPage.NAME);
            }
        });
        content.addComponent(gamePolicy);

        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

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
