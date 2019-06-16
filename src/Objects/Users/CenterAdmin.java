package Objects.Users;

import Model.Model;
import Objects.Category;
import javafx.collections.ObservableList;

import java.util.List;

public class CenterAdmin extends Admin {
    private static CenterAdmin centerAdmin;
    private List<Category> categoryList;

    private CenterAdmin(String userName, String password, String organization) {
        super(userName, password, organization);
    }

    public static CenterAdmin getInstance(){
        if(centerAdmin==null)
            centerAdmin= new CenterAdmin("centerAdmin", "1234","Center");
        return centerAdmin;
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