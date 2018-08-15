package com.oocl.business.dao;

import com.oocl.business.model.Items;
import com.oocl.business.model.Orders;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ItemsDao {
    Items add(Items items);
}
