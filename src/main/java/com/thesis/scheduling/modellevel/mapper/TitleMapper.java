package com.thesis.scheduling.modellevel.mapper;

import org.mapstruct.Mapper;

import com.thesis.scheduling.modellevel.entity.Title;
import com.thesis.scheduling.modellevel.model.M_Tittle_ShowAllPublic_Response;

@Mapper(componentModel = "spring")
public interface TitleMapper {
	
	Iterable<M_Tittle_ShowAllPublic_Response> toMShowAll(Iterable<Title> title);
	
}
