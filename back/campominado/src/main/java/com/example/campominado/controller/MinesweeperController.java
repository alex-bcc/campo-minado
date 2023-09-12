package com.example.campominado.controller;

import com.example.campominado.model.Cell;
import com.example.campominado.service.MinesweeperGame;
import com.example.campominado.service.MinesweeperSolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MinesweeperController {
    private MinesweeperGame game;

    public MinesweeperController(MinesweeperGame game) {
        this.game = game;
    }

    @GetMapping("/board")
    public ResponseEntity<Cell[][]> getBoard() {
        return ResponseEntity.ok(game.getBoard());
    }

    @GetMapping("/solver-board")
    public ResponseEntity<Cell[][]> getSolverBoard() {
        MinesweeperSolver minesweeperSolver = new MinesweeperSolver(game);
        minesweeperSolver.minesweeperOperations();
        return ResponseEntity.ok(game.getSolverBoard());
    }

    @PostMapping("/reveal")
    public ResponseEntity<Cell[][]> revealCell(@RequestParam int x, @RequestParam int y) {
        game.revealDepthSearch(x, y);
        return ResponseEntity.ok(game.getBoard());
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetGame() {
        try {
            game.resetGame();
            return ResponseEntity.ok("Game reset");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    @GetMapping("/game-info")
    public Map<String, Integer> getGameInfo() {
        return Map.of(
                "rows", game.getRows(),
                "cols", game.getCols(),
                "totalMines", game.getTotalMines()
        );
    }

}
