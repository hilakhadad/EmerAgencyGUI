package sample;

import sample.Model.Model;

public class Controller {

    View m_view;
    Model m_model;

    public void setView(View view){
        m_view = view;
    }

    public void setModel(Model model){
        m_model = model;
    }

}
