package com.forge.domain.execution;

import java.util.Objects;

public final class Execution {

    private final String id;
    private ExecutionStatus status;
    private ExecutionStage currentStage;
    private final ExecutionContext context;

    public Execution(
            String id,
            ExecutionContext context) {

        this.id = requireNonBlank(id, "id");
        this.context = Objects.requireNonNull(
                context,
                "context must not be null");

        this.status = ExecutionStatus.NOT_STARTED;
        this.currentStage = ExecutionStage.EVIDENCE;
    }

    public String id() {
        return id;
    }

    public ExecutionStatus status() {
        return status;
    }

    public ExecutionStage currentStage() {
        return currentStage;
    }

    public ExecutionContext context() {
        return context;
    }

    public void start() {
        this.status = ExecutionStatus.RUNNING;
    }

    public void moveTo(ExecutionStage stage) {
        this.currentStage = Objects.requireNonNull(
                stage,
                "stage must not be null");
    }

    public void waitForClarification() {
        this.status = ExecutionStatus.WAITING_FOR_CLARIFICATION;
    }

    public void resume() {
        this.status = ExecutionStatus.RUNNING;
    }

    public void complete() {
        this.status = ExecutionStatus.COMPLETED;
    }

    public void fail() {
        this.status = ExecutionStatus.FAILED;
    }

    // Método privado de esta propia clase; no requiere import.
    private static String requireNonBlank(
            String value,
            String fieldName) {

        Objects.requireNonNull(
                value,
                fieldName + " must not be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " must not be blank");
        }

        return value;
    }
}