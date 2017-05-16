package ru.stqa.jpfste.mantis.model;

public class Issue {
    private int id;
    private String summary;
    private String description;
    private Project project;

    public int getId() {
        return id;
    }

    public void withId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }
}