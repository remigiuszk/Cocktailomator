package pl.kostrzynkamien.cocktailomator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrzynkamien.cocktailomator.Model.FavouriteCocktail;
import pl.kostrzynkamien.cocktailomator.Repo.CocktailRepo;

import java.util.List;

@Service
public class CocktailServiceImpl implements CocktailService{

    private CocktailRepo cocktailRepo;

    @Autowired
    public CocktailServiceImpl(CocktailRepo cocktailRepo) {
        this.cocktailRepo = cocktailRepo;
    }

    @Override
    public List<FavouriteCocktail> getAllFavouriteCocktails() {
        return cocktailRepo.findAll();
    }

    @Override
    public Boolean addFavouriteCocktail(FavouriteCocktail favouriteCocktail) {
        try{
            cocktailRepo.save(favouriteCocktail);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Boolean deleteFavouriteCocktailById(long id) {
        if(cocktailRepo.findById(id).isPresent()) {
            cocktailRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
