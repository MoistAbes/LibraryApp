package com.example.libraryproject.mapper;

import com.example.libraryproject.dto.TitleDto;
import com.example.libraryproject.enitity.Title;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TitleMapper {

    public TitleDto mapToTitleDto(Title title){
        return new TitleDto(
                title.getId(),
                title.getAuthor(),
                title.getReleaseDate(),
                title.getTitle()
        );
    }

    public Title mapToTitle(TitleDto titleDto){
        return new Title(
                titleDto.getId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getReleaseDate()
        );
    }

    public List<TitleDto> mapToTitleDtoList(List<Title> titles){
        return titles.stream()
                .map(this::mapToTitleDto)
                .collect(Collectors.toList());
    }

}
