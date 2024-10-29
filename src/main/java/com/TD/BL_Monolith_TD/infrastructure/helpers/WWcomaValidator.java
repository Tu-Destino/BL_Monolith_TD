package com.TD.BL_Monolith_TD.infrastructure.helpers;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class WWcomaValidator implements ConstraintValidator<WWcomma, List<String>> {

    private String[] words;

    @Override
    public void initialize(WWcomma constraintAnnotation) {
        this.words = constraintAnnotation.words();
    }

    @Override
    public boolean isValid(List<String> strings, ConstraintValidatorContext constraintValidatorContext) {
        if (strings == null || strings.isEmpty()) {
            return true; //If the list is null or empty the validation doesn't execute
        }

        for (String elemento : strings) {

            //Divide the array in commas for validate
            String[] wordsElement = elemento.split(",");

            for (String word : wordsElement){
                word = word.trim(); //Remove blank spaces

                // Verify if the word is allowed
                boolean isValidWord = false;
                for (String allowWord : words){
                    if (word.equals(allowWord)){
                        isValidWord = true;
                        break;
                    }
                }
                if (!isValidWord) {
                    return false; // If the word doesn't have comma in the final
                }
            }
        }
        return true; //If all the words are valid return true
    }

}
