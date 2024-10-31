package com.javaweb.service.impl;

import com.javaweb.converter.BuildingResponseConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.District;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingResponseConverter buildingResponseConverter;
    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        System.out.println(building);
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<UserEntity> staffAssignment = building.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity it : staffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if(staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }
    @Override
    public List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest){
        List<BuildingEntity> buildings = buildingRepository.search(buildingSearchRequest);
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();
        for (BuildingEntity building : buildings) {
            BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
            buildingSearchResponse = buildingResponseConverter.buildingResponseConverter(building);
            buildingSearchResponses.add(buildingSearchResponse);
//            buildingSearchResponse.setName(building.getName());
//            buildingSearchResponse.setId(building.getId());
//            Map<String,String> districts = District.type();
//            String res = ""+building.getDistrict();
//            if(districts.containsKey(res)){
//                buildingSearchResponse.setAddress(building.getStreet()+", "+building.getWard()+", "+districts.get(building.getDistrict()));
//            }
//            buildingSearchResponse.setNumberOfBasement(building.getNumberOfBasement());
//            buildingSearchResponse.setManagerName(building.getManagername());
//            buildingSearchResponse.setManagerPhone(building.getManagerphone());
//            buildingSearchResponse.setFloorArea(building.getFloorarea());
//            List<String> value = new ArrayList<>();
//            for(RentAreaEntity it:building.getRentArea()){
//                value.add(""+it.getValue());
//            }
//            buildingSearchResponse.setRentPrice(building.getRentprice());
//            buildingSearchResponse.setEmptyArea(String.join(""+",", value)+"");
//            buildingSearchResponses.add(buildingSearchResponse);
        }
        return buildingSearchResponses;
    }
}
