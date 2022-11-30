package com.org.todolist.utils.enumeration;

public enum StatusEnum {
    IN_PROGRESS(1){
        public String toString(){
            return "id: 1, state: in progress";
        }
    },
    TO_DO(2){
        public String toString(){
            return "id: 2, state: to do";
        }
    },
    DONE(3){
        public String toString(){
            return "id: 3, state: done";
        }
    },
    DEPRECATED(4){
        public String toString(){
            return "id: 4, state: deprecated";
        }
    },
    DELETED(5){
        public String toString(){
            return "id: 5, state: deleted";
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
