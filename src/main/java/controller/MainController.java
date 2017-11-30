package controller;


import model.ApiHandler;
import model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    private ApiHandler apiHandler = new ApiHandler();

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/games/";
    }

    @RequestMapping(value = "/games")
    public String displayGames(Model model, @RequestParam(required=false) boolean isSearch,
                               @RequestParam(required=false) String gameNotFound) {
        if(isSearch == true){
            model.addAttribute("isSearch",true);
            model.addAttribute("found",false);
            model.addAttribute("gameNotFound",gameNotFound);
        }
        model.addAttribute("search",new Game());
        model.addAttribute("games",apiHandler.getAllGames());
        return "allGames";
    }


    @RequestMapping(value = "/game/{gameId}")
    public String displayGame(Model model, @PathVariable int gameId) {
        model.addAttribute("search",new Game());
        model.addAttribute("game",apiHandler.getAGame(gameId));
        return "gameDetails";
    }

    @RequestMapping(value = "/game/search/")
    public String searchGame(Model model, @ModelAttribute Game gameSearched, RedirectAttributes redirectAttributes) {

        model.addAttribute("search",new Game());
        model.addAttribute("isSearch",true);
        model.addAttribute("gameSearched",gameSearched.getName());
        Game[] games = apiHandler.searchGame(gameSearched.getName());
        if(games == null){
            //redirect could be done
            redirectAttributes.addAttribute("isSearch", true);
            redirectAttributes.addAttribute("found", false);
            redirectAttributes.addAttribute("gameNotFound", gameSearched.getName());
            return "redirect:/games/";
        }else{
            model.addAttribute("found",true);
            model.addAttribute("games",games);
            return "allGames";
        }
    }


}
