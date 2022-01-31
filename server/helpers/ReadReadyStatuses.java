package server.helpers;

public enum ReadReadyStatuses {
    NOT_READY(0), READY(1);

    int code;

    ReadReadyStatuses(int code) {
        this.code = code;
    }

    public static ReadReadyStatuses findByCode(int code){
        for(ReadReadyStatuses v : values()){
            if (v.code == code) {
                return v;
            }
        }
        return null;
    }

    public int getCode() {
        return this.code;
    }
}
