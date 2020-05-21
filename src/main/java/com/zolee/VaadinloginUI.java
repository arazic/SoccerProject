package com.zolee;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;


@SuppressWarnings("serial")
@Theme("valo")
@SpringUI
public class VaadinloginUI extends UI {

	private String currentKind;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VaadinloginUI.class)
	public static class Servlet extends VaadinServlet {
	}

	//	public static Authentication AUTH;
	@Override
	protected void init(VaadinRequest request) {
//		AUTH = new Authentication();
		new Navigator(this, this);

		getNavigator().addView(LoginPage.NAME, LoginPage.class);
		getNavigator().addView(HomePage.NAME,HomePage.class);
		getNavigator().addView(SecurePage.NAME, SecurePage.class);
		getNavigator().addView(IFAPage.NAME, IFAPage.class);
		getNavigator().addView(CalculationPolicyPage.NAME, CalculationPolicyPage.class);

		getNavigator().setErrorView(HomePage.class);


		Page.getCurrent().addUriFragmentChangedListener(new UriFragmentChangedListener() {

			@Override
			public void uriFragmentChanged(UriFragmentChangedEvent event) {
				router(event.getUriFragment());
			}
		});

		router("");
	}

	private void router(String route){
		Notification.show(route);
		if(route.equals("!Login")) {
			getNavigator().navigateTo(LoginPage.NAME);
			return;
		}
		if(getSession().getAttribute("user") != null){
			switch (route){
				case "!IFA":
					getNavigator().navigateTo(IFAPage.NAME);
					break;
				case "!CalculationPolicy":
					getNavigator().navigateTo(CalculationPolicyPage.NAME);
					break;
				default:
					getNavigator().navigateTo(HomePage.NAME);
			}
		}else{
			getNavigator().navigateTo(HomePage.NAME);
		}
	}

}