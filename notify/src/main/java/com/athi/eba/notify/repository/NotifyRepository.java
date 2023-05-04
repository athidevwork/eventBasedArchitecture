package com.athi.eba.notify.repository;

import com.athi.eba.notify.model.Notify;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotifyRepository extends JpaRepository<Notify, Long> {
}
