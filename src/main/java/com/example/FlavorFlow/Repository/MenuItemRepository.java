package com.example.FlavorFlow.Repository;

import com.example.FlavorFlow.Model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
