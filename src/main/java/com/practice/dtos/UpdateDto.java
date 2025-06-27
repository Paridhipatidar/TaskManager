package com.practice.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateDto {

	String description;
	String deadline;
	Boolean completed;
}
