package server.helpers;

public enum IdOrNameStatuses {
    ID(2), NAME(1);

    int code;

    IdOrNameStatuses(int code) {
        this.code = code;
    }

    public static IdOrNameStatuses findByCode(int code){
        for(IdOrNameStatuses v : values()){
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