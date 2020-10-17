package tacos.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.repository.IngredientRepository;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo){
        this.ingredientRepo=ingredientRepo;
    }

    @GetMapping
    public String showDesignForm(Model model) {

//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "pszenna", Ingredient.Type.WRAP),
//                new Ingredient("COTO", "kukurydziana", Ingredient.Type.WRAP),
//                new Ingredient("GRBF", "mielona wołowina", Ingredient.Type.PROTEIN),
//                new Ingredient("CARN", "kawałki mięsa", Ingredient.Type.PROTEIN),
//                new Ingredient("TMTO", "pomidory pokrojone w kostkę", Ingredient.Type.VEGGIES),
//                new Ingredient("LETC", "sałata", Ingredient.Type.VEGGIES),
//                new Ingredient("CHED", "cheddar", Ingredient.Type.CHEESE),
//                new Ingredient("JACK", "Monterey Jack", Ingredient.Type.CHEESE),
//                new Ingredient("SLSA", "pikantny sos pomidorowy", Ingredient.Type.SAUCE),
//                new Ingredient("SRCR", "śmietana", Ingredient.Type.SAUCE)
//        );
        List<Ingredient> ingredients= new ArrayList<>();
        ingredientRepo.findAll().forEach(i->ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        //model.addAttribute("design", new Taco());
        return "design";
    }


    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Przetwarzanie projektu taco: " + design);
        return "redirect:/orders/current";
    }

}
