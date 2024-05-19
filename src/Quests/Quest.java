package Quests;

import java.io.Serializable;

public abstract class Quest implements Serializable {
    private String name;
    private String description;
    private boolean completed;
    private boolean questAccepted;

    public Quest(String name, String description) {
        this.name = name;
        this.description = description;
        this.completed = false;
        this.questAccepted = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete() {
        completed = true;
    }

    public abstract void startQuest();


    public void setQuestAccepted() {
        questAccepted = true;
    }

    public boolean isQuestAccepted() {
        return questAccepted;
    }
}