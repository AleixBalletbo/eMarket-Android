package com.aleixballetbo.data.repository.datasource.cloud.model;


import com.aleixballetbo.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static Product map (ProductDTO productDTO) {
        return new Product(productDTO.get_id(), productDTO.getName(), productDTO.getPrice(), productDTO.getDescription(), productDTO.getOwner());
    }

    public static List<Product> map(List<ProductDTO> productDTOList) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < productDTOList.size(); i++) {
            productList.add(map(productDTOList.get(i)));
        }
        return productList;
    }
}
