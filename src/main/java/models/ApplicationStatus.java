package src.main.java.models;

public enum ApplicationStatus {
    APPLIED(0),
    INTERVIEW(1),
    OFFER(2),
    REJECTED(3),
    ACCEPTED(4),
    WITHDRAWN(5);
    
    private ApplicationStatus(int level) {
        this.level = level;
    }

    private final int level;

    public int getLevel() {
        return level;
    }




    
}
