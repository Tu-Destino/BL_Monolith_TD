package com.TD.BL_Monolith_TD.util.exceptions;

public class IdNotFoundException extends RuntimeException{

    private static  final String ERROR_MESSAGE="No hay registros en la entidad %s con el id suministrado";
    public  IdNotFoundException(String nameEntity){
        super(String.format(ERROR_MESSAGE,nameEntity));
    }
}
