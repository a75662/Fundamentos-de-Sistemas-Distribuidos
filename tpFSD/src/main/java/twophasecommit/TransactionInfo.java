package twophasecommit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TransactionInfo {

    private Set<Integer> participants;
    private Set<Integer> prepared;
    private Set<Integer> committed;
    private List<Integer> indexes;
    private int id;
    private int client;

    private Status status;

    private CompletableFuture<Object> answer;

    public TransactionInfo(int id, int client) {
        this.id = id;
        status = Status.RUNNING;
        this.participants = new HashSet<>();
        this.prepared = new HashSet<>();
        this.committed = new HashSet<>();
        this.indexes = new ArrayList<>();
        this.client = client;
    }

    public boolean allPrepared() {
        return prepared.size() == participants.size();
    }

    public void addPrepared(Integer from) {
        this.prepared.add(from);
    }

    public void addParticipant(int i) {
        this.participants.add(i);
    }

    public void addIndex(Integer index) {
        this.indexes.add(index);
    }

    public void setPreparing() {
        this.status = Status.PREPARING;
    }

    public boolean isPreparing() {
        return this.status == Status.PREPARING;
    }

    public void addCommitted(int from) {
        this.committed.add(from);
    }

    public boolean allCommitted() {
        return participants.size() == committed.size();
    }

    public boolean containsParticipant(Integer from) {
        return participants.contains(from);
    }

    public void setCommitting() {
        this.status = Status.COMMITTING;
    }

    public Status getStatus() {
        return status;
    }

    public Set<Integer> getParticipants() {
        return participants;
    }

    public int getClient() {
        return client;
    }

    public int getId() {
        return id;
    }

    public Set<Integer> getPrepared() {
        return prepared;
    }

    public void setCommitted() {
        this.status = Status.COMMITTED;
    }

    public void complete(Object result) {
        this.answer.complete(result);
    }

    public List<Integer> getIndexes() {
        return indexes;
    }

    public CompletableFuture<Object> getAnswer() {
        return answer;
    }

    public void createAnswer() {
        this.answer = new CompletableFuture<>();
    }


    public enum Status {
        RUNNING, PREPARING, COMMITTING, COMMITTED
    }
}
