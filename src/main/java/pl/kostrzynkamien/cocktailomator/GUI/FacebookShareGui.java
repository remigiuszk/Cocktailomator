package pl.kostrzynkamien.cocktailomator.GUI;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;
import pl.kostrzynkamien.cocktailomator.Model.FavouriteCocktail;

@Component
@Route("share")
public class FacebookShareGui extends VerticalLayout {
    private Grid<FavouriteCocktail> favouriteCocktailGrid;
    private FavouriteCocktail favouriteCocktail;

    public void init(Grid<FavouriteCocktail> favouriteCocktailGrid){
        add(favouriteCocktailGrid);
    }
}
