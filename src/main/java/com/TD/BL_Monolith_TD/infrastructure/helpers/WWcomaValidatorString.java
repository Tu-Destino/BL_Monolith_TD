package com.TD.BL_Monolith_TD.infrastructure.helpers;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WWcomaValidatorString implements ConstraintValidator<WWcomma, String> {

    private String[] words;

    @Override
    public void initialize(WWcomma constraintAnnotation) {
        this.words = constraintAnnotation.words();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.trim().isEmpty()) {
            return true; //If the String is null or empty the validation doesn't execute
        }

        String[] wordsElement = value.split(",");

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
        return true; //If all the words are valid return true
    }

}
