package pl.kostrzynkamien.cocktailomator.GUI;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
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



@Route("cocktail")
public class CocktailGUI extends VerticalLayout {

    private CocktailControler cocktailControler;
    private CocktailService cocktailService;
    private TextField textFieldName,textFieldIngredient;
    private Button buttonSearchByName,buttonGetRandomCocktail,buttonSearchIngredient, buttonAddFavourite,
            buttonCreateNewCocktail, buttonDeleteFavouriteCocktail,buttonCreateNewFavouriteCocktail;
    private Grid<Recipe> specificAndRandomGrid;
    private Grid<FavouriteCocktail> favouriteGrid;


    @Autowired
    public CocktailGUI(CocktailControler cocktailControler, CocktailService cocktailService) {
        this.cocktailService=cocktailService;
        fieldsInit();
        favouriteGridSet(favouriteGrid);
        specificAndRandomGridSet(specificAndRandomGrid);

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
                buttonSearchIngredient,buttonGetRandomCocktail,specificAndRandomGrid,buttonAddFavourite);
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
        TextField drinkName = new TextField("Enter Name");
        TextField category = new TextField("Enter Category");
        TextField genre = new TextField("Enter Genre");
        TextField glass = new TextField("Enter Glass");
        TextField instructions = new TextField("Enter Instructions");
        TextField drinkThumb = new TextField("Enter DrinkThumb");
        TextField ingredient1 = new TextField("Enter Ingredient1");
        TextField ingredient2 = new TextField("Enter ingredient2");
        TextField ingredient3 = new TextField("Enter ingredient3");
        TextField ingredient4 = new TextField("Enter ingredient4");
        TextField ingredient5 = new TextField("Enter ingredient5");
        TextField ingredient6 = new TextField("Enter ingredient6");
        TextField ingredient7 = new TextField("Enter Ingredient7");
        TextField ingredient8 = new TextField("Enter Ingredient8");
        TextField ingredient9 = new TextField("Enter Ingredient9");
        TextField ingredient10 = new TextField("Enter Ingredient10");
        TextField ingredient11 = new TextField("Enter Ingredient11");
        TextField ingredient12 = new TextField("Enter Ingredient12");
        TextField ingredient13 = new TextField("Enter Ingredient13");
        TextField ingredient14 = new TextField("Enter Ingredient14");
        TextField ingredient15 = new TextField("Enter Ingredient15");
        TextField measure1 = new TextField("Enter Measure1");
        TextField measure2 = new TextField("Enter Measure2");
        TextField measure3 = new TextField("Enter Measure3");
        TextField measure4 = new TextField("Enter Measure4");
        TextField measure5 = new TextField("Enter Measure5");
        TextField measure6 = new TextField("Enter Measure6");
        TextField measure7 = new TextField("Enter Measure7");
        TextField measure8 = new TextField("Enter Measure8");
        TextField measure9 = new TextField("Enter Measure9");
        TextField measure10 = new TextField("Enter Measure10");
        TextField measure11 = new TextField("Enter Measure11");
        TextField measure12 = new TextField("Enter Measure12");
        TextField measure13 = new TextField("Enter Measure13");
        TextField measure14 = new TextField("Enter Measure14");
        TextField measure15 = new TextField("Enter Measure15");
        buttonCreateNewFavouriteCocktail = new Button("Add a new Cocktail");
        Dialog dialog = new Dialog();
        dialog.add(drinkName,category,genre,glass,instructions,drinkThumb,ingredient1,measure1,ingredient2,measure2,ingredient3,measure3,
                ingredient4,measure4,ingredient5,measure5,ingredient6,measure6,ingredient7,measure7,
                ingredient8,measure8,ingredient9,measure9,ingredient10,measure10,ingredient11,measure11,ingredient12,measure12,
                ingredient13,measure13,ingredient14,measure14,ingredient15,measure15,buttonCreateNewFavouriteCocktail);
        setDialog(dialog);
        buttonCreateNewFavouriteCocktail.addClickListener(clickEvent -> {
            CreateNewFromDialog(cocktailService, drinkName, category, genre, glass, instructions, drinkThumb, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10, ingredient11, ingredient12, ingredient13, ingredient14, ingredient15, measure1, measure2, measure3, measure4, measure5, measure6, measure7, measure8, measure9, measure10, measure11, measure12, measure13, measure14, measure15);
        });
    }

    private void CreateNewFromDialog(CocktailService cocktailService, TextField drinkName, TextField category, TextField genre, TextField glass, TextField instructions, TextField drinkThumb, TextField ingredient1, TextField ingredient2, TextField ingredient3, TextField ingredient4, TextField ingredient5, TextField ingredient6, TextField ingredient7, TextField ingredient8, TextField ingredient9, TextField ingredient10, TextField ingredient11, TextField ingredient12, TextField ingredient13, TextField ingredient14, TextField ingredient15, TextField measure1, TextField measure2, TextField measure3, TextField measure4, TextField measure5, TextField measure6, TextField measure7, TextField measure8, TextField measure9, TextField measure10, TextField measure11, TextField measure12, TextField measure13, TextField measure14, TextField measure15) {
        try {
            if(!drinkName.getValue().isEmpty()&&!instructions.getValue().isEmpty()&&!ingredient1.getValue().isEmpty()&&!measure1.getValue().isEmpty()) {
            FavouriteCocktail favouriteCocktail = new FavouriteCocktail(drinkName.getValue(), category.getValue(), genre.getValue(), glass.getValue(), instructions.getValue(), drinkThumb.getValue(),
                    ingredient1.getValue(), ingredient2.getValue(), ingredient3.getValue(), ingredient4.getValue(), ingredient5.getValue(), ingredient6.getValue(), ingredient7.getValue(),
                    ingredient8.getValue(), ingredient9.getValue(), ingredient10.getValue(), ingredient11.getValue(), ingredient12.getValue(), ingredient13.getValue(),
                    ingredient14.getValue(), ingredient15.getValue(), measure1.getValue(), measure2.getValue(), measure3.getValue(), measure4.getValue(), measure5.getValue(),
                    measure6.getValue(), measure7.getValue(), measure8.getValue(), measure9.getValue(), measure10.getValue(), measure11.getValue(), measure12.getValue(), measure13.getValue(),
                    measure14.getValue(), measure15.getValue());
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
