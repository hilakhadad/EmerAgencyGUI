package Objects.Users;

import Models.Model;
import Objects.Category;
import Objects.Complaint;
import javafx.collections.ObservableList;

import java.util.List;

public class CenterAdmin extends Admin {
    private List<Category> categoryList;

    public CenterAdmin(String userName, String password, String organization) {
        super(userName, password, organization);
    }

    @Override
    public void setModel(Model model) {
        this.m_model = model;
        categoryList = m_model.getAllCategories();
    }


    public boolean createNewCategory(String category_name, String category_desc){
        boolean c = categoryInList(category_name);
        if(!c){
            Category cat = new Category(category_name,category_desc);
            categoryList.add(cat);
            m_model.createCategory(cat);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean categoryInList(String category_name) {
        for (Category c:categoryList) {
            if(c.getCategoryName().equals(category_name))
                return true;
        }
        return false;
    }

    public ObservableList<RegularUser> getAllUsers(){
        return m_model.showUsers();
    }
}
