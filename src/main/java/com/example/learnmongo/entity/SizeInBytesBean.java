package com.example.learnmongo.entity;

import com.example.learnmongo.annotations.SizeInBytes;
import lombok.Data;

@Data
public class SizeInBytesBean {

    @SizeInBytes(value = 12, message = "Value is greater than required size")
    String inputData;
}
