package server.helpers;

public enum CommandTypes {
    GET(1), CREATE(2), DELETE(3), EXIT(4);

    int index;

    CommandTypes(int index) {
        this.index = index;
    }

    public static CommandTypes findByIndex(int index){
        for(CommandTypes v : values()){
            if (v.index == index) {
                return v;
            }
        }
        return null;
    }

    public int getIndex() {
        return this.index;
    }
}
