package controller;


import model.ApiHandler;
import model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private ApiHandler apiHandler = new ApiHandler();


    @RequestMapping(value = "/games")
    public String displayGames(Model model) {
        model.addAttribute("search",new Game());
        model.addAttribute("games",apiHandler.getAllGames());
        return "allGames";
    }


    @RequestMapping(value = "/game/{gameId}")
    public String displayGames(Model model, @PathVariable int gameId) {
        model.addAttribute("search",new Game());
        model.addAttribute("game",apiHandler.getAGame(gameId));
        return "gameDetails";
    }

    @RequestMapping(value = "/game/search/")
    public String searchGame(Model model, @ModelAttribute Game gameSearched) {

        model.addAttribute("search",new Game());
        Game game = apiHandler.searchGame(gameSearched.getName());
        if(game == null){
            //redirect could be done
            model.addAttribute("notFound",true);
            model.addAttribute("games",apiHandler.getAllGames());
            return "allGames";
        }else{
            model.addAttribute("game",game);
            return "gameDetails";
        }
    }


}
