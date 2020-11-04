package com.org.todolist.utils.enumeration;

public enum StatusEnum {
    IN_PROGRESS(1){
        public String toString(){
            return "1";
        }
    },
    TO_DO(2){
        public String toString(){
            return "2";
        }
    },
    DONE(3){
        public String toString(){
            return "3";
        }
    },
    DEPRECATED(4){
        public String toString(){
            return "4";
        }
    },
    DELETED(5){
        public String toString(){
            return "5";
        }
    };

    private int id;

    StatusEnum(int id) {
        this.id =id;
    }

    public int getId() {
        return id;
    }

    public static StatusEnum  getById(int id)  {
        for(StatusEnum type : values()) {
            if(type.id == (id))
                return type;
        }

        return null;
    }
}
