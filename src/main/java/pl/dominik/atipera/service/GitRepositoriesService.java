package pl.dominik.atipera.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pl.dominik.atipera.exception.UserNotFoundException;
import pl.dominik.atipera.model.Branch;
import pl.dominik.atipera.model.RepositoryResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitRepositoriesService {
    private final static String REPOS_URL_TEMPLATE = "https://api.github.com/users/{username}/repos";
    private final static String BRANCHES_URL_TEMPLATE = "https://api.github.com/repos/{owner}/{repo}/branches";
    private final WebClient webClient = WebClient.builder().build();

    public List<RepositoryResponse> getGitRepositories(String username, String accept) {
        WebClient.Builder builder = WebClient.builder();
        try {
            List<RepositoryResponse> response = builder.build()
                    .get()
                    .uri(REPOS_URL_TEMPLATE, username)
                    .header("Accept", accept)
                    .retrieve()
                    .bodyToFlux(RepositoryResponse.class)
                    .collectList()
                    .block();

            assert response != null;
            List<RepositoryResponse> nonForkedRepositories = response.stream()
                    .filter(repo -> !repo.isFork())
                    .collect(Collectors.toList());

            for (RepositoryResponse repository : nonForkedRepositories) {
                List<Branch> branches = fetchBranches(repository.getOwner().getOwnerLogin(),
                        repository.getRepositoryName(), accept);
                repository.setBranches(branches);
            }

            return nonForkedRepositories;
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UserNotFoundException("User not found");
            }
            throw new RuntimeException("Error while fetching repositories", e);
        }
    }

    private List<Branch> fetchBranches(String owner, String repo, String accept) {
        return webClient.get()
                .uri(BRANCHES_URL_TEMPLATE, owner, repo)
                .header("Accept", accept)
                .retrieve()
                .bodyToFlux(Branch.class)
                .collectList()
                .block();
    }
}
