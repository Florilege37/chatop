package com.chatop.chatop.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RentalListResponse {

    private List<RentalResponse> rentals = new ArrayList<RentalResponse>();
}
