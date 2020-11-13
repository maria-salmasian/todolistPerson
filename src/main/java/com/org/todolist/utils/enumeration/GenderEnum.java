package com.org.todolist.utils.enumeration;

public enum GenderEnum {
    FEMALE(1){
        public String toString(){
            return "id: 1, gender: female";
        }
    },
    MALE(2){
        public String toString(){
            return "id: 2, gender: male";
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

