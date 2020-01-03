package pl.kostrzynkamien.cocktailomator.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrzynkamien.cocktailomator.Model.FavouriteCocktail;

@Repository
public interface CocktailRepo extends JpaRepository<FavouriteCocktail,Long> {
}
