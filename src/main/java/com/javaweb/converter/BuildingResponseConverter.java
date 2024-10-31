package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.District;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BuildingResponseConverter {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaRepository rentAreaRepository;

    public BuildingSearchResponse buildingResponseConverter(BuildingEntity item) {
        BuildingSearchResponse building = modelMapper.map(item, BuildingSearchResponse.class);
        Map<String,String> districts = District.type();
        String res = ""+item.getDistrict();
        if(districts.containsKey(res)){
            building.setAddress(item.getStreet()+", "+item.getWard()+", "+districts.get(item.getDistrict()));
        }
        List<String> value = new ArrayList<>();
        for(RentAreaEntity it:item.getRentArea()){
            value.add(""+it.getValue());
        }
        building.setRentArea(String.join(""+",", value)+"");
        return building;
    }
}
