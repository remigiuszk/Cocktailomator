package pl.kostrzynkamien.cocktailomator.Service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import pl.kostrzynkamien.cocktailomator.Model.Cocktail;
import pl.kostrzynkamien.cocktailomator.Model.Recipe;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CocktailControllerImpl implements CocktailControler{

    public CocktailControllerImpl() {
    }

    @Override
    public List<Recipe> getRecipies(String link){
        RestTemplate restTemplate = new RestTemplate();
        Cocktail cocktail = restTemplate.getForObject(link, Cocktail.class);
        if (cocktail.getRecipes() != null) return new ArrayList<>(cocktail.getRecipes());
        return null;
    }

    @Override
    public String getIngredientInfo(String ingredient) {
        RestTemplate restTemplate = new RestTemplate();
        String info;
        try {
            info = restTemplate.getForObject("https://www.thecocktaildb.com/api/json/v1/1/search.php?i=" + ingredient,
                    JsonNode.class).get("ingredients").get(0).get("strDescription").toString();
            return info;
        }catch (NullPointerException e) {
            return null;
        }
    }


}
