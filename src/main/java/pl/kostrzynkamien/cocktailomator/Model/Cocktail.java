package pl.kostrzynkamien.cocktailomator.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "drinks"
})
public class Cocktail {

    @JsonProperty("drinks")
    private List<Recipe> recipes = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("drinks")
    public List<Recipe> getRecipes() {
        return recipes;
    }

    @JsonProperty("drinks")
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "CocktailJSON2POJO{" +
                "drinks=" + recipes +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

