package handin1.view;

import handin1.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;

public class ViewFactory {
    public static final String ADD = "add";
    public static final String MANAGE = "manage";

    private final ViewHandler viewHandler;
    private final ViewModelFactory viewModelFactory;
    private AddVinylViewController addVinylViewController;
    private ManageVinylsViewController manageVinylsViewController;

    public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.addVinylViewController = null;
        this.manageVinylsViewController = null;
    }
    public Region loadAddVinylView() {
        if (addVinylViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/handin1/AddVinylView.fxml"));
            try {
                Region root = loader.load();
                addVinylViewController = loader.getController();
                addVinylViewController.init(viewHandler, viewModelFactory.getAddVinylViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        addVinylViewController.reset();
        return addVinylViewController.getRoot();
    }
    public Region loadManageVinylsView() {
        if (manageVinylsViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/handin1/ManageVinylsView.fxml"));
            try {
                Region root = loader.load();
                manageVinylsViewController = loader.getController();
                manageVinylsViewController.init(viewHandler, viewModelFactory.getManageVinylsViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        manageVinylsViewController.reset();
        return manageVinylsViewController.getRoot();
    }


    public Region loadView(String id) {
        return switch (id) {
            case ADD -> loadAddVinylView();
            case MANAGE -> loadManageVinylsView();
            default -> throw new IllegalArgumentException("Unknown view: " + id);
        };
    }
}
