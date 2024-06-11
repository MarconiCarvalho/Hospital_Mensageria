package com.hospital.mensageria.repositories;

import com.hospital.mensageria.models.CallHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CallHistoryRepository extends JpaRepository<CallHistory, Long> {
}
