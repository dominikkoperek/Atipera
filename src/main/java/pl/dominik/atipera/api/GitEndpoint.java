package pl.dominik.atipera.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dominik.atipera.service.GitRepositoriesService;
import pl.dominik.atipera.exception.UserNotFoundException;
import pl.dominik.atipera.model.RepositoryResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GitEndpoint {
    private final GitRepositoriesService repositoriesService;

    public GitEndpoint(GitRepositoriesService service) {
        this.repositoriesService = service;
    }

    @GetMapping(value = "/repositories/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUserRepositories(@PathVariable(name = "username") String username,
                                                  @RequestHeader String accept) {
        try {
            List<RepositoryResponse> userRepositories = repositoriesService.getGitRepositories(username, accept);
            return ResponseEntity.ok(userRepositories);
        } catch (UserNotFoundException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", e.getMessage());
            responseBody.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));

            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }
}
