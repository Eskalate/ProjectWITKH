package com.example.appengine.springboot;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("")
public class CarGUI extends VerticalLayout {


    private final CarManager manager;

    private Car car;

    private boolean addVision = false;


    private Grid<Car> grid = new Grid<>(Car.class);
    private TextField model = new TextField("model");
    private TextField mark = new TextField("mark");

    private Button button = new Button("UPDATE", e -> saveCar());
    private Button plus = new Button("+");
    private Button delete = new Button("DELETE", e -> deleteCar());
    private Button add = new Button("ADD", e-> addCar());


    public CarGUI(CarManager manager){

        this.manager = manager;

        grid.setColumns("mark", "model");
        grid.addSelectionListener(e -> updateForm());
        plus.addClickListener(e -> addForm());




        add(grid, mark, model, plus, delete, add, button);
        setMargin(true);
        setSpacing(true);
        add.setVisible(false);
        plus.setVisible(true);
        delete.setVisible(false);
        button.setVisible(false);

        updateGrid();


    }

    private void updateGrid(){

        grid.setItems(manager.findAll());
        setFormVisible(false);
    }
    private void addForm(){
        if(addVision == false){
        car = new Car(999L, "default", "default");
        Binder<Car> adding = new Binder<>(Car.class);
        adding.bindInstanceFields(this);
        adding.setBean(car);
        setFormVisible(true);
        add.setVisible(true);
        addVision = true;   }
        else{
            setFormVisible(false);
            add.setVisible(false);
            addVision = false;
        }
    }


   private void updateForm(){
        if (grid.asSingleSelect().isEmpty()){
            plus.setVisible(true);
            setFormVisible(false);
            button.setVisible(false);
            delete.setVisible(false);
        }else {
            car = grid.asSingleSelect().getValue();
            Binder<Car> binder = new Binder<>(Car.class);
            binder.bindInstanceFields(this);
            binder.setBean(car);
            setFormVisible(true);
            plus.setVisible(false);
            add.setVisible(false);
            button.setVisible(true);
            delete.setVisible(true);

        }
    }

    private void setFormVisible(boolean visible){
        mark.setVisible(visible);
        model.setVisible(visible);
    }


    private void saveCar(){
        manager.update(car);
        updateGrid();

    }

    private void addCar(){
        manager.add(car);
        updateGrid();
    }

    private void deleteCar(){
        manager.delete(car);
        updateGrid();
    }

}
