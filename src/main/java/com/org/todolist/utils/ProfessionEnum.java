package com.org.todolist.utils;

public enum ProfessionEnum {

    DANCER(1){
        public String toString(){
            return "1";
        }
    },
    SINGER(2){
        public String toString(){
            return "2";
        }
    },
    PROGRAMMER(3){
        public String toString(){
            return "3";
        }
    };
    private int id;
    ProfessionEnum(int id) {
        this.id =id;
    }

    public int getId() {
        return id;
    }

    public static ProfessionEnum  getById(int id)  {
        for(ProfessionEnum type : values()) {
            if(type.id == (id))
                return type;
        }

        return null;
    }
}
