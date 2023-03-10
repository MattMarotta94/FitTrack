package com.nashss.se.fittrack.activity.results;

/**
 * DeleteWorkoutResult.
 */
public class DeleteWorkoutResult {
    private final String deleted;

    /**
     * Instantiates a DeleteWorkoutResult object.
     * @param deleted the message displayed to confirm deletion.
     */
    public DeleteWorkoutResult(String deleted) {
        this.deleted = deleted;

    }
    public String getDeleted() {
        return this.deleted;
    }

    @Override
    public String toString() {
        return "DeleteWorkoutResult{" +
                "deleted='" + deleted + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String deleted;

        public Builder withString(String deleted) {
            this.deleted = deleted;
            return this;
        }

        public DeleteWorkoutResult build() {
            return new DeleteWorkoutResult(deleted);
        }
    }
}
