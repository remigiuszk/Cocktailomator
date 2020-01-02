package pl.kostrzynkamien.cocktailomator.Service;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import pl.kostrzynkamien.cocktailomator.Model.CocktailJSON2POJO;

@Controller
public class CocktailController {
    public CocktailController() {
        RestTemplate restTemplate = new RestTemplate();
        CocktailJSON2POJO forObject = restTemplate.getForObject("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita",CocktailJSON2POJO.class);
        System.out.println(forObject);

    }



}
