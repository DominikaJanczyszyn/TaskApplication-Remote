package handin1.viewmodel;

import handin1.model.Model;

public class ViewModelFactory {
    private AddVinylViewModel addVinylViewModel;
    private ManageVinylViewModel manageVinylsViewController;

    public ViewModelFactory(Model model){
        this.addVinylViewModel = new AddVinylViewModel(model);
        this.manageVinylsViewController = new ManageVinylViewModel(model);
    }
    public AddVinylViewModel getAddVinylViewModel(){
        return addVinylViewModel;
    }
    public ManageVinylViewModel getManageVinylsViewModel(){return  manageVinylsViewController;}
}
