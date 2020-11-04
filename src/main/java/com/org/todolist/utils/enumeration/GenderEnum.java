package com.org.todolist.utils.enumeration;

public enum GenderEnum {
    FEMALE(1){
        public String toString(){
            return "1";
        }
    },
    MALE(2){
        public String toString(){
            return "2";
        }
    };
    private int id;
    GenderEnum(int id) {
        this.id =id;
    }

    public int getId() {
        return id;
    }

    public static GenderEnum getById(int id){
        for(GenderEnum type : values()) {
            if(type.id == (id))
                return type;
        }

        return null;
    }

}

