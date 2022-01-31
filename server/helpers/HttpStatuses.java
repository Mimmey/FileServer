package server.helpers;

public enum HttpStatuses {
    FORBIDDEN(403), OK(200), NOT_FOUND(404), ERROR(500);

    int code;

    HttpStatuses(int code) {
        this.code = code;
    }

    public static HttpStatuses findByCode(int code){
        for(HttpStatuses v : values()){
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
