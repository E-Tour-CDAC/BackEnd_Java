package com.example.mapper;

import com.example.dto.CustomerMasterDTO;
import com.example.entities.CustomerMaster;
import com.example.model.CustomerMasterModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerMasterMapper {

    // DTO → Model
    public CustomerMasterModel toModel(CustomerMaster entity) {
        if (entity == null) return null;

        CustomerMasterModel model = new CustomerMasterModel();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setEmail(entity.getEmail());
        model.setPhone(entity.getPhone());
        model.setAddress(entity.getAddress());
        model.setCustomerRole(entity.getCustomerRole().name());
        model.setAuthProvider(entity.getAuthProvider().name());
        model.setProfileCompleted(entity.getProfileCompleted());
        model.setBookingHeaders(entity.getBookingHeaders());

        return model;
    }

    // Model → DTO
    public CustomerMasterDTO toDTO(CustomerMasterModel model) {
        if (model == null) return null;

        CustomerMasterDTO dto = new CustomerMasterDTO();
        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEmail(model.getEmail());
        dto.setPhone(model.getPhone());
        dto.setAddress(model.getAddress());

        // ⚠️ Decide carefully if password should be exposed
        dto.setPassword(model.getPassword());

        dto.setCustomerRole(model.getCustomerRole());
        dto.setAuthProvider(model.getAuthProvider());
        dto.setProfileCompleted(model.getProfileCompleted());
        dto.setBookingHeaders(model.getBookingHeaders());

        return dto;
    }
}
