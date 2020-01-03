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
import pl.kostrzynkamien.cocktailomator.Model.Recipe;
import pl.kostrzynkamien.cocktailomator.Service.CocktailControler;

import java.lang.reflect.Type;

@Route("cocktail")
public class CocktailGUI extends VerticalLayout {

    private CocktailControler cocktailControler;
    private TextField textFieldName,textFieldIngredient;
    private Button buttonSearchByName,buttonGetRandomCocktail,buttonSearchIngredient;
    private Grid<Recipe> specificAndRandomGrid,favouriteGrid;
    private Dialog dialog;

    @Autowired
    public CocktailGUI(CocktailControler cocktailControler) {
        fieldsInit();
        specificAndRandomGridSet(specificAndRandomGrid);

        buttonGetRandomCocktail.addClickListener(clickEvent -> {
            if(cocktailControler.getRecipies("https://www.thecocktaildb.com/api/json/v1/1/random.php")!=null)
            specificAndRandomGrid.setItems(cocktailControler.getRecipies("https://www.thecocktaildb.com/api/json/v1/1/random.php"));
            else Notification.show("Cocktail not found",
                    5000, Notification.Position.TOP_CENTER);
        });
        buttonSearchByName.addClickListener(clickEvent -> {
            if(cocktailControler.getRecipies("https://www.thecocktaildb.com/api/json/v1/1/search.php?s="+textFieldName.getValue().replaceAll(" ",""))!=null)
            specificAndRandomGrid.setItems(cocktailControler.getRecipies("https://www.thecocktaildb.com/api/json/v1/1/search.php?s="+
                    textFieldName.getValue().replaceAll(" ","")));
            else Notification.show("Connection error! Try again later",
                    5000, Notification.Position.TOP_CENTER);
        });
        buttonSearchIngredient.addClickListener(clickEvent -> {
            if(cocktailControler.getIngredientInfo(textFieldIngredient.getValue().replaceAll(" ",""))!=null)
                setDialog(cocktailControler.getIngredientInfo(textFieldIngredient.getValue().replaceAll(" ","")));

                else Notification.show("Ingredient not found",
                    5000, Notification.Position.TOP_CENTER);
        });
        add(textFieldName,buttonSearchByName,textFieldIngredient,buttonSearchIngredient,buttonGetRandomCocktail,specificAndRandomGrid);
    }

    private void setDialog(String text){
        dialog.add(new Label(text));
        dialog.setWidth("1000px");
        dialog.setHeight("800px");
        dialog.open();
    }
    private void fieldsInit() {
        textFieldName=new TextField("Enter a drink name");
        textFieldIngredient= new TextField("Enter an ingredient name");
        specificAndRandomGrid = new Grid<>(Recipe.class);
        buttonSearchByName = new Button("Search by name");
        buttonSearchIngredient=new Button("Search an Ingredient");
        buttonGetRandomCocktail = new Button("Get a random Cocktail");
        favouriteGrid = new Grid<>(Recipe.class);
        dialog = new Dialog();
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
        grid.getColumnByKey("strIngredient1").setHeader("Ingredient1");
        grid.getColumnByKey("strMeasure1").setHeader("Measure1");
        grid.getColumnByKey("strIngredient2").setHeader("Ingredient2");
        grid.getColumnByKey("strMeasure2").setHeader("Measure2");
        grid.getColumnByKey("strIngredient3").setHeader("Ingredient3");
        grid.getColumnByKey("strMeasure3").setHeader("Measure3");
        grid.getColumnByKey("strIngredient4").setHeader("Ingredient4");
        grid.getColumnByKey("strMeasure4").setHeader("Measure4");
        grid.getColumnByKey("strIngredient5").setHeader("Ingredient5");
        grid.getColumnByKey("strMeasure5").setHeader("Measure5");
        grid.getColumnByKey("strIngredient6").setHeader("Ingredient6");
        grid.getColumnByKey("strMeasure6").setHeader("Measure6");
        grid.getColumnByKey("strIngredient7").setHeader("Ingredient7");
        grid.getColumnByKey("strMeasure7").setHeader("Measure7");
        grid.getColumnByKey("strIngredient8").setHeader("Ingredient8");
        grid.getColumnByKey("strMeasure8").setHeader("Measure8");
        grid.getColumnByKey("strIngredient9").setHeader("Ingredient9");
        grid.getColumnByKey("strMeasure9").setHeader("Measure9");
        grid.getColumnByKey("strIngredient10").setHeader("Ingredient10");
        grid.getColumnByKey("strMeasure10").setHeader("Measure10");
        grid.getColumnByKey("strIngredient11").setHeader("Ingredient11");
        grid.getColumnByKey("strMeasure11").setHeader("Measure11");
        grid.getColumnByKey("strIngredient12").setHeader("Ingredient12");
        grid.getColumnByKey("strMeasure12").setHeader("Measure12");
        grid.getColumnByKey("strIngredient13").setHeader("Ingredient13");
        grid.getColumnByKey("strMeasure13").setHeader("Measure13");
        grid.getColumnByKey("strIngredient14").setHeader("Ingredient14");
        grid.getColumnByKey("strMeasure14").setHeader("Measure14");
        grid.getColumnByKey("strIngredient15").setHeader("Ingredient15");
        grid.getColumnByKey("strMeasure15").setHeader("Measure15");
        grid.getColumns().forEach(personColumn -> personColumn.setResizable(true));
        specificAndRandomGrid.getColumns().forEach(personColumn -> personColumn.setAutoWidth(true));
    }


}
