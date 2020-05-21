package com.zolee;

import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LoginPage extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "";
	private Authentication Auth;
    private final Label WELCOME ;


    @Autowired
	public void setAuth(Authentication tempAuth) {
		Auth = tempAuth;
	}

	public LoginPage(){

        MenuBar barmenu = new MenuBar();
        addComponent(barmenu);

        // regular nav
        MenuBar.MenuItem login = barmenu.addItem("Login", null, mycommand);
        MenuBar.MenuItem servs = barmenu.addItem("Services", null, null);
        servs.addItem("Car Service", null, mycommand);

        WELCOME = new Label("WELCOME TO OUR IFA!");
        addComponent(WELCOME);

		Panel panel = new Panel("Login");
		panel.setSizeUndefined();
		addComponent(panel);

		FormLayout content = new FormLayout();
		TextField username = new TextField("Username");
		content.addComponent(username);
		PasswordField password = new PasswordField("Password");
		content.addComponent(password);

		Button send = new Button("Enter");
		send.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
			    String userKind= authenticationTest(username, password);
				if(userKind!="fail"){
					VaadinSession.getCurrent().setAttribute("user", username.getValue());
                    VaadinSession.getCurrent().setAttribute("kind", userKind);
					Page.getCurrent().setUriFragment("!"+userKind);
				}else{
					Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
				}
			}

		});
		content.addComponent(send);
		content.setSizeUndefined();
		content.setMargin(true);
		panel.setContent(content);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
	}

	//TODO: real Authentication test in authentication class
    //TODO: supposed to check is exist in DB- if exist return type, if not return fail
    private String authenticationTest(TextField username, PasswordField password) {
        return "IFA";
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
    @Override
	public void enter(ViewChangeEvent event) {

	}

}
