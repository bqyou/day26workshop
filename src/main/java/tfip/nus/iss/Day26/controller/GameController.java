package tfip.nus.iss.Day26.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import tfip.nus.iss.Day26.Utils;
import tfip.nus.iss.Day26.repo.GameRepo;

@RestController
@RequestMapping
public class GameController {

    @Autowired
    private GameRepo gameRepo;

    @GetMapping(path = "/games")
    public ResponseEntity<String> getGames(@RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset,
            Model model) {
        List<String> json = gameRepo.listGames(limit, offset).stream().map(v -> v.toJson()).toList();

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        json.stream().map(v -> Utils.toJson(v)).forEach(v -> {
            arrBuilder.add(v);
        });
        JsonObject j = Json.createObjectBuilder()
                .add("games", arrBuilder.build())
                .add("offset", offset)
                .add("limit", limit)
                .add("total", gameRepo.countGames())
                .add("timestamp", new Date().toString())
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(j.toString());
    }

    @GetMapping(path = "/games/rank")
    public ResponseEntity<String> getGamesByRank(@RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset,
            Model model) {
        List<String> json = gameRepo.listGamesByRank(limit, offset).stream().map(v -> v.toJson()).toList();

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        json.stream().map(v -> Utils.toJson(v)).forEach(v -> {
            arrBuilder.add(v);
        });
        JsonObject j = Json.createObjectBuilder()
                .add("games", arrBuilder.build())
                .add("offset", offset)
                .add("limit", limit)
                .add("total", gameRepo.countGames())
                .add("timestamp", new Date().toString())
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(j.toString());
    }

}
