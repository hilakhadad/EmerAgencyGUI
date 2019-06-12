package Controller;

import Models.Model;
import Objects.User;
import Views.View;

public class Controller {

    View m_view;
    Model m_model;
    User loggedUser;

    public void setView(View view){
        m_view = view;
    }

    public void setModel(Model model){
        m_model = model;
    }

    public User getLoggedUser(){
        return loggedUser;
    }
}
