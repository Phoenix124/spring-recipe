package guru.springframework.services;

import guru.springframework.Repository.RecipeRepository;
import guru.springframework.domain.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes(){
        log.debug("I'm in the service");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }
    @Override
    public Recipe findById(Long L){
        Optional<Recipe> recipeOptional = recipeRepository.findById(L);

        if (!recipeOptional.isPresent()){
            throw new RuntimeException("recipe not found!");
        }

        return recipeOptional.get();
    }
}
