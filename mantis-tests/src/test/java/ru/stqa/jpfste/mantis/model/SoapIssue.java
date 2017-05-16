package ru.stqa.jpfste.mantis.model;

public class SoapIssue {
    private int id;
    private String summary;
    private String description;
    private Project project;

    public int getId() {
        return id;
    }

    public SoapIssue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public SoapIssue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SoapIssue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public SoapIssue withProject(Project project) {
        this.project = project;
        return this;
    }
}
