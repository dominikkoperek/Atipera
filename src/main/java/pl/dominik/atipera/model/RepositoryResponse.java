package pl.dominik.atipera.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RepositoryResponse {
    @JsonProperty("name")
    private String repositoryName;
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("branch_url")
    private List<Branch> branches;
    @JsonProperty("fork")
    private boolean fork;

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
