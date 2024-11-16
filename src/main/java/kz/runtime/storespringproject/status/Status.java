package kz.runtime.storespringproject.status;


public enum Status {
    CREATED(1),
    DELIVERED(2),
    CANCELLED(3);

    final Integer i;

    Status(int i) {
        this.i = i;
    }
    public Integer getValue() {
        return i;
    }


}
