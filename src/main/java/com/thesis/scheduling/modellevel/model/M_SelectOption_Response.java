package com.thesis.scheduling.modellevel.model;

import lombok.Data;

@Data
public class M_SelectOption_Response implements Comparable<M_SelectOption_Response> {
    private Integer id;
	
	private String value;
	
	private String text;

	@Override
    public int compareTo(M_SelectOption_Response value) {
        return this.getId().compareTo(value.getId());
    }

    @Override
    public String toString() {
        return this.getText();
    }
}
