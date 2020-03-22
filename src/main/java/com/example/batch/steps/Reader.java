package com.example.batch.steps;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<String> {

    private String[] names = {"Koray Güney", "İlknur Güney", "Alkım Ece Güney"};
    private int count;
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("Inside Reader...");
        if(count < names.length){
            //if(count==2) throw new Exception(">>>>>>>>>>>>>>>> EXCEPTION TEST <<<<<<<<<<<<<<<<<<");
            return names[count++];
        } else {
            count = 0;
        }
        return null;
    }
}
