package com.gamelist.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gamelist.model.Game;
import com.gamelist.model.GameRepository;
import com.gamelist.model.Status;
import com.gamelist.model.StatusRepository;
import com.gamelist.model.User;
import com.gamelist.model.UserRepository;

import jakarta.validation.Valid;

@Controller
public class GamelistController {

	private GameRepository gameRepository;
	private StatusRepository statusRepository;
    private UserRepository userRepository;
    

	public GamelistController(GameRepository gameRepository, StatusRepository statusRepository, UserRepository userRepository) {
		this.gameRepository = gameRepository;
		this.statusRepository = statusRepository;
        this.userRepository = userRepository;
	}

    //poistaa pelin
    @RequestMapping(value = "/deletegame/{id}")
    public String deleteGame(@PathVariable Long id, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        Game game = gameRepository.findByIdAndUser(id, user).orElse(null);
        if (game != null) {
            gameRepository.delete(game);
        }
        return "redirect:/games";
    }

    //lisää pelin
    @RequestMapping(value = "/addgame")
    public String addMovie(Model model) {
        model.addAttribute("gameForm", new Game());
        model.addAttribute("statuses", statusRepository.findAll());
        return "gameform";

    }
    @RequestMapping(value = "/savegame", method = RequestMethod.POST)
    public String savegame(@Valid Game game, BindingResult bindingResult, @RequestParam("statusId") Long statusId, Model model, Principal principal) {
        if (bindingResult.hasErrors()){
            model.addAttribute("gameForm", game);
            model.addAttribute("statuses", statusRepository.findAll());
            return "gameform";
        }
        Status status = statusRepository.findById(statusId).orElse(null);
        game.setStatus(status);
        User user = userRepository.findByUsername(principal.getName());
        game.setUser(user);
        gameRepository.save(game);
        return "redirect:/games";
    }


	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping("/games")
	public String games(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("games", gameRepository.findByUser(user));
		return "games";
	}
}
