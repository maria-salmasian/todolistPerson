package com.org.todolist.utils.enumeration;

public enum ProfessionEnum {

    DANCER(1){
        public String toString(){
            return "id: 1, profession: dancer";
        }
    },
    SINGER(2){
        public String toString(){
            return "id: 2, profession: singer";
        }
    },
    PROGRAMMER(3){
        public String toString(){
            return "id: 3, profession: programmer";
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
