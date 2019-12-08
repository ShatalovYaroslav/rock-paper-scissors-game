package org.myproject.game.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myproject.game.model.GameMove;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Stream;

@Controller
@RequestMapping(value = "/game")
public class DefaultFrontendController {

    private final Logger logger = LogManager.getRootLogger();

    @RequestMapping(value = "/online/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String playOnline(Model model) {

        String playerId = "";
        String errorMessage = "";

        model.addAttribute("playerId", playerId);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("moves", Stream.of(GameMove.values())
                .map(Enum::name).
                toArray(String[]::new));

        logger.debug("We are in the welcome controller for RSP online game");

        return "RSPGame";
    }
}
