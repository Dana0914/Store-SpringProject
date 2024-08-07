package kz.runtime.storespringproject.status;


public enum Status {
    PROCESSING(1),
    DELIVERED(2),
    CANCELED(3);

    final Integer i;

    Status(int i) {
        this.i = i;
    }
    public Integer getValue() {
        return i;
    }


}
