package pl.kostrzynkamien.cocktailomator.GUI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kostrzynkamien.cocktailomator.Model.FavouriteCocktail;
import pl.kostrzynkamien.cocktailomator.Model.Recipe;
import pl.kostrzynkamien.cocktailomator.Service.CocktailControler;
import pl.kostrzynkamien.cocktailomator.Service.CocktailService;

import java.util.ArrayList;


@Route("cocktail")
public class CocktailGUI extends VerticalLayout {

    private CocktailControler cocktailControler;
    private CocktailService cocktailService;
    private TextField textFieldName,textFieldIngredient;
    private Button buttonSearchByName;
    private Button buttonGetRandomCocktail;
    private Button buttonSearchIngredient;
    private Button buttonAddFavourite;
    private Button buttonCreateNewCocktail;
    private Button buttonDeleteFavouriteCocktail;
    private Button buttonPostOnFacebook;
    private Grid<Recipe> specificAndRandomGrid;
    private Grid<FavouriteCocktail> favouriteGrid;


    @Autowired
    public CocktailGUI(CocktailControler cocktailControler, CocktailService cocktailService) {
        this.cocktailService=cocktailService;
        fieldsInit();
        favouriteGridSet(favouriteGrid);
        specificAndRandomGridSet(specificAndRandomGrid);

        buttonPostOnFacebook.addClickListener(clickEvent -> {
            postOnFacebook();
        });
        buttonCreateNewCocktail.addClickListener(clickEvent -> {
            createANewCocktail(cocktailService);
        });
        buttonAddFavourite.addClickListener(clickEvent -> {
            favouriteAdder(cocktailService);
        });
        buttonDeleteFavouriteCocktail.addClickListener(clickEvent -> {
            favouriteDeleter(cocktailService);
        });
        buttonGetRandomCocktail.addClickListener(clickEvent -> {
            getCocktailAndDisplay(cocktailControler, "https://www.thecocktaildb.com/api/json/v1/1/random.php", "Cocktail not found");
        });
        buttonSearchByName.addClickListener(clickEvent -> {
            getCocktailAndDisplay(cocktailControler, "https://www.thecocktaildb.com/api/json/v1/1/search.php?s="+
                    textFieldName.getValue().replaceAll(" ",""), "Connection error! Try again later");
        });
        buttonSearchIngredient.addClickListener(clickEvent -> {
            showIngredientInfo(cocktailControler);
        });
        add(favouriteGrid,buttonCreateNewCocktail,buttonDeleteFavouriteCocktail, textFieldName,buttonSearchByName,textFieldIngredient,
                buttonSearchIngredient,buttonGetRandomCocktail,specificAndRandomGrid,buttonAddFavourite,buttonPostOnFacebook);
    }

    private void postOnFacebook() {
        Dialog dialog=new Dialog();
        Label label=new Label("Which cocktail do you want to share on facebook?");
        Button post=new Button("Post on facebook");
        Button cancel=new Button("Cancel");
        dialog.add(label,favouriteGrid,post,cancel);
        dialog.open();
        post.addClickListener(clickEvent -> {
            createAPost();
            dialog.close();
        });
        cancel.addClickListener(clickEvent -> {
            dialog.close();
        });
    }

    private void createAPost() {
        if (favouriteGrid.getSelectionModel().getFirstSelectedItem().isPresent()){
            FavouriteCocktail favouriteCocktail = favouriteGrid.getSelectionModel().getFirstSelectedItem().get();

        }
        else{
            Notification.show("Select a post!",
                    5000, Notification.Position.TOP_CENTER);
            postOnFacebook();
        }
    }

    private void showIngredientInfo(CocktailControler cocktailControler) {
        Dialog dialog=new Dialog();
        if(cocktailControler.getIngredientInfo(textFieldIngredient.getValue().replaceAll(" ",""))!=null)
        {
            dialog.add(new Label(cocktailControler.getIngredientInfo(textFieldIngredient.getValue().replaceAll(" ",""))));
            setDialog(dialog);
        }
            else Notification.show("Ingredient not found",
                5000, Notification.Position.TOP_CENTER);
    }

    private void getCocktailAndDisplay(CocktailControler cocktailControler, String url, String message) {
        if (cocktailControler.getRecipies(url) != null)
            specificAndRandomGrid.setItems(cocktailControler.getRecipies(url));
        else Notification.show(message,
                5000, Notification.Position.TOP_CENTER);
    }

    private void favouriteDeleter(CocktailService cocktailService) {
        if (favouriteGrid.getSelectionModel().getFirstSelectedItem().isPresent()){
            cocktailService.deleteFavouriteCocktailById(favouriteGrid.getSelectionModel().getFirstSelectedItem().get().getID());
            favouriteGrid.setItems(cocktailService.getAllFavouriteCocktails());
        }
        else{
            Notification.show("Something went wrong!",
                    5000, Notification.Position.TOP_CENTER);}
    }

    private void createANewCocktail(CocktailService cocktailService){
        ArrayList<TextField> ingredientList=new ArrayList<>();
        ArrayList<TextField> measureList=new ArrayList<>();
        TextField drinkName = new TextField("Enter Name");
        TextField category = new TextField("Enter Category");
        TextField genre = new TextField("Enter Genre");
        TextField glass = new TextField("Enter Glass");
        TextField instructions = new TextField("Enter Instructions");
        TextField drinkThumb = new TextField("Enter DrinkThumb");
        for(int i=0;i<=14;i++){
            ingredientList.add(new TextField("Enter Ingredient "+i));
            measureList.add(new TextField("Enter Measure "+i));
        }
        Button buttonCreateNewFavouriteCocktail = new Button("Add a new Cocktail");
        Dialog dialog = new Dialog();
        dialog.add(drinkName,category,genre,glass,instructions,drinkThumb,ingredientList.get(0),
                measureList.get(0),ingredientList.get(1),measureList.get(1),ingredientList.get(2), measureList.get(2),
                ingredientList.get(3),measureList.get(3),ingredientList.get(4), measureList.get(4),ingredientList.get(5),
                measureList.get(5),ingredientList.get(6), measureList.get(6),ingredientList.get(7),measureList.get(7),
                ingredientList.get(8), measureList.get(8),ingredientList.get(9),measureList.get(9),ingredientList.get(10),
                measureList.get(10),ingredientList.get(11),measureList.get(11),ingredientList.get(12),measureList.get(12),
                ingredientList.get(13),measureList.get(13),ingredientList.get(14),measureList.get(14), buttonCreateNewFavouriteCocktail);
        setDialog(dialog);
        buttonCreateNewFavouriteCocktail.addClickListener(clickEvent -> {
            CreateNewFromDialog(cocktailService, drinkName, category, genre, glass, instructions, drinkThumb, ingredientList,  measureList);
            dialog.close();
        });
    }

    private void CreateNewFromDialog(CocktailService cocktailService, TextField drinkName, TextField category, TextField genre, TextField glass, TextField instructions, TextField drinkThumb,
                                     ArrayList<TextField> ingredientList, ArrayList<TextField> measureList) {
        try {
            if(!drinkName.getValue().isEmpty()&&!instructions.getValue().isEmpty()&&!ingredientList.get(0).isEmpty()&&!measureList.get(0).isEmpty()) {

            FavouriteCocktail favouriteCocktail = new FavouriteCocktail(drinkName.getValue(), category.getValue(), genre.getValue(), glass.getValue(),
                    instructions.getValue(), drinkThumb.getValue(),ingredientList.get(0).getValue(), measureList.get(0).getValue(),ingredientList.get(1).getValue(),
                    measureList.get(1).getValue(),ingredientList.get(2).getValue(), measureList.get(2).getValue(), ingredientList.get(3).getValue(),
                    measureList.get(3).getValue(),ingredientList.get(4).getValue(), measureList.get(4).getValue(),ingredientList.get(5).getValue(),
                    measureList.get(5).getValue(),ingredientList.get(6).getValue(), measureList.get(6).getValue(),ingredientList.get(7).getValue(),
                    measureList.get(7).getValue(), ingredientList.get(8).getValue(), measureList.get(8).getValue(),ingredientList.get(9).getValue(),
                    measureList.get(9).getValue(),ingredientList.get(10).getValue(), measureList.get(10).getValue(),ingredientList.get(11).getValue(),
                    measureList.get(11).getValue(),ingredientList.get(12).getValue(), measureList.get(12).getValue(), ingredientList.get(13).getValue(),
                    measureList.get(13).getValue(),ingredientList.get(14).getValue(), measureList.get(14).getValue());

                cocktailService.addFavouriteCocktail(favouriteCocktail);
                favouriteGrid.setItems(cocktailService.getAllFavouriteCocktails());
            }
            else{Notification.show("DrinkName, instructions and at least Ingredient1 and measure1 muss be filled!",
                    5000, Notification.Position.TOP_CENTER);}
        }catch (Exception e){
            Notification.show("Something went wrong!",
                    5000, Notification.Position.TOP_CENTER);
        }
    }

    private void favouriteAdder(CocktailService cocktailService) {
        if(specificAndRandomGrid.getSelectionModel().getFirstSelectedItem().isPresent()) {
            Recipe cocktail = specificAndRandomGrid.getSelectionModel().getFirstSelectedItem().get();
            FavouriteCocktail favouriteCocktail = new FavouriteCocktail(cocktail.getStrDrink(),cocktail.getStrCategory(),cocktail.getStrAlcoholic(),cocktail.getStrGlass(),
                    cocktail.getStrInstructions(),cocktail.getStrDrinkThumb(),cocktail.getStrIngredient1(),cocktail.getStrIngredient2(),cocktail.getStrIngredient3(),
                    cocktail.getStrIngredient4(),cocktail.getStrIngredient5(),cocktail.getStrIngredient6(),(String)cocktail.getStrIngredient7(),(String)cocktail.getStrIngredient8(),
                    (String)cocktail.getStrIngredient9(),(String)cocktail.getStrIngredient10(),(String)cocktail.getStrIngredient11(),(String)cocktail.getStrIngredient12(),
                    (String)cocktail.getStrIngredient13(), (String)cocktail.getStrIngredient14(),(String)cocktail.getStrIngredient15(),
                    cocktail.getStrMeasure1(),cocktail.getStrMeasure2(),cocktail.getStrMeasure3(), cocktail.getStrMeasure4(),cocktail.getStrMeasure5(),
                    (String)cocktail.getStrMeasure6(),(String)cocktail.getStrMeasure7(),(String)cocktail.getStrMeasure8(),
                    (String)cocktail.getStrMeasure9(), (String)cocktail.getStrMeasure10(),(String)cocktail.getStrMeasure11(),(String)cocktail.getStrMeasure12(),
                    (String)cocktail.getStrMeasure13(), (String)cocktail.getStrMeasure14(),(String)cocktail.getStrMeasure15());
            cocktailService.addFavouriteCocktail(favouriteCocktail);
            favouriteGrid.setItems(cocktailService.getAllFavouriteCocktails());
        }
        else{
            Notification.show("Something went wrong!",
                5000, Notification.Position.TOP_CENTER);}
    }

    private void setDialog(Dialog dialog){
        dialog.setWidth("1000px");
        dialog.setHeight("800px");
        dialog.open();
    }

    private void fieldsInit() {
        textFieldName=new TextField("Enter a drink name");
        textFieldIngredient= new TextField("Enter an ingredient name");
        specificAndRandomGrid = new Grid<>(Recipe.class);
        favouriteGrid = new Grid<>(FavouriteCocktail.class);
        favouriteGrid.setItems(cocktailService.getAllFavouriteCocktails());
        buttonSearchByName = new Button("Search by name");
        buttonSearchIngredient=new Button("Search an Ingredient");
        buttonGetRandomCocktail = new Button("Get a random Cocktail");
        buttonAddFavourite = new Button("Add a selected cocktail to your favourites");
        buttonCreateNewCocktail = new Button("Click to create a new cocktail");
        buttonDeleteFavouriteCocktail = new Button("Click to delete a cocktail");
        buttonPostOnFacebook = new Button("Post on facebook");
    }

    private void specificAndRandomGridSet(Grid<Recipe> grid) {
        grid.setColumns("strDrink", "strCategory", "strAlcoholic", "strGlass", "strInstructions", "strIngredient1", "strMeasure1",
                "strIngredient2","strMeasure2", "strIngredient3", "strMeasure3","strIngredient4", "strMeasure4","strIngredient5",
                "strMeasure5","strIngredient6", "strMeasure6","strIngredient7", "strMeasure7","strIngredient8","strMeasure8",
                "strIngredient9","strMeasure9", "strIngredient10","strMeasure10", "strIngredient11","strMeasure11", "strIngredient12",
                "strMeasure12","strIngredient13","strMeasure13", "strIngredient14","strMeasure14", "strIngredient15", "strMeasure15");

        grid.getColumnByKey("strDrink").setHeader("Drink Name");
        grid.getColumnByKey("strCategory").setHeader("Category");
        grid.getColumnByKey("strAlcoholic").setHeader("Alcoholic");
        grid.getColumnByKey("strGlass").setHeader("Glass");
        grid.getColumnByKey("strInstructions").setHeader("Instructions");
        for(int i=1;i<=15;i++)
        {
            grid.getColumnByKey("strIngredient"+i).setHeader("Ingredient"+i);
            grid.getColumnByKey("strMeasure"+i).setHeader("Measure"+i);
        }
        grid.getColumns().forEach(cocktailColumn -> cocktailColumn.setResizable(true));
        grid.getColumns().forEach(cocktailColumn -> cocktailColumn.setAutoWidth(true));
    }

    private void favouriteGridSet(Grid<FavouriteCocktail> grid) {
        grid.setColumns("strDrink", "strCategory", "strAlcoholic", "strGlass", "strInstructions", "strIngredient1", "strMeasure1",
                "strIngredient2","strMeasure2", "strIngredient3", "strMeasure3","strIngredient4", "strMeasure4","strIngredient5",
                "strMeasure5","strIngredient6", "strMeasure6","strIngredient7", "strMeasure7","strIngredient8","strMeasure8",
                "strIngredient9","strMeasure9", "strIngredient10","strMeasure10", "strIngredient11","strMeasure11", "strIngredient12",
                "strMeasure12","strIngredient13","strMeasure13", "strIngredient14","strMeasure14", "strIngredient15", "strMeasure15");
        grid.getColumnByKey("strDrink").setHeader("Drink Name");
        grid.getColumnByKey("strCategory").setHeader("Category");
        grid.getColumnByKey("strAlcoholic").setHeader("Alcoholic");
        grid.getColumnByKey("strGlass").setHeader("Glass");
        grid.getColumnByKey("strInstructions").setHeader("Instructions");
        for(int i=1;i<=15;i++)
        {
            grid.getColumnByKey("strIngredient"+i).setHeader("Ingredient"+i);
            grid.getColumnByKey("strMeasure"+i).setHeader("Measure"+i);
        }
        grid.getColumns().forEach(cocktailColumn -> cocktailColumn.setResizable(true));
        grid.getColumns().forEach(cocktailColumn -> cocktailColumn.setAutoWidth(true));
    }

}
