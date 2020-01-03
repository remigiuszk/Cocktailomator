package pl.kostrzynkamien.cocktailomator.Service;

import pl.kostrzynkamien.cocktailomator.Model.FavouriteCocktail;

import java.util.List;

public interface CocktailService {
    List<FavouriteCocktail> getAllFavouriteCocktails();
    Boolean addFavouriteCocktail(FavouriteCocktail favouriteCocktail);
    Boolean deleteFavouriteCocktailById(long id);
}
